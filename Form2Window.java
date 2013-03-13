import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BoxLayout;
import org.forms.Form2;
import org.forms.FormGenerator;
import org.forms.form2.Attribute;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.dropdown.DropDownOptionElement;
import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.form2.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

public class Form2Window extends JFrame {

	private Main main;
	private JPanel contentPane;
	JPanel leftCardPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	JPanel rightCardPanel;

	private JScrollPane scrollBar;

	private FormGenerator formGenerator;

	private JComboBox comboBoxOptionLeft;
	private JComboBox comboBoxComparison;
	private JComboBox comboBoxOptionRight;

	private Hashtable syntaxMap;
	private Hashtable syntaxMapLabelToName;
	private LanguageEntry languageEntry;
	private Attribute currentSelectedLeftAttribute;
	private Attribute currentSelectedRightAttribute;
	private String currentPanel = "";
	private int row = 0;

	private String leftPanelSelectedItem = "";
	private String comparisonSelectedItem = "";
	private String rightPanelSelectedItem = "";

	private String tempLeftPanelSelectedItem = "";
	private String tempComparisonSelectedItem = "";
	private String tempRightPanelSelectedItem = "";
	public FormGenerator tempFormGenerator;
	
	private String[] variables;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	/*
	 * Creating the frame.
	 */
	public Form2Window(String language, String[] variables,String form2XmlName, Main main) {
		this.main = main;
		this.variables = variables;
		// initializing form2
		if(this.main.formGenerator == null)
		{
			this.main.formGenerator = new FormGenerator();
		}
		if(this.main.form2IsFirstTime)
		{
			this.main.formGenerator.generateForm2(form2XmlName);
			this.formGenerator = this.main.formGenerator;
			
			this.main.form2LeftPanelSelectedItem = (String) getLeftAttributes().toArray()[0];
        	this.main.form2ComparisonSelectedItem = (String) getOperators().toArray()[0];
        	this.main.form2RightPanelSelectedItem = (String) getRightAttributes().toArray()[0];
		}		
		else
		{
			this.formGenerator = this.main.formGenerator;			
		}
		this.leftPanelSelectedItem = this.main.form2LeftPanelSelectedItem;
		this.comparisonSelectedItem = this.main.form2ComparisonSelectedItem;
		this.rightPanelSelectedItem = this.main.form2RightPanelSelectedItem;
		//initializing left panel, comparison panel and right panel initial selected item
		/*leftPanelSelectedItem = (String) getLeftAttributes().toArray()[0];
		comparisonSelectedItem = (String) getOperators().toArray()[0];
		rightPanelSelectedItem = (String) getRightAttributes().toArray()[0];*/

		showForm2(language);
	}

