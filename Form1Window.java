import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;

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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1Window frame = new Form1Window("English");
					//Form1Window frame = new Form1Window("Francais");
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//-----------------Adding group layout-------------------//
		
		//Creating group layout
		GroupLayout layout = new GroupLayout(contentPane);
		//setting the grouplaoyt in content pane
		contentPane.setLayout(layout);
		contentPane.setPreferredSize(new Dimension(500, 400));
		
		//setting gaps in container
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		//horizontal and vertical group
		ParallelGroup horizontalGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		layout.setHorizontalGroup(horizontalGroup);
		
		SequentialGroup horizontalSequentialGroup = layout.createSequentialGroup();
		horizontalGroup.addGroup(horizontalSequentialGroup);
		
		ParallelGroup horizontalParallelGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
		horizontalSequentialGroup.addGroup(horizontalParallelGroup);
		
		
		ParallelGroup verticalGroup = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
		layout.setVerticalGroup(verticalGroup);
		
		SequentialGroup verticalSequentialGroup = layout.createSequentialGroup();
		verticalGroup.addGroup(verticalSequentialGroup);
		
		//-----------------Adding group layout-------------------//
				
		
		//setting language in formgenerator
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm1();
		
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		
		LanguageEntry titleEntry = (LanguageEntry)syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);
		
		for(Option option : formGenerator.getForm1().getListOptions().getOption())
		{
			addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, option);
		}
		
		this.pack();
	}
	
	public List<String> getOptions(String name)
	{
		List<String> options = new ArrayList<String>();
		
		Form1 form1 = formGenerator.getForm1();
		
		
		for(Option option:form1.getListOptions().getOption())
		{
			options = getOptionsAndSub(option, name);
			
			if(options.size() > 0)
			{
				return options;
			}
		}
		
		return options;
	}
	
	public List<String> getOptionsAndSub(Option option, String name)
	{
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		LanguageEntry languageEntry = (LanguageEntry)syntaxMap.get(name);
		
		List<String> options = new ArrayList<String>();
		if(option.getName().equals(name))
		{
			for(DropDownOption dropDownOption: option.getDropdownOption())
			{
				for(DropDownOptionElement dropDownOptionElement: dropDownOption.getOption())
				{
					if(syntaxMap.containsKey(dropDownOptionElement.getAs()))
					{
						languageEntry = (LanguageEntry)syntaxMap.get(dropDownOptionElement.getAs());
						options.add(languageEntry.getLabel());
					}
					if(dropDownOptionElement.getAs().equals("showvars"))
					{
						options.add("will add some variables later");
					}
				}
			}
			for(SpinnerOption spinnerOption: option.getSpinnerOption())
			{
				for(SpinnerOptionElement spinnerOptionElement:spinnerOption.getOption())
				{
					int min = spinnerOptionElement.getMin();
					int max = spinnerOptionElement.getMax();
					
					for(int i = min; i <= max; i++)
					{
						options.add(i + "");
					}
					if(spinnerOptionElement.getAs() != null && syntaxMap.containsKey(spinnerOptionElement.getAs()))
					{
						languageEntry = (LanguageEntry)syntaxMap.get(spinnerOptionElement.getAs());
						options.add(languageEntry.getLabel());
					}
					if(spinnerOptionElement.getAs() != null && spinnerOptionElement.getAs().equals("showvars"))
					{
						options.add("will add some variables later");
					}
					
				}
			}
			return options;
		}
		else if(option.getSuboption().size() > 0)
		{
			for(Option subOption: option.getSuboption())
			{
				return getOptionsAndSub(subOption, name);
			}
		}
		
		return options;
	}
	
	private void addComponent(GroupLayout layout, ParallelGroup horizontalParallelGroup, 
			SequentialGroup verticalSequentialGroup, Option option)
	{
		
		String type = option.getType();
		String name = option.getName();
		String labelText = option.getLabel();
		String tooltip = option.getTooltip();
		String defaultOption = option.getDefaultOption();
		
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		
		LanguageEntry languageEntry = (LanguageEntry)syntaxMap.get(name);
		JLabel leftComponent = new JLabel(languageEntry.getLabel());
		
		Component rightComponent = null;
		
		if( type.equalsIgnoreCase("text"))
		{
			JTextField textField = new JTextField(defaultOption);
			textField.setToolTipText(tooltip);
			
			rightComponent = textField;
		}
		else if( type.equalsIgnoreCase("dropdown") || type.equalsIgnoreCase("spinner"))
		{
			JComboBox comboBox = new JComboBox(getOptions(name).toArray());
			comboBox.setName(name);
			comboBox.setToolTipText(languageEntry.getTooltip());
			comboBox.setSelectedItem(defaultOption);
			comboBox.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					JComboBox combo = (JComboBox)e.getSource();
					String comboName = combo.getName();
					
					for(Option option : formGenerator.getForm1().getListOptions().getOption())
					{
						if(option.getName().equals(comboName))
						{
							Option newSubOption = null;
							JComboBox subOptionCombo = null;
							for(Option subOption: option.getSuboption())
							{
								if(subOption.getLabel().equals(combo.getSelectedItem().toString()))
								{
									newSubOption = subOption;
									combo.setName(option.getName());
								}
								for (Component component:contentPane.getComponents())
								{
									if(subOption.getName().equals(component.getName()))
									{
										subOptionCombo = (JComboBox) component;
									}
									
								}
							}
							
							
							if(newSubOption != null)
							{
								subOptionCombo.removeAllItems();
								
								for(DropDownOption dropDownOption: newSubOption.getDropdownOption())
								{
									for(DropDownOptionElement dropDownOptionElement:dropDownOption.getOption())
									{
										subOptionCombo.addItem(dropDownOptionElement.getAs());
									}
								}
								
								subOptionCombo.setName(newSubOption.getName());
								subOptionCombo.setToolTipText(newSubOption.getTooltip());
								subOptionCombo.setSelectedItem(newSubOption.getDefaultOption());
								
								subOptionCombo.revalidate();
							}
						}
						
					}
				}
			});
			rightComponent = comboBox;
		}
		else if( type.equalsIgnoreCase("check"))
		{
			JCheckBox checkBox = new JCheckBox();
			checkBox.setToolTipText(tooltip);
			
			if(defaultOption.equals("checked"))
			{
				checkBox.setSelected(true);
			}
			
			rightComponent = checkBox;
		}
		
		
		if(leftComponent != null && rightComponent != null)
		{
		
			SequentialGroup componentSequential2 = layout.createSequentialGroup();
			
			componentSequential2.addComponent(leftComponent);
			componentSequential2.addComponent(rightComponent, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE);
			
			horizontalParallelGroup.addGroup(componentSequential2);
			
			
			ParallelGroup verticlaParallelGroupFinal2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
			verticalSequentialGroup.addGroup(verticlaParallelGroupFinal2);
			verticlaParallelGroupFinal2.addComponent(leftComponent);
			verticlaParallelGroupFinal2.addComponent(rightComponent);
		}
		
		if(option.getSuboption().size() > 0)
		{
			for(Option subOption: option.getSuboption())
			{
				if(subOption.getName().equals(defaultOption))
				{
					addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, subOption);
				}
			}
		}
	}
}
