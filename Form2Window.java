import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.forms.Form2;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.form2.Attribute;
import org.forms.form2.Attributes;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.languages.LanguageEntry;


public class Form2Window extends JFrame {

	private JPanel contentPane;
	private FormGenerator formGenerator;
	private int heighestNumberOfComponent = 1;
	private int leftComponents = 1;
	private int rightComponents = 1;
	
	private JComboBox comboBoxOptionLeft;
	private JComboBox comboBoxComparison;
	private JComboBox comboBoxOptionRight ;
	private JPanel panelLeft;
	private JPanel panelRight;
	
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
	public Form2Window(String language) 
	{
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm2();
		
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm2();
		
		LanguageEntry titleEntry = (LanguageEntry)syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);
		
		initComponent();
		
		setLeftComponent(panelLeft);
		setRightComponent(panelRight);
		
		
		int frameWidth = 800;
		heighestNumberOfComponent = leftComponents > rightComponents? leftComponents :rightComponents;
	    int frameHeight = heighestNumberOfComponent * 50 + 100;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private void initComponent()
	{
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 3));
		
		panelLeft = new JPanel();
		contentPane.add(panelLeft);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter);
		
		panelRight = new JPanel();
		contentPane.add(panelRight);
		
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.Y_AXIS));
		panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.Y_AXIS));
		
		
		comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		comboBoxOptionLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				setLeftComponent(panelLeft);
			}
		});
		JPanel comboBoxOptionLeftPanel = new JPanel();
		comboBoxOptionLeftPanel.add(comboBoxOptionLeft);
		panelLeft.add(comboBoxOptionLeftPanel);
		
		comboBoxComparison = new JComboBox(getOperators().toArray());
		JPanel comboBoxOptionCenterPanel = new JPanel();
		comboBoxOptionCenterPanel.add(comboBoxComparison);
		panelCenter.add(comboBoxOptionCenterPanel);
		
		comboBoxOptionRight = new JComboBox(getRightAttributes().toArray());
		comboBoxOptionRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setRightComponent(panelRight);
			}
		});
		JPanel comboBoxOptionRightPanel = new JPanel();
		comboBoxOptionRightPanel.add(comboBoxOptionRight);
		panelRight.add(comboBoxOptionRightPanel);
		
	}
	
	
	private void setRightComponent(JPanel panelRight) 
	{
		// TODO Auto-generated method stub
		
		String selectedOption = "";
		
		if(comboBoxOptionRight.getSelectedItem() != null)
		{
			selectedOption = comboBoxOptionRight.getSelectedItem().toString();
		}
		
		for(Attribute attribute: formGenerator.getForm2().getComparison().getAttributesright().getAttribute())
		{
			if(selectedOption.equals(attribute.getName()))
			{
				rightComponents = attribute.getParameters().getDropdownOption().size() + attribute.getParameters().getSpinnerOption().size();
				
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					for(DropDownOptionElement dropDownOptionElement: dropDownOption.getOption())
					{
						comboSource.add(dropDownOptionElement.getAs());
					}
					
					JComboBox comboBox = new JComboBox(comboSource.toArray());
					comboBox.setToolTipText(dropDownOption.getTooltip());
					JPanel attributePanel = new JPanel();
					JLabel attributeLabel = new JLabel(dropDownOption.getName());
					attributePanel.add(attributeLabel);
					attributePanel.add(comboBox);
					panelRight.add(attributePanel);
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						int min = spinnerOptionElement.getMin();
						int max = spinnerOptionElement.getMax();
						
						for(int i = min; i <= max; i ++)
						{
							comboSource.add(i + "");
						}
						comboSource.add(spinnerOptionElement.getAs());
					}
					
					JComboBox comboBox = new JComboBox(comboSource.toArray());
					comboBox.setToolTipText(spinnerOption.getTooltip());
					JPanel attributePanel = new JPanel();
					JLabel attributeLabel = new JLabel(spinnerOption.getName());
					attributePanel.add(attributeLabel);
					attributePanel.add(comboBox);
					panelRight.add(attributePanel);
				}
			}
		}
		int frameWidth = 800;
		heighestNumberOfComponent = leftComponents > rightComponents? leftComponents :rightComponents;
	    int frameHeight = heighestNumberOfComponent * 50 + 100;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	    this.doLayout();
		this.invalidate();
		this.validate();
		this.repaint();
	}

	private void setLeftComponent(JPanel panelLeft) {
		// TODO Auto-generated method stub
		String selectedOption = "";
		
		if(comboBoxOptionLeft.getSelectedItem() != null)
		{
			selectedOption = comboBoxOptionLeft.getSelectedItem().toString();
		}
		
		for(Attribute attribute: formGenerator.getForm2().getComparison().getAttributesleft().getAttribute())
		{
			if(selectedOption.equals(attribute.getName()))
			{
				leftComponents = attribute.getParameters().getDropdownOption().size() + attribute.getParameters().getSpinnerOption().size();
				
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					for(DropDownOptionElement dropDownOptionElement: dropDownOption.getOption())
					{
						comboSource.add(dropDownOptionElement.getAs());
					}
					
					JComboBox comboBox = new JComboBox(comboSource.toArray());
					comboBox.setToolTipText(dropDownOption.getTooltip());
					JPanel attributePanel = new JPanel();
					JLabel attributeLabel = new JLabel(dropDownOption.getName());
					attributePanel.add(attributeLabel);
					attributePanel.add(comboBox);
					panelLeft.add(attributePanel);
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						int min = spinnerOptionElement.getMin();
						int max = spinnerOptionElement.getMax();
						
						for(int i = min; i <= max; i ++)
						{
							comboSource.add(i + "");
						}
						comboSource.add(spinnerOptionElement.getAs());
					}
					
					JComboBox comboBox = new JComboBox(comboSource.toArray());
					comboBox.setToolTipText(spinnerOption.getTooltip());
					JPanel attributePanel = new JPanel();
					JLabel attributeLabel = new JLabel(spinnerOption.getName());
					attributePanel.add(attributeLabel);
					attributePanel.add(comboBox);
					panelLeft.add(attributePanel);
				}
			}
		}
		int frameWidth = 800;
		heighestNumberOfComponent = leftComponents > rightComponents? leftComponents :rightComponents;
	    int frameHeight = heighestNumberOfComponent * 50 + 100;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.doLayout();
		this.repaint();
	}

	public void fun()
	{
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm2();
		
		LanguageEntry titleEntry = (LanguageEntry)syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 266);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/*
		JComboBox comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		comboBoxOptionLeft.setBounds(12, 13, 245, 22);
		contentPane.add(comboBoxOptionLeft);
		
		JComboBox comboBoxComparison = new JComboBox(getOperators().toArray());
		comboBoxComparison.setBounds(284, 13, 245, 22);
		contentPane.add(comboBoxComparison);
		
		JComboBox comboBoxOptionRight = new JComboBox(getRightAttributes().toArray());
		comboBoxOptionRight.setBounds(582, 13, 245, 22);
		contentPane.add(comboBoxOptionRight);
		
		
		
		LanguageEntry symbolEntry = (LanguageEntry)syntaxMap.get("Symbol");
		LanguageEntry subOption1Entry = (LanguageEntry)syntaxMap.get("SubOption1");
		LanguageEntry maxEntry = (LanguageEntry)syntaxMap.get("Max");
		
		JComboBox comboBoxMaxRight = new JComboBox();
		comboBoxMaxRight.setBounds(738, 162, 89, 22);
		contentPane.add(comboBoxMaxRight);
		
		
		JLabel label = new JLabel(maxEntry.getLabel());
		label.setBounds(582, 165, 144, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel(subOption1Entry.getLabel());
		label_1.setBounds(582, 117, 144, 16);
		contentPane.add(label_1);
		
		JComboBox comboBoxSubOption1Right = new JComboBox();
		comboBoxSubOption1Right.setBounds(738, 114, 89, 22);
		contentPane.add(comboBoxSubOption1Right);
		
		JComboBox comboBoxSymbolRight = new JComboBox();
		comboBoxSymbolRight.setBounds(738, 73, 89, 22);
		contentPane.add(comboBoxSymbolRight);
		
		JLabel label_2 = new JLabel(symbolEntry.getLabel());
		label_2.setBounds(582, 76, 144, 16);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(184, 162, 89, 22);
		contentPane.add(comboBox);
		
		JLabel label_3 = new JLabel((String) null);
		label_3.setBounds(28, 165, 144, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel((String) null);
		label_4.setBounds(28, 117, 144, 16);
		contentPane.add(label_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(184, 114, 89, 22);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(184, 73, 89, 22);
		contentPane.add(comboBox_2);
		*/
		JLabel label_5 = new JLabel((String) null);
		label_5.setBounds(28, 76, 144, 16);
		contentPane.add(label_5);
	}
	
	public List<String> getOperators()
	{
		List<String> operators = new ArrayList<String>();
		
		Form2 form2 = formGenerator.getForm2();
		
		for(Operand oprand: form2.getComparison().getOperators().getOp())
		{
			operators.add(oprand.getName());
		}
		
		return operators;
	}
	
	public List<String> getLeftAttributes()
	{
		List<String> attributes = new ArrayList<String>();
		
		Form2 form2 = formGenerator.getForm2();
		
		for(Attribute attribute: form2.getComparison().getAttributesleft().getAttribute())
		{
			attributes.add(attribute.getName());
		}
		
		return attributes;
	}
	
	public List<String> getRightAttributes()
	{
		List<String> attributes = new ArrayList<String>();
		
		Form2 form2 = formGenerator.getForm2();
		
		for(Attribute attribute: form2.getComparison().getAttributesright().getAttribute())
		{
			attributes.add(attribute.getName());
		}
		
		return attributes;
	}
}
