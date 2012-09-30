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
	private JComboBox comboBoxOptionRight ;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
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
	}*/

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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  //setBounds(100, 100, 450, 300);
		  contentPane = new JPanel();
		  contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		  contentPane.setLayout(new BorderLayout(0, 0));
		  setContentPane(contentPane);
		  
		  JPanel panel = new JPanel();
		  //contentPane.add(panel, BorderLayout.CENTER);
		  
		  //Creating group layout
		  //GroupLayout layout = new GroupLayout(panel);
		  //setting the grouplaoyt in content pane
		  //panel.setLayout(new GridLayout(0, 3, 30, 30));
		  //contentPane.setPreferredSize(new Dimension(500, 200));
		  panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		  
		  scrollBar = new JScrollPane();
		  scrollBar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  scrollBar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		  contentPane.add(scrollBar, BorderLayout.CENTER);
		  scrollBar.setViewportView(panel);
		
		//set left container into left side
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		//set left container into center side
		JPanel comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new BorderLayout());
		
		//set left container into right side
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		//adding 3 panel in the container
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
				 setLeftComponent(comboBoxOptionLeft.getSelectedItem().toString());
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
				 setRightComponent(comboBoxOptionRight.getSelectedItem().toString());
			}
		});
		this.pack();
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
	
	private void setLeftComponent(String cardName) {
		
		leftCardPanel.removeAll();
		leftCardPanel.validate();
		leftCardPanel.repaint();
		
		JPanel cp = new JPanel();
		
		
		GroupLayout layout = new GroupLayout(cp);
		//setting the grouplaoyt in content pane
		cp.setLayout(layout);
		//cp.setPreferredSize(new Dimension(300, 400));
		
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
		//cpRight.setPreferredSize(new Dimension(300, 400));
		
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

}
