import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.forms.Form1;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOption;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.listoptions.Option;
import org.forms.form1.spinner.SpinnerOption;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

public class Form1Window extends JFrame {

	private JTextField textFieldLotReduction;
	private FormGenerator formGenerator;
	private JPanel contentPane;
	private JPanel panel;
	private int row = 0;
	private boolean blockComboChangeEvent = false;
	private Hashtable syntaxMap;
	private LanguageEntry languageEntry;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public FormGenerator getFormGenerator()
	{
		return this.formGenerator;
	}

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Form1Window frame = new Form1Window("English"); 
					Form1Window frame = new Form1Window("Francais");
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
	public Form1Window(String language) 
	{
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm1();
		
		showForm1();
	}
	
	public void showForm1()
	{
		syntaxMap = formGenerator.getSyntaxMapForm1();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel(new GridBagLayout());

		contentPane.setPreferredSize(new Dimension(500, 250));

		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		String title = "title";
		if(syntaxMap.containsKey("title"))
		{
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get(title);
			title = titleEntry.getLabel();
		}
		//setting form title
		setTitle(title);
		

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
				Form1Output from1Output = new Form1Output(formGenerator);
				from1Output.setVisible(true);
				from1Output.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Main.centreWindow(from1Output);
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
				disposePanel();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		//adding each component to the panel
		GridBagConstraints constraints = new GridBagConstraints();
		for (Option option : formGenerator.getForm1().getListOptions()
				.getOption()) {
			addComponent(option, constraints, panel);
		}
	}

	public void disposePanel() {
		this.dispose();
	}

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

	/*
	 * This method adds an option to the panel
	 * */
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
						
						for (Option option : formGenerator.getForm1()
								.getListOptions().getOption()) {
							
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
	}
	
	/*
	 * This method updates sub option of an option if item is changed of selected option
	 * */
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
