import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.form2.Attribute;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.languages.LanguageEntry;


public class Form2Window extends JFrame {

	private JPanel contentPane;
	JPanel leftCardPanel;
	JPanel rightCardPanel;
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
		
		//only close this frame not it's parent
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//set bounds of the frame
		setBounds(100, 100, 450, 300);
		
		//set the container layout to gridlayout one row 3 colum
		contentPane = new JPanel();
		contentPane.setLayout(new GridLayout(0, 3));
		setContentPane(contentPane);
		
		//set left container into left side
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());
		
		//set left container into center side
		JPanel comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new BorderLayout());
		
		//set left container into right side
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		//adding 3 panel in the container
		contentPane.add(leftPanel);
		contentPane.add(comparisonPanel);
		contentPane.add(rightPanel);
		
		
		comboBoxOptionLeft = new JComboBox(getLeftAttributes().toArray());
		leftPanel.add(comboBoxOptionLeft, BorderLayout.PAGE_START);
		leftCardPanel = new JPanel(new CardLayout());
		setLeftComponent(comboBoxOptionLeft.getSelectedItem().toString());
        
		leftPanel.add(leftCardPanel, BorderLayout.CENTER);
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
		// TODO Auto-generated method stub
		JPanel currentLeftCard = new JPanel();
		currentLeftCard.setLayout(new BoxLayout(currentLeftCard, BoxLayout.Y_AXIS));
        leftCardPanel.add(currentLeftCard, cardName+"left");
        
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
					
					JComboBox comboBox = new JComboBox(comboSource.toArray());
					comboBox.setToolTipText(dropDownOption.getTooltip());
					JPanel attributePanel = new JPanel();
					JLabel attributeLabel = new JLabel(dropDownOption.getName());
					attributePanel.add(attributeLabel);
					attributePanel.add(comboBox);
					currentLeftCard.add(attributePanel);
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
					currentLeftCard.add(attributePanel);
				}
			}
		}
		
		CardLayout cl = (CardLayout)(leftCardPanel.getLayout());
        cl.show(leftCardPanel, cardName+"left");
        
		int frameWidth = 800;
		heighestNumberOfComponent = leftComponents > rightComponents? leftComponents :rightComponents;
	    int frameHeight = heighestNumberOfComponent * 50 + 100;
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    this.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.doLayout();
		this.repaint();
	}

	private void setRightComponent(String cardName) 
	{
		// TODO Auto-generated method stub
		JPanel currentRightCard = new JPanel();
		currentRightCard.setLayout(new BoxLayout(currentRightCard, BoxLayout.Y_AXIS));
        rightCardPanel.add(currentRightCard, cardName+"right");
        
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
					currentRightCard.add(attributePanel);
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
					currentRightCard.add(attributePanel);
				}
			}
		}
		
		CardLayout cl = (CardLayout)(rightCardPanel.getLayout());
        cl.show(rightCardPanel, cardName+"right");
        
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

}
