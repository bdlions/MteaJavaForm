import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;

import org.forms.Form2;
import org.forms.Form3;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.listoptions.Option;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.form2.Attribute;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.languages.LanguageEntry;

import com.sun.java.swing.plaf.windows.resources.windows;


public class Form3Window extends JFrame {

	private JScrollPane scrollBar;
	
	private JPanel contentPane;
	private JPanel leftCardPanel;
	private JPanel leftCardPanelAttributeRight;
	private JPanel leftPanel;
	private JPanel leftPanelAttributeRight;
	private JPanel rightPanel;
	private JPanel rightPanelAttributeRight;
	private JPanel rightCardPanel;
	private JPanel rightCardPanelAttributeRight;
	
	private JPanel operatorPanelLeft;
	private JPanel comparisonPanel;
	private JPanel operatorPanelRight;
	
	private GroupLayout leftLayoutAttributeRight;
	private GroupLayout rightLayout;
	private GroupLayout rightLayoutAttributeRight;
	
	private FormGenerator formGenerator;
	private int heighestNumberOfComponent = 1;
	private int leftComponents = 1;
	private int rightComponents = 1;
	
	private JComboBox comboBoxOptionLeft;
	private JComboBox comboBoxOptionLeftAttributeRight;
	private JComboBox comboBoxOperatorLeft;
	private JComboBox comboBoxComparison;
	private JComboBox comboBoxOperatorRight;
	private JComboBox comboBoxOptionRight;
	private JComboBox comboBoxOptionRightAttributeRight ;

	

	/**
	 * Create the frame.
	 */
	public Form3Window(String language) 
	{
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm3();
		
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm3();
		
		LanguageEntry titleEntry = (LanguageEntry)syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  //setBounds(100, 100, 950, 300);
		  contentPane = new JPanel();
		  //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		  contentPane.setLayout(new BorderLayout(0, 0));
		  setContentPane(contentPane);
		  
		  JPanel panel = new JPanel();
		  //contentPane.add(panel, BorderLayout.CENTER);
		  
		  //Creating group layout
		  //GroupLayout layout = new GroupLayout(panel);
		  //setting the grouplaoyt in content pane
		  panel.setLayout(new GridLayout(0, 7, 30, 30));
		  //contentPane.setPreferredSize(new Dimension(500, 200));
		  
		  scrollBar = new JScrollPane();
		  scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		  contentPane.add(scrollBar, BorderLayout.CENTER);
		  scrollBar.setViewportView(panel);

		
		//set left container into left side
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		leftPanelAttributeRight = new JPanel();
		leftPanelAttributeRight.setLayout(new BorderLayout());
		
		operatorPanelLeft = new JPanel();
		operatorPanelLeft.setLayout(new BorderLayout());
		
		//set left container into center side
		comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new BorderLayout());
		
		operatorPanelRight = new JPanel();
		operatorPanelRight.setLayout(new BorderLayout());
		
		//set left container into right side
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		rightPanelAttributeRight = new JPanel();
		rightPanelAttributeRight.setLayout(new BorderLayout());
		
		//adding 3 panel in the container
		panel.add(leftPanel);
		panel.add(operatorPanelLeft);
		panel.add(leftPanelAttributeRight);		
		panel.add(comparisonPanel);		
		panel.add(rightPanel);
		panel.add(operatorPanelRight);
		panel.add(rightPanelAttributeRight);
		
		
		comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		leftPanel.add(comboBoxOptionLeft, BorderLayout.PAGE_START);
		leftCardPanel = new JPanel(new CardLayout());
		leftPanel.add(leftCardPanel, BorderLayout.CENTER);
		
		setLeftComponent(comboBoxOptionLeft.getSelectedItem().toString());
        
		comboBoxOptionLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 setLeftComponent(comboBoxOptionLeft.getSelectedItem().toString());
			}
		});
		
		comboBoxOptionLeftAttributeRight = new JComboBox(getRightAttributes().toArray());
		leftPanelAttributeRight.add(comboBoxOptionLeftAttributeRight, BorderLayout.PAGE_START);
		leftCardPanelAttributeRight = new JPanel(new CardLayout());
		leftPanelAttributeRight.add(leftCardPanelAttributeRight, BorderLayout.CENTER);
		
		setLeftComponentAttributeRight(comboBoxOptionLeftAttributeRight.getSelectedItem().toString());
        
		comboBoxOptionLeftAttributeRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 setLeftComponentAttributeRight(comboBoxOptionLeftAttributeRight.getSelectedItem().toString());
			}
		});
		
		comboBoxOperatorLeft = new JComboBox(getArithmaticOperators().toArray());
		operatorPanelLeft.add(comboBoxOperatorLeft, BorderLayout.PAGE_START);
		comboBoxOperatorLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 
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
		
		comboBoxOperatorRight = new JComboBox(getArithmaticOperators().toArray());
		operatorPanelRight.add(comboBoxOperatorRight, BorderLayout.PAGE_START);
		comboBoxOperatorRight.addActionListener(new ActionListener() {
			
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
				 setRightComponent(comboBoxOptionRight.getSelectedItem().toString());
			}
		});
		
		comboBoxOptionRightAttributeRight = new JComboBox(getRightAttributes().toArray());
		rightPanelAttributeRight.add(comboBoxOptionRightAttributeRight, BorderLayout.PAGE_START);
		rightCardPanelAttributeRight = new JPanel(new CardLayout());
		rightPanelAttributeRight.add(rightCardPanelAttributeRight, BorderLayout.CENTER);
		
		setRightComponentAttributeRight(comboBoxOptionRightAttributeRight.getSelectedItem().toString());
		comboBoxOptionRightAttributeRight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 setRightComponentAttributeRight(comboBoxOptionRightAttributeRight.getSelectedItem().toString());
			}
		});
		this.pack();
	}
	
	public List<String> getOperators()
	{
		List<String> operators = new ArrayList<String>();
		
		Form3 form3 = formGenerator.getForm3();
		
		for(Operand oprand: form3.getComparison().getOperators().getOp())
		{
			operators.add(oprand.getName());
		}
		
		return operators;
	}
	
	public List<String> getArithmaticOperators()
	{
		List<String> operators = new ArrayList<String>();
		
		Form3 form3 = formGenerator.getForm3();
		
		for(Operand oprand: form3.getComparison().getArithmaticoperators().getOp())
		{
			operators.add(oprand.getName());
		}
		
		return operators;
	}
	
	public List<String> getLeftAttributes()
	{
		List<String> attributes = new ArrayList<String>();
		
		Form3 form3 = formGenerator.getForm3();
		
		for(Attribute attribute: form3.getComparison().getAttributesleft().getAttribute())
		{
			attributes.add(attribute.getName());
		}
		
		return attributes;
	}
	
	public List<String> getRightAttributes()
	{
		List<String> attributes = new ArrayList<String>();
		
		Form3 form3 = formGenerator.getForm3();
		
		for(Attribute attribute: form3.getComparison().getAttributesright().getAttribute())
		{
			attributes.add(attribute.getName());
		}
		
		return attributes;
	}
	
	private void setLeftComponent(String cardName) {
		
		leftCardPanel.removeAll();
		leftCardPanel.validate();
		leftCardPanel.repaint();
		
		JPanel cp = new JPanel();
		
		
		GroupLayout layout = new GroupLayout(cp);
		//setting the grouplaoyt in content pane
		cp.setLayout(layout);
		//cp.setPreferredSize(new Dimension(500, 400));
		
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
		
		leftCardPanel.add(cp, cardName+"left");		
        
		String selectedOption = cardName;
		
		for(Attribute attribute: formGenerator.getForm3().getComparison().getAttributesleft().getAttribute())
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
					addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, dropDownOption.getName(), comboSource);
					
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					int counter = 0;
					int min = 0;
					int max = 0;
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						if(counter == 0)
							min = spinnerOptionElement.getMin();
						else if(counter == 1)
							max = spinnerOptionElement.getMax();
						
						counter++;
					}
					for(int i = min; i <= max; i ++)
					{
						comboSource.add(i + "");
					}
					//comboSource.add(spinnerOptionElement.getAs());
					
					addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, spinnerOption.getName(), comboSource);
										
				}
			}
		}
		
		leftCardPanel.validate();
		leftCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}
	
