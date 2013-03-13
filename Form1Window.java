import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import org.forms.Form1;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOption;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.listoptions.ListSubOptions;
import org.forms.form1.listoptions.Option;
import org.forms.form1.spinner.SpinnerOption;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

public class Form1Window extends JFrame {

	private Main main;
	private FormGenerator formGenerator;
	private JPanel contentPane;
	private JPanel panel;
	private int row = 0;
	private Hashtable syntaxMap;
	private Hashtable syntaxMapLabelToName;
	private LanguageEntry languageEntry;
	private String[] variables;

	public FormGenerator tempFormGenerator;
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public FormGenerator getFormGenerator() {
		return this.formGenerator;
	}

	/*
	 * Creating the frame.
	 */
	public Form1Window(String language, String[] variables,String form1XmlName, Main main) {
		this.main = main;
		this.variables = variables;
		// initializing form1
		if(this.main.formGenerator == null)
		{
			this.main.formGenerator = new FormGenerator();
		}
		if(this.main.form1IsFirstTime)
		{
			this.main.formGenerator.generateForm1(form1XmlName);
		}
		this.formGenerator = this.main.formGenerator;
		
		
		showForm1(language);
	}

	/*
	 * Rendering form1
	 */
	public void showForm1(String language) {
		//cloning initial object content to a temporary object
		tempFormGenerator = (FormGenerator) formGenerator.clone();
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// setting language to render form1
		formGenerator.setLanguage(language);
		formGenerator.updateLanguageMapForm1();
		// retrieving language info which is stored in a hash table
		syntaxMap = formGenerator.getSyntaxMapForm1();
		syntaxMapLabelToName = formGenerator.getSyntaxMapLabelToNameForm1();

		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	//rolling back to the initial opening state of this form
            	formGenerator = (FormGenerator) tempFormGenerator.clone();
            	Form1Window.this.main.formGenerator = formGenerator;
            }
        });
		
		// setting window size
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getSize().width;
				int height = getSize().height;

				Dimension dimension = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int screenWidth = dimension.width;
				int screenHeight = dimension.height;

				int currentHeight = height > screenHeight ? screenHeight
						: height;
				int currentWidth = width > screenWidth ? screenWidth : width;

				setSize(new Dimension(currentWidth, currentHeight));

				super.componentResized(e);
			}

		});
		// panel to render form1
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel(new GridBagLayout());

		// adding vertical and horizontal scroll bars
		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);
		// form1 title
		String title = formGenerator.getForm1().getTitle();
		if (syntaxMap.containsKey(title)) {
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get(title);
			title = titleEntry.getLabel();
		}
		// setting form title
		setTitle(title);

		// ok and cancel buttons panel
		JPanel buttonPanel = new JPanel();

		String okLabel = "ok";
		if (syntaxMap.containsKey("okLabel")) {
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("ok");
			okLabel = titleEntry.getLabel();
		}
		JButton okButton = new JButton(okLabel);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// hiding current frame. State of this frame is unchanged
				setVisible(false);
				// rendering output of form1
				Form1Output from1Output = new Form1Output(formGenerator);
				from1Output.setVisible(true);
				from1Output.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				Main.centreWindow(from1Output);
				Form1Window.this.main.formGenerator = formGenerator;
				printForm1();
			}
		});

		String cancelLabel = "cancel";
		if (syntaxMap.containsKey("cancel")) {
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("cancel");
			cancelLabel = titleEntry.getLabel();
		}
		JButton cancelButton = new JButton(cancelLabel);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// user presses cancel button
				buttonCancelPressed();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		// adding each component to the panel
		GridBagConstraints constraints = new GridBagConstraints();
		for (Option option : formGenerator.getForm1().getListOptions()
				.getOption()) {
			addComponent(option, constraints, panel);
		}
	}

	public void buttonCancelPressed() {
		//rolling back to the initial opening state of this form
		formGenerator = (FormGenerator) tempFormGenerator.clone();	
		Form1Window.this.main.formGenerator = formGenerator;
		// hiding this window
		this.setVisible(false);
	}

	/*
	 * This method returns options for combo box / spinner
	 */
	public List<String> getOptions(String name) {
		List<String> options = new ArrayList<String>();
		Form1 form1 = formGenerator.getForm1();
		for (Option option : form1.getListOptions().getOption()) {
			options = getOptionsAndSub(option, name);
			if (options.size() > 0) {
				return options;
			}
		}
		return options;
	}

	/*
	 * This method checks sub options to get options for combo box / spinner
	 */
	public List<String> getOptionsAndSub(Option option, String name) {
		List<String> options = new ArrayList<String>();
		if (option.getName().equals(name)) {
			for (DropDownOption dropDownOption : option.getDropdownOption()) {
				// this is a drop down option
				for (DropDownOptionElement dropDownOptionElement : dropDownOption
						.getOption()) {
					//adding variable as dropdown element from variable list
					if (dropDownOptionElement.getAs().equals("showvars")) 
					{
						for(int counter = 0 ; counter < variables.length ; counter++)
						{
							options.add(variables[counter]);
						}
					} 
					//adding dropdown element defined in xml file					
					else {
						String dropDownElementText = dropDownOptionElement
								.getAs();
						if (syntaxMap.containsKey(dropDownElementText)) {
							languageEntry = (LanguageEntry) syntaxMap
									.get(dropDownElementText);
							dropDownElementText = languageEntry.getLabel();
						}
						options.add(dropDownElementText);
					}
				}
			}
			for (SpinnerOption spinnerOption : option.getSpinnerOption()) {
				// this is a spinner option
				int min = 0;
				int max = 0;
				boolean hasShowVars = false;
				for (SpinnerOptionElement spinnerOptionElement : spinnerOption
						.getOption()) {
					if (spinnerOptionElement.getMin() > 0) {
						min = spinnerOptionElement.getMin();
					}
					if (spinnerOptionElement.getMax() > 0) {
						max = spinnerOptionElement.getMax();
					}
					if(spinnerOptionElement.getAs()!= null && spinnerOptionElement.getAs().equals("showvars"))
					{
						hasShowVars = true;
					}
				}
				for (int i = min; i <= max; i++) {
					options.add(i + "");
				}
				//adding variable as spinner element from variable list
				if(hasShowVars)
				{
					for(int counter = 0 ; counter < variables.length ; counter++)
					{
						options.add(variables[counter]);
					}
				}							
			}
			return options;
		}
		// checking sup options
		else if (option.getListSubOptions().size() > 0) {
			for (ListSubOptions listSuboption : option.getListSubOptions()) {
				for (Option subOption : listSuboption.getSubOption()) {
					if (subOption.getName().equals(name)) {
						return getOptionsAndSub(subOption, name);
					}
				}
			}
		}
		return options;
	}

	/*
	 * This method adds an option to the panel
	 */
	public void addComponent(final Option option,
			final GridBagConstraints constraints, final JPanel panel) {

		// retrieving option properties
		String type = option.getType();
		String name = option.getName();
		String labelText = option.getLabel();
		String tooltip = option.getTooltip();
		String defaultOption = option.getDefaultOption();

		String leftComponentText = labelText;
		String tooltipText = tooltip;
		if (syntaxMap.containsKey(name)) {
			languageEntry = (LanguageEntry) syntaxMap.get(name);
			leftComponentText = languageEntry.getLabel();
			tooltipText = languageEntry.getTooltip();
		}
		String defaultOptionText = defaultOption;
		if (syntaxMap.containsKey(defaultOptionText)) {
			languageEntry = (LanguageEntry) syntaxMap.get(defaultOptionText);
			defaultOptionText = languageEntry.getLabel();
		}

		JLabel leftComponent = new JLabel(leftComponentText);
		leftComponent.setName(name + "Label");

		Component rightComponent = null;

		// option's type is text
		if (type.equalsIgnoreCase("text")) {
			JTextField textField = new JTextField(defaultOption);
			textField.setToolTipText(tooltipText);
			textField.setName(name);
			textField.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// current text is stored in option object
					option.setDefaultOption(((JTextField) e.getSource())
							.getText());
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}
			});
			rightComponent = textField;
		}
		// option's type is dropdown / spinner
		else if (type.equalsIgnoreCase("dropdown")
				|| type.equalsIgnoreCase("spinner")) {

			// getting dropdown values of this option
			JComboBox comboBox = new JComboBox(getOptions(name).toArray());
			comboBox.setName(name);
			comboBox.setToolTipText(tooltipText);
			comboBox.setSelectedItem(defaultOptionText);
			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// dropdown / spinner selected item is changed
					JComboBox combo = (JComboBox) e.getSource();
					if (combo.getSelectedItem() != null) {
						String selectedItemName = combo.getSelectedItem()
								.toString();
						if (syntaxMapLabelToName.containsKey(selectedItemName)) {
							selectedItemName = (String) syntaxMapLabelToName
									.get(selectedItemName);
						}
						// updating selected item in option object
						option.setDefaultOption(selectedItemName);
						// removing all components from this panel
						panel.removeAll();
						panel.validate();
						panel.repaint();
						// adding components again
						for (Option option : formGenerator.getForm1()
								.getListOptions().getOption()) {
							addComponent(option, constraints, panel);
						}
					}
				}
			});
			rightComponent = comboBox;
		}
		// option's type is checkbox
		else if (type.equalsIgnoreCase("check")) {
			JCheckBox checkBox = new JCheckBox();
			checkBox.setToolTipText(tooltipText);
			checkBox.setName(name);
			if (defaultOption.equals("checked")) {
				checkBox.setSelected(true);
			}

			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					AbstractButton abstractButton = (AbstractButton) e
							.getSource();

					//updating option object
					boolean selected = abstractButton.getModel().isSelected();
					if (((JCheckBox) e.getSource()).isSelected()) {
						option.setDefaultOption("checked");
						panel.removeAll();
					} else {
						option.setDefaultOption("unchecked");
						panel.removeAll();
					}

					// removing all components from this panel
					panel.removeAll();
					panel.validate();
					panel.repaint();
					// adding components again
					for (Option option : formGenerator.getForm1()
							.getListOptions().getOption()) {
						addComponent(option, constraints, panel);
					}
				}
			});
			rightComponent = checkBox;
		}
		//adding this option to the panel
		if (leftComponent != null && rightComponent != null) {

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = getRow();
			panel.add(new JLabel("	"), constraints);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 1;
			constraints.gridy = getRow();
			panel.add(new JLabel("	"), constraints);

			setRow(getRow() + 1);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = getRow();
			panel.add(leftComponent, constraints);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 1;
			constraints.gridy = getRow();
			panel.add(rightComponent, constraints);

			setRow(getRow() + 1);
		}
		// if an option has sub option then we are adding sub option to the panel
		if (option.getListSubOptions().size() > 0) {
			for (ListSubOptions listSuboption : option.getListSubOptions()) {
				if (listSuboption.getName().equals(defaultOption)) {
					for (Option subOption : listSuboption.getSubOption()) {
						addComponent(subOption, constraints, panel);
					}
				}
			}
		}
		pack();
	}
	
	public void printForm1()
	{
		String tBlockName [] = new String[8];
		int tBlockNameIndex = 0;
		String tBlockDisplayBuilder [] = new String[8];
		int tBlockDisplayBuilderIndex = 0;
		String tBlockHelpBuilder [] = new String[8];
		int tBlockHelpBuilderIndex = 0;
		for(Option option : formGenerator.getForm1().getListOptions().getOption())
		{
			tBlockName [tBlockNameIndex++] = option.getName();
			tBlockDisplayBuilder [tBlockDisplayBuilderIndex++] = option.getType();
			tBlockHelpBuilder [tBlockHelpBuilderIndex++] = option.getDefaultOption();
			if (option.getType().equalsIgnoreCase("dropdown") || option.getType().equalsIgnoreCase("spinner")) {
				System.out.println("list of available options for "+option.getName()+Arrays.toString(getOptions(option.getName()).toArray()));
			}
			if (option.getListSubOptions().size() > 0) {
				for (ListSubOptions listSuboption : option.getListSubOptions()) {
					if (listSuboption.getName().equals(option.getDefaultOption())) {
						for (Option subOption : listSuboption.getSubOption()) {
							tBlockName [tBlockNameIndex++] = subOption.getName();
							tBlockDisplayBuilder [tBlockDisplayBuilderIndex++] = subOption.getType();
							tBlockHelpBuilder [tBlockHelpBuilderIndex++] = subOption.getDefaultOption();
							if (subOption.getType().equalsIgnoreCase("dropdown") || subOption.getType().equalsIgnoreCase("spinner")) {
								System.out.println("list of available options for "+subOption.getName()+Arrays.toString(getOptions(subOption.getName()).toArray()));
							}
						}
					}
				}
			}
		}
		System.out.println(Arrays.toString(tBlockName));
		System.out.println(Arrays.toString(tBlockDisplayBuilder));
		System.out.println(Arrays.toString(tBlockHelpBuilder));
	}
}