	/*
	 * Rendering form2
	 */
	public void showForm2(String language) {
		//cloning initial object content to a temporary object
		tempFormGenerator = (FormGenerator) formGenerator.clone();
		tempLeftPanelSelectedItem = leftPanelSelectedItem;
		tempComparisonSelectedItem = comparisonSelectedItem;
		tempRightPanelSelectedItem = rightPanelSelectedItem;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// setting language to render form2
		formGenerator.setLanguage(language);
		formGenerator.updateLanguageMapForm2();
		// retrieving language info which is stored in a hash table
		syntaxMap = formGenerator.getSyntaxMapForm2();
		syntaxMapLabelToName = formGenerator.getSyntaxMapLabelToNameForm2();

		String title = formGenerator.getForm2().getTitle();
		if (syntaxMap.containsKey(title)) {
			languageEntry = (LanguageEntry) syntaxMap.get(title);
			title = languageEntry.getLabel();
		}
		setTitle(title);
		// panel to render form2
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	//rolling back to the initial opening state of this form
            	formGenerator = (FormGenerator) tempFormGenerator.clone();
            	leftPanelSelectedItem = tempLeftPanelSelectedItem;
            	comparisonSelectedItem = tempComparisonSelectedItem;
            	rightPanelSelectedItem = tempRightPanelSelectedItem;
            	
            	Form2Window.this.main.formGenerator = formGenerator;
            	Form2Window.this.main.form2LeftPanelSelectedItem = leftPanelSelectedItem;
            	Form2Window.this.main.form2ComparisonSelectedItem = comparisonSelectedItem;
            	Form2Window.this.main.form2RightPanelSelectedItem = rightPanelSelectedItem;
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
		JPanel buttonPanel = new JPanel();
		// ok and cancel buttons panel
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
				// rendering output of form2
				Form2Output from2Output = new Form2Output(formGenerator
						.getForm2(), comboBoxOptionLeft, comboBoxComparison,
						comboBoxOptionRight, formGenerator);
				from2Output.setVisible(true);
				from2Output.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				Main.centreWindow(from2Output);
				
				Form2Window.this.main.formGenerator = formGenerator;
				Form2Window.this.main.form2LeftPanelSelectedItem = leftPanelSelectedItem;
            	Form2Window.this.main.form2ComparisonSelectedItem = comparisonSelectedItem;
            	Form2Window.this.main.form2RightPanelSelectedItem = rightPanelSelectedItem;
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
				// TODO Auto-generated method stub
				// initializing form2
				/*formGenerator = new FormGenerator();
				formGenerator.generateForm2();
				leftPanelSelectedItem = (String) getLeftAttributes().toArray()[0];
				comparisonSelectedItem = (String) getOperators().toArray()[0];
				rightPanelSelectedItem = (String) getRightAttributes().toArray()[0];*/
				// hiding this window
				buttonCancelPressed();
			}
		});
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		// adding vertical and horizontal scroll bars
		scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		// set left container into left side
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		// set left container into center side
		JPanel comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new BorderLayout());

		// set left container into right side
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());

		// adding 3 panel in the container
		panel.add(leftPanel);
		panel.add(comparisonPanel);
		panel.add(rightPanel);

		//left panel selected item combo box
		comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		leftPanel.add(comboBoxOptionLeft, BorderLayout.PAGE_START);
		leftCardPanel = new JPanel(new CardLayout());
		leftPanel.add(leftCardPanel, BorderLayout.CENTER);
		comboBoxOptionLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//updating left panel selected item
				leftPanelSelectedItem = comboBoxOptionLeft.getSelectedItem()
						.toString();
				//rendering left panel
				setLeftComponent(comboBoxOptionLeft.getSelectedItem()
						.toString());
			}
		});
		//initial left panel item is selected
		comboBoxOptionLeft.setSelectedItem(leftPanelSelectedItem);

		//comparison panel selected item combo box
		comboBoxComparison = new JComboBox(getOperators().toArray());
		comparisonPanel.add(comboBoxComparison, BorderLayout.PAGE_START);
		comboBoxComparison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//updating comparison panel selected item
				comparisonSelectedItem = comboBoxComparison.getSelectedItem()
						.toString();
			}
		});
		//initial comparison panel item is selected
		comboBoxComparison.setSelectedItem(comparisonSelectedItem);

		//right panel selected item combo box
		comboBoxOptionRight = new JComboBox(getRightAttributes().toArray());
		rightPanel.add(comboBoxOptionRight, BorderLayout.PAGE_START);
		rightCardPanel = new JPanel(new CardLayout());
		rightPanel.add(rightCardPanel, BorderLayout.CENTER);
		comboBoxOptionRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//updating right panel selected item
				rightPanelSelectedItem = comboBoxOptionRight.getSelectedItem()
						.toString();
				//rendering right panel				
				setRightComponent(comboBoxOptionRight.getSelectedItem()
						.toString());
			}
		});
		//initial right panel item is selected
		comboBoxOptionRight.setSelectedItem(rightPanelSelectedItem);
		this.pack();
	}
	
	public void buttonCancelPressed() {
		//rolling back to the initial opening state of this form
		formGenerator = (FormGenerator) tempFormGenerator.clone();	
		leftPanelSelectedItem = tempLeftPanelSelectedItem;
    	comparisonSelectedItem = tempComparisonSelectedItem;
    	rightPanelSelectedItem = tempRightPanelSelectedItem;
    	
    	Form2Window.this.main.formGenerator = formGenerator;
    	Form2Window.this.main.form2LeftPanelSelectedItem = leftPanelSelectedItem;
    	Form2Window.this.main.form2ComparisonSelectedItem = comparisonSelectedItem;
    	Form2Window.this.main.form2RightPanelSelectedItem = rightPanelSelectedItem;
		// hiding this window
		this.setVisible(false);
	}

	/*
	 * This method returns comparison panel operators
	 * */
	public List<String> getOperators() {
		List<String> operators = new ArrayList<String>();
		Form2 form2 = formGenerator.getForm2();
		for (Operand oprand : form2.getComparison().getOperators().getOp()) {
			operators.add(oprand.getName());
		}
		return operators;
	}

	/*
	 * This method returns left panel item list
	 * */
	public List<String> getLeftAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form2 form2 = formGenerator.getForm2();
		for (Attribute attribute : form2.getComparison().getAttributesleft()
				.getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method returns right panel item list
	 * */
	public List<String> getRightAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form2 form2 = formGenerator.getForm2();
		for (Attribute attribute : form2.getComparison().getAttributesright()
				.getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method renders left panel
	 * */
	private void setLeftComponent(String cardName) {
		leftCardPanel.removeAll();
		leftCardPanel.validate();
		leftCardPanel.repaint();

		JPanel cp = new JPanel(new GridBagLayout());
		cp.setName("LeftPanel");
		leftCardPanel.add(cp, cardName + "left");
		
		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();

		for (Attribute attribute : formGenerator.getForm2().getComparison()
				.getAttributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "LeftPanel";
				currentSelectedLeftAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cp);
				}
			}
		}

		leftCardPanel.validate();
		leftCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method renders right panel
	 */
	private void setRightComponent(String cardName) {
		rightCardPanel.removeAll();
		rightCardPanel.validate();
		rightCardPanel.repaint();

		JPanel cpRight = new JPanel(new GridBagLayout());
		cpRight.setName("RightPanel");
		rightCardPanel.add(cpRight, cardName + "right");

		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm2().getComparison()
				.getAttributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "RightPanel";
				currentSelectedRightAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cpRight);
				}
			}
		}

		rightCardPanel.validate();
		rightCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method adds an option to the panel
	 */
	public void addComponent(final Option option,
			GridBagConstraints constraints, final JPanel panel) {
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

			JComboBox comboBox = new JComboBox(getOptions(name).toArray());
			comboBox.setName(name);
			comboBox.setToolTipText(tooltipText);
			comboBox.setSelectedItem(defaultOptionText);
			option.setDefaultOption(defaultOptionText);
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

						//updating left panel
						if (panel.getName().equals("LeftPanel")) {
							setLeftComponent(comboBoxOptionLeft
									.getSelectedItem().toString());
						} 
						//updating right panel
						else if (panel.getName().equals("RightPanel")) {
							setRightComponent(comboBoxOptionRight
									.getSelectedItem().toString());
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
					//updating left panel
					if (panel.getName().equals("LeftPanel")) {
						setLeftComponent(comboBoxOptionLeft.getSelectedItem()
								.toString());
					} 
					//updating right panel
					else if (panel.getName().equals("RightPanel")) {
						setRightComponent(comboBoxOptionRight.getSelectedItem()
								.toString());
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

	/*
	 * This method returns options for combo box / spinner
	 */
	public List<String> getOptions(String name) {
		List<String> options = new ArrayList<String>();
		Attribute currentAttribute = null;
		if (currentPanel == "LeftPanel") {
			currentAttribute = currentSelectedLeftAttribute;
		} else if (currentPanel == "RightPanel") {
			currentAttribute = currentSelectedRightAttribute;
		}
		if (currentAttribute != null) {
			for (Option option : currentAttribute.getParameters().getOption()) {
				options = getOptionsAndSub(option, name);

				if (options.size() > 0) {
					return options;
				}
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
}