private void setLeftComponentAttributeRight(String cardName) {
		
		leftCardPanelAttributeRight.removeAll();
		leftCardPanelAttributeRight.validate();
		leftCardPanelAttributeRight.repaint();
		
		JPanel cp = new JPanel();
		
		
		leftLayoutAttributeRight = new GroupLayout(cp);
		//setting the grouplaoyt in content pane
		cp.setLayout(leftLayoutAttributeRight);
		//cp.setPreferredSize(new Dimension(500, 400));
		
		//setting gaps in container
		leftLayoutAttributeRight.setAutoCreateGaps(true);
		leftLayoutAttributeRight.setAutoCreateContainerGaps(true);
		
		//horizontal and vertical group
		ParallelGroup horizontalGroup = leftLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.LEADING);
		leftLayoutAttributeRight.setHorizontalGroup(horizontalGroup);
		
		SequentialGroup horizontalSequentialGroup = leftLayoutAttributeRight.createSequentialGroup();
		horizontalGroup.addGroup(horizontalSequentialGroup);
		
		ParallelGroup horizontalParallelGroup = leftLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.LEADING);
		horizontalSequentialGroup.addGroup(horizontalParallelGroup);
		
		
		ParallelGroup verticalGroup = leftLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.BASELINE);
		leftLayoutAttributeRight.setVerticalGroup(verticalGroup);
		
		SequentialGroup verticalSequentialGroup = leftLayoutAttributeRight.createSequentialGroup();
		verticalGroup.addGroup(verticalSequentialGroup);
		
		leftCardPanelAttributeRight.add(cp, cardName+"left");		
        
		String selectedOption = cardName;
		
		for(Attribute attribute: formGenerator.getForm3().getComparison().getAttributesright().getAttribute())
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
					addComponent(leftLayoutAttributeRight, horizontalParallelGroup, verticalSequentialGroup, dropDownOption.getName(), comboSource);
					
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					int counter = 0;
					int min = 0;
					int max = 0;
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						if(counter == 0)
							min = spinnerOptionElement.getMin();
						else if(counter == 1)
							max = spinnerOptionElement.getMax();
						
						counter++;
					}
					for(int i = min; i <= max; i ++)
					{
						comboSource.add(i + "");
					}
					//comboSource.add(spinnerOptionElement.getAs());
					
					addComponent(leftLayoutAttributeRight, horizontalParallelGroup, verticalSequentialGroup, spinnerOption.getName(), comboSource);
										
				}
			}
		}
		
		leftCardPanelAttributeRight.validate();
		leftCardPanelAttributeRight.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}
	
	private void addComponent(GroupLayout layout, ParallelGroup horizontalParallelGroup, 
			SequentialGroup verticalSequentialGroup, String comboName, List<String> comboSource)
	{
		
		JLabel leftComponent = new JLabel(comboName);
		leftComponent.setName(comboName+"Label");
		
		Component rightComponent = null;
		
		//if( type.equalsIgnoreCase("dropdown") || type.equalsIgnoreCase("spinner"))
		{
			JComboBox comboBox = new JComboBox(comboSource.toArray());
			comboBox.setName(comboName);
			
			comboBox.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub					
				}
			});
			rightComponent = comboBox;
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
	}
	

	private void setRightComponent(String cardName) 
	{
		rightCardPanel.removeAll();
		rightCardPanel.validate();
		rightCardPanel.repaint();
		
		JPanel cpRight = new JPanel();
		rightLayout = new GroupLayout(cpRight);
		//setting the grouplaoyt in content pane
		cpRight.setLayout(rightLayout);
		//cpRight.setPreferredSize(new Dimension(500, 400));
		
		//setting gaps in container
		rightLayout.setAutoCreateGaps(true);
		rightLayout.setAutoCreateContainerGaps(true);
		
		//horizontal and vertical group
		ParallelGroup horizontalGroup = rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		rightLayout.setHorizontalGroup(horizontalGroup);
		
		SequentialGroup horizontalSequentialGroup = rightLayout.createSequentialGroup();
		horizontalGroup.addGroup(horizontalSequentialGroup);
		
		ParallelGroup horizontalParallelGroup = rightLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
		horizontalSequentialGroup.addGroup(horizontalParallelGroup);
		
		
		ParallelGroup verticalGroup = rightLayout.createParallelGroup(GroupLayout.Alignment.BASELINE);
		rightLayout.setVerticalGroup(verticalGroup);
		
		SequentialGroup verticalSequentialGroup = rightLayout.createSequentialGroup();
		verticalGroup.addGroup(verticalSequentialGroup);
		
		rightCardPanel.add(cpRight, cardName+"right");		
        
		
		String selectedOption = "";
		
		if(comboBoxOptionRight.getSelectedItem() != null)
		{
			selectedOption = comboBoxOptionRight.getSelectedItem().toString();
		}
		
		for(Attribute attribute: formGenerator.getForm3().getComparison().getAttributesright().getAttribute())
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
					addComponent(rightLayout, horizontalParallelGroup, verticalSequentialGroup, dropDownOption.getName(), comboSource);
										
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					int counter = 0;
					int min = 0;
					int max = 0;
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						if(counter == 0)
							min = spinnerOptionElement.getMin();
						else if(counter == 1)
							max = spinnerOptionElement.getMax();
						
						counter++;
					}
					for(int i = min; i <= max; i ++)
					{
						comboSource.add(i + "");
					}
					//comboSource.add(spinnerOptionElement.getAs());
					
					addComponent(rightLayout, horizontalParallelGroup, verticalSequentialGroup, spinnerOption.getName(), comboSource);
					
				}
			}
		}
		
		rightCardPanel.validate();
		rightCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}
	
	private void setRightComponentAttributeRight(String cardName) 
	{
		rightCardPanelAttributeRight.removeAll();
		rightCardPanelAttributeRight.validate();
		rightCardPanelAttributeRight.repaint();
		
		JPanel cpRight = new JPanel();
		rightLayoutAttributeRight = new GroupLayout(cpRight);
		//setting the grouplaoyt in content pane
		cpRight.setLayout(rightLayoutAttributeRight);
		//cpRight.setPreferredSize(new Dimension(500, 400));
		
		//setting gaps in container
		rightLayoutAttributeRight.setAutoCreateGaps(true);
		rightLayoutAttributeRight.setAutoCreateContainerGaps(true);
		
		//horizontal and vertical group
		ParallelGroup horizontalGroup = rightLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.LEADING);
		rightLayoutAttributeRight.setHorizontalGroup(horizontalGroup);
		
		SequentialGroup horizontalSequentialGroup = rightLayoutAttributeRight.createSequentialGroup();
		horizontalGroup.addGroup(horizontalSequentialGroup);
		
		ParallelGroup horizontalParallelGroup = rightLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.LEADING);
		horizontalSequentialGroup.addGroup(horizontalParallelGroup);
		
		
		ParallelGroup verticalGroup = rightLayoutAttributeRight.createParallelGroup(GroupLayout.Alignment.BASELINE);
		rightLayoutAttributeRight.setVerticalGroup(verticalGroup);
		
		SequentialGroup verticalSequentialGroup = rightLayoutAttributeRight.createSequentialGroup();
		verticalGroup.addGroup(verticalSequentialGroup);
		
		rightCardPanelAttributeRight.add(cpRight, cardName+"right");		
        
		
		String selectedOption = "";
		
		if(comboBoxOptionRightAttributeRight.getSelectedItem() != null)
		{
			selectedOption = comboBoxOptionRightAttributeRight.getSelectedItem().toString();
		}
		
		for(Attribute attribute: formGenerator.getForm3().getComparison().getAttributesright().getAttribute())
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
					addComponent(rightLayoutAttributeRight, horizontalParallelGroup, verticalSequentialGroup, dropDownOption.getName(), comboSource);
										
				}
				
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					List<String> comboSource = new ArrayList<String>();
					
					int counter = 0;
					int min = 0;
					int max = 0;
					for(SpinnerOptionElement spinnerOptionElement: spinnerOption.getOption())
					{
						if(counter == 0)
							min = spinnerOptionElement.getMin();
						else if(counter == 1)
							max = spinnerOptionElement.getMax();
						
						counter++;
					}
					for(int i = min; i <= max; i ++)
					{
						comboSource.add(i + "");
					}
					//comboSource.add(spinnerOptionElement.getAs());
					
					addComponent(rightLayoutAttributeRight, horizontalParallelGroup, verticalSequentialGroup, spinnerOption.getName(), comboSource);
					
				}
			}
		}
		
		rightCardPanelAttributeRight.validate();
		rightCardPanelAttributeRight.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

}
