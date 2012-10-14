import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;

import org.forms.Form1;
import org.forms.Form2;
import org.forms.FormGenerator;
import org.forms.form2.Attribute;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.dropdown.DropDownOptionElement;
import org.forms.form2.parameters.Option;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.form2.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

import com.sun.java.swing.plaf.windows.resources.windows;

public class Form2Window extends JFrame {

	private JPanel contentPane;
	JPanel leftCardPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	JPanel rightCardPanel;

	private JScrollPane scrollBar;

	private GroupLayout rightLayout;

	private FormGenerator formGenerator;
	private int heighestNumberOfComponent = 1;
	private int leftComponents = 1;
	private int rightComponents = 1;

	private JComboBox comboBoxOptionLeft;
	private JComboBox comboBoxComparison;
	private JComboBox comboBoxOptionRight;
	
	private Hashtable syntaxMap;
	private LanguageEntry languageEntry;
	private Attribute currentSelectedLeftAttribute;
	private Attribute currentSelectedRightAttribute;
	private String currentPanel = "";
	private boolean blockComboChangeEvent = false;
	private int row = 0;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form2Window frame = new Form2Window("Francais");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Form2Window(String language) {
		
		
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm2();

		syntaxMap = formGenerator.getSyntaxMapForm2();

		String title = "title";
		if(syntaxMap.containsKey(title))
		{
			languageEntry = (LanguageEntry) syntaxMap.get(title);
			title = languageEntry.getLabel();
		}
		setTitle(title);

		// setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		addComponentListener(new ComponentAdapter(){
		    @Override
		    public void componentResized(ComponentEvent e) {
		    	int width = getSize().width;
		    	int height = getSize().height;
		    	
		    	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		    	int screenWidth = dimension.width;
		    	int screenHeight = dimension.height;
		    	
		    	int currentHeight = height > screenHeight ? screenHeight:height;
		    	int currentWidth = width > screenWidth ? screenWidth:width;
		    	
		        setSize(new Dimension(currentWidth, currentHeight));
		        
		        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
			    int y = (int) ((dimension.getHeight() - getHeight()) / 2);
			    setLocation(x, y);
		        
		        super.componentResized(e);
		    }

		});
		JPanel buttonPanel = new JPanel();

		String okLabel = "ok";
		if(syntaxMap.containsKey("okLabel"))
		{
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("ok");
			okLabel = titleEntry.getLabel();
		}
		JButton okButton = new JButton(okLabel);

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Form2Output from2Output = new Form2Output(formGenerator.getForm2(), comboBoxOptionLeft, comboBoxComparison, comboBoxOptionRight, formGenerator);
				from2Output.setVisible(true);
				from2Output.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Main.centreWindow(from2Output);
			}
		});

		String cancelLabel = "cancel";
		if(syntaxMap.containsKey("cancel"))
		{
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("cancel");
			cancelLabel = titleEntry.getLabel();
		}	
		JButton cancelButton = new JButton(cancelLabel);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				((JFrame) ((JButton) e.getSource()).getTopLevelAncestor())
						.dispose();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		// contentPane.add(panel, BorderLayout.CENTER);

		// Creating group layout
		// GroupLayout layout = new GroupLayout(panel);
		// setting the grouplaoyt in content pane
		// panel.setLayout(new GridLayout(0, 3, 30, 30));
		// contentPane.setPreferredSize(new Dimension(500, 200));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

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

		comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		leftPanel.add(comboBoxOptionLeft, BorderLayout.PAGE_START);
		leftCardPanel = new JPanel(new CardLayout());
		leftPanel.add(leftCardPanel, BorderLayout.CENTER);

		setLeftComponent(comboBoxOptionLeft.getSelectedItem().toString());

		comboBoxOptionLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLeftComponent(comboBoxOptionLeft.getSelectedItem()
						.toString());
			}
		});

		comboBoxComparison = new JComboBox(getOperators().toArray());
		comparisonPanel.add(comboBoxComparison, BorderLayout.PAGE_START);
		comboBoxComparison.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		comboBoxOptionRight = new JComboBox(getRightAttributes().toArray());
		rightPanel.add(comboBoxOptionRight, BorderLayout.PAGE_START);
		rightCardPanel = new JPanel(new CardLayout());
		rightPanel.add(rightCardPanel, BorderLayout.CENTER);

		setRightComponent(comboBoxOptionRight.getSelectedItem().toString());
		comboBoxOptionRight.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setRightComponent(comboBoxOptionRight.getSelectedItem()
						.toString());
			}
		});
		this.pack();
	}

	public List<String> getOperators() {
		List<String> operators = new ArrayList<String>();

		Form2 form2 = formGenerator.getForm2();

		for (Operand oprand : form2.getComparison().getOperators().getOp()) {
			operators.add(oprand.getName());
		}

		return operators;
	}

	public List<String> getLeftAttributes() {
		List<String> attributes = new ArrayList<String>();

		Form2 form2 = formGenerator.getForm2();

		for (Attribute attribute : form2.getComparison().getAttributesleft()
				.getAttribute()) {
			attributes.add(attribute.getName());
		}

		return attributes;
	}

	public List<String> getRightAttributes() {
		List<String> attributes = new ArrayList<String>();

		Form2 form2 = formGenerator.getForm2();

		for (Attribute attribute : form2.getComparison().getAttributesright()
				.getAttribute()) {
			attributes.add(attribute.getName());
		}

		return attributes;
	}

	private void setLeftComponent(String cardName) {
		leftCardPanel.removeAll();
		leftCardPanel.validate();
		leftCardPanel.repaint();

		
		JPanel cp = new JPanel(new GridBagLayout());
		cp.setName("LeftPanel");
		leftCardPanel.add(cp, cardName + "left");
		String selectedOption = cardName;

		setRow(0);
		
		GridBagConstraints constraints = new GridBagConstraints();
		
	
		for (Attribute attribute : formGenerator.getForm2().getComparison().getAttributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if(attributeName.equals(cardName))
			{
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

	private void setRightComponent(String cardName) {
		rightCardPanel.removeAll();
		rightCardPanel.validate();
		rightCardPanel.repaint();

		JPanel cpRight = new JPanel(new GridBagLayout());	
		cpRight.setName("RightPanel");
		rightCardPanel.add(cpRight, cardName + "right");

		setRow(0);
		
		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm2().getComparison().getAttributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if(attributeName.equals(cardName))
			{
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
	
	public void addComponent(final Option option,
			GridBagConstraints constraints, final JPanel panel) {
			//retrieving option properties
			String type = option.getType();
			String name = option.getName();
			String labelText = option.getLabel();
			String tooltip = option.getTooltip();
			String defaultOption = option.getDefaultOption();

			String leftComponentText = labelText;
			String tooltipText = tooltip;
			if(syntaxMap.containsKey(name))
			{
				languageEntry = (LanguageEntry) syntaxMap.get(name);		
				leftComponentText = languageEntry.getLabel();
				tooltipText = languageEntry.getTooltip();
			}
			String defaultOptionText = defaultOption;		
			if(syntaxMap.containsKey(defaultOptionText))
			{
				languageEntry = (LanguageEntry) syntaxMap.get(defaultOptionText);
				defaultOptionText = languageEntry.getLabel();
			}
			
			JLabel leftComponent = new JLabel(leftComponentText);
			leftComponent.setName(name + "Label");

			Component rightComponent = null;

			if (type.equalsIgnoreCase("text")) 
			{
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
						// TODO Auto-generated method stub
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
						// TODO Auto-generated method stub					
						if(!blockComboChangeEvent)
						{
							JComboBox combo = (JComboBox) e.getSource();
							//JOptionPane.showMessageDialog(null, "addComponent->before option name:"+option.getName()+";option default value:"+option.getDefaultOption());
							if(combo.getSelectedItem() != null){
								//option.setDefaultOption(combo.getSelectedItem().toString());
							}
							//JOptionPane.showMessageDialog(null, "addComponent->after option name:"+option.getName()+";option default value:"+option.getDefaultOption());
							String panelName = combo.getParent().getName();
							Attribute selectedComboAttribute = null;
							if(panelName.equals("LeftPanel"))
							{
								selectedComboAttribute = currentSelectedLeftAttribute;
							}
							else if(panelName.equals("RightPanel"))
							{
								selectedComboAttribute = currentSelectedRightAttribute;
							}
							for (Option option : selectedComboAttribute.getParameters().getOption()) {
								
								comboChangeSubOptionUpdate(option, combo, panel);
							}
						}					
					}
				});
				rightComponent = comboBox;
			} else if (type.equalsIgnoreCase("check")) {
				JCheckBox checkBox = new JCheckBox();
				checkBox.setToolTipText(tooltipText);
				checkBox.setName(name);
				if (defaultOption.equals("checked")) {
					checkBox.setSelected(true);
				}
				checkBox.addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {
						// TODO Auto-generated method stub
						if (((JCheckBox) e.getSource()).isSelected()) {
							option.setDefaultOption("checked");
						} else {
							option.setDefaultOption("unchecked");
						}
					}
				});

				rightComponent = checkBox;
			}

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
			if (option.getSuboption().size() > 0) {
				for (Option subOption : option.getSuboption()) {
					if (subOption.getName().equals(defaultOption)) {
						addComponent(subOption, constraints, panel);
					}
				}
			}
			pack();
		}
	
	
	
	public void comboChangeSubOptionUpdate(Option option, JComboBox combo, JPanel panel)
	{
		String comboName = combo.getName();
		if (option.getName().equals(comboName)) {	
			if(combo.getSelectedItem() != null){
				//JOptionPane.showMessageDialog(null, "comboChangeSubOptionUpdate->1.option name:"+option.getName()+";comboselectedtext:"+combo.getSelectedItem().toString());
				option.setDefaultOption(combo.getSelectedItem().toString());
				//initializing new suboption
				Option newSubOption = null;
				JComboBox subOptionCombo = null;
				JLabel subOptionLable = null;
				String selectedComboItemText = combo.getSelectedItem().toString();
				String subOptionNameText = "";
				//finding specific suboption of this option based on suboption name and option selected item
				for (Option subOption : option.getSuboption()) {
					subOptionNameText = subOption.getName();
					if(syntaxMap.containsKey(subOptionNameText))
					{
						languageEntry = (LanguageEntry) syntaxMap.get(subOption.getName());
						subOptionNameText = languageEntry.getLabel();
					}					
					if (subOptionNameText.equals(selectedComboItemText)) 
					{
						//we have got the new suboption
						newSubOption = subOption;
						//combo.setName(option.getName());
						//option.setDefaultOption(subOption.getName());
					}
					for (Component component : panel.getComponents()) {
						if (subOption.getName().equals(component.getName())) {
							//we have detect previous suboption combo
							subOptionCombo = (JComboBox) component;
							removeComponentSubOption(subOption, panel);
						}
						if ((subOption.getName() + "Label").equals(component.getName())) {
							//we have detected previous suboption label
							subOptionLable = (JLabel) component;
						}
					}
				}

				if (newSubOption != null) 
				{
					//JOptionPane.showMessageDialog(null, "comboChangeSubOptionUpdate->2.new sub option name:"+newSubOption.getName()+";new suboption default value:"+newSubOption.getDefaultOption()+"previous subOptionCombo name:"+subOptionCombo.getName());
					if(subOptionCombo != null)
					{
						blockComboChangeEvent = true;
						//removing all elements from previous suboption combo
						subOptionCombo.removeAllItems();
						//subOptionCombo = new JComboBox();
						for (DropDownOption dropDownOption : newSubOption.getDropdownOption()) 
						{
							for (DropDownOptionElement dropDownOptionElement : dropDownOption.getOption()) 
							{
								String comboElementNameText = dropDownOptionElement.getAs();
								if(syntaxMap.containsKey(comboElementNameText))
								{
									languageEntry = (LanguageEntry) syntaxMap.get(comboElementNameText);
									comboElementNameText = languageEntry.getLabel();
								}
								//adding new combo element
								subOptionCombo.addItem(comboElementNameText);
							}
						}
						blockComboChangeEvent = false;
						//JOptionPane.showMessageDialog(null, "comboChangeSubOptionUpdate->3.previous combo is reloaded.");
						
						//updating suboption combo name
						subOptionCombo.setName(newSubOption.getName());		
						//updating suboption combo default option
						String defaultOptionText = newSubOption.getDefaultOption();
						if(syntaxMap.containsKey(defaultOptionText))
						{
							languageEntry = (LanguageEntry) syntaxMap.get(defaultOptionText);
							defaultOptionText = languageEntry.getLabel();
						}
						//JOptionPane.showMessageDialog(null, "comboChangeSubOptionUpdate->4.new sub option name:"+newSubOption.getName()+";new suboption default value:"+newSubOption.getDefaultOption());
						
						subOptionCombo.setSelectedItem(defaultOptionText);
						
						//updating suboption combo tooltip text
						String tooltipText = newSubOption.getTooltip();
						if(syntaxMap.containsKey(tooltipText))
						{
							languageEntry = (LanguageEntry) syntaxMap.get(tooltipText);
							tooltipText = languageEntry.getLabel();
						}
						subOptionCombo.setToolTipText(tooltipText);
						
						subOptionCombo.revalidate();
					}
					
					if (subOptionLable != null) {
						subOptionLable.setName(newSubOption.getName() + "Label");
						String newLabelText = newSubOption.getLabel();
						if(syntaxMap.containsKey(newLabelText))
						{
							languageEntry = (LanguageEntry) syntaxMap.get(newLabelText);		
							newLabelText = languageEntry.getLabel();
						}
						subOptionLable.setText(newLabelText);
						subOptionLable.revalidate();
					}
					
					if(subOptionCombo == null && subOptionLable == null)
					{
						GridBagConstraints constraints = new GridBagConstraints();						
						addComponent(newSubOption, constraints, panel);						
					}
										
				}				
			}			
			
		}
		else if (option.getSuboption().size() > 0) {
			for (Option subOption : option.getSuboption()) {
				comboChangeSubOptionUpdate(subOption, combo, panel);
			}
		}
	}
	
	public List<String> getOptions(String name) {
		List<String> options = new ArrayList<String>();
		Attribute currentAttribute = null;
		//JOptionPane.showMessageDialog(null, "name in getOptions:"+name);
		if(currentPanel == "LeftPanel")
		{
			currentAttribute = currentSelectedLeftAttribute;
		}
		else if(currentPanel == "RightPanel")
		{
			currentAttribute = currentSelectedRightAttribute;
		}
		if(currentAttribute != null)
		{
			for (Option option : currentAttribute.getParameters().getOption()) {
				options = getOptionsAndSub(option, name);

				if (options.size() > 0) {
					return options;
				}
			}
		}		

		return options;
	}
	
	public List<String> getOptionsAndSub(Option option, String name) {
		List<String> options = new ArrayList<String>();
		if (option.getName().equals(name)) {
			for (DropDownOption dropDownOption : option.getDropdownOption()) {
				for (DropDownOptionElement dropDownOptionElement : dropDownOption.getOption()) {
					if (dropDownOptionElement.getAs().equals("showvars")) {
						options.add("will add some variables later");
					}
					else
					{
						String dropDownElementText = dropDownOptionElement.getAs();					
						if (syntaxMap.containsKey(dropDownElementText)) {
							languageEntry = (LanguageEntry) syntaxMap.get(dropDownElementText);
							dropDownElementText = languageEntry.getLabel();						
						}
						options.add(dropDownElementText);
					}
				}
			}
			for (SpinnerOption spinnerOption : option.getSpinnerOption()) {
				int min = 0;
				int max = 0;
				for (SpinnerOptionElement spinnerOptionElement : spinnerOption
						.getOption()) {
					if (spinnerOptionElement.getMin() > 0) {
						min = spinnerOptionElement.getMin();
					}
					if (spinnerOptionElement.getMax() > 0) {
						max = spinnerOptionElement.getMax();
					}
				}
				for (int i = min; i <= max; i++) {
					options.add(i + "");
				}
				// if(spinnerOptionElement.getAs() != null &&
				// spinnerOptionElement.getAs().equals("showvars"))
				{
					options.add("will add some variables later");
				}
			}
			return options;
		} else if (option.getSuboption().size() > 0) {
			for (Option subOption : option.getSuboption()) {
				return getOptionsAndSub(subOption, name);
			}
		}

		return options;
	}
	
	public void removeComponentSubOption(Option option, JPanel panel)
	{
		JComboBox subOptionCombo = null;
		JLabel subOptionLable = null;
		for (Option subOption : option.getSuboption()) {
			for (Component component : panel.getComponents()) {
				if (subOption.getName().equals(component.getName())) {
					subOptionCombo = (JComboBox) component;
					removeComponentSubOption(subOption, panel);
				}
				if ((subOption.getName() + "Label").equals(component.getName())) {
					subOptionLable = (JLabel) component;
				}
			}
		}
		if(subOptionCombo != null && subOptionLable != null)
		{
			panel.remove(subOptionCombo);
			panel.remove(subOptionLable);
			panel.revalidate();
			panel.repaint();
		}
		
	}

}
