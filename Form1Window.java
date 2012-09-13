import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
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
		
		
		LanguageEntry playSoundEntry = (LanguageEntry)syntaxMap.get("PlaySound");
		
		JLabel lblNewLabel = new JLabel(playSoundEntry.getLabel());
		JComboBox comboBoxPlaySound = new JComboBox(getOptions("PlaySound").toArray());
		
		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, lblNewLabel, comboBoxPlaySound);
		
		LanguageEntry tradingHourStart = (LanguageEntry)syntaxMap.get("TradingHourStart");
		JLabel label = new JLabel(tradingHourStart.getLabel());
		JComboBox comboBoxTradingHour = new JComboBox(getOptions("TradingHourStart").toArray());
		
		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, label, comboBoxTradingHour);
		
		LanguageEntry lotReductionFactor = (LanguageEntry)syntaxMap.get("LotReductionFactor");
		JLabel label_1 = new JLabel(lotReductionFactor.getLabel());
		textFieldLotReduction = new JTextField();
		
		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, label_1, textFieldLotReduction);
		
		
		LanguageEntry buySellordersincludeTPSL = (LanguageEntry)syntaxMap.get("BuySellordersincludeTPSL");
		JLabel label_2 = new JLabel(buySellordersincludeTPSL.getLabel());
		JCheckBox chckbxNewCheckBoxBuySell = new JCheckBox();
		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, label_2, chckbxNewCheckBoxBuySell);
		
		
		LanguageEntry mm = (LanguageEntry)syntaxMap.get("MM");
		JLabel label_3 = new JLabel(mm.getLabel());
		JComboBox comboBoxMM = new JComboBox(getOptions("MM").toArray());
		
		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, label_3, comboBoxMM);
		

		LanguageEntry half = (LanguageEntry)syntaxMap.get("half");
		JLabel label_4 = new JLabel(half.getLabel());

		JComboBox comboBoxHalfOption = new JComboBox(getOptions("half").toArray());

		addComponent(layout, horizontalParallelGroup, verticalSequentialGroup, label_4, comboBoxHalfOption);
		
		this.pack();
	}
	
	
	
	
	
	public List<String> getOptions(String name)
	{
		List<String> options = new ArrayList<String>();
		
		Form1 form1 = formGenerator.getForm1();
		
		
		for(Option option:form1.getListOptions().getOption())
		{
			if(option.getName().equals(name))
			{
				for(DropDownOption dropDownOption: option.getDropdownOption())
				{
					for(DropDownOptionElement dropDownOptionElement: dropDownOption.getOption())
					{
						options.add(dropDownOptionElement.getAs());
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
						
						options.add(spinnerOptionElement.getAs());
					}
				}
			}
		}
		
		return options;
	}
	
	
	private void addComponent(GroupLayout layout, ParallelGroup horizontalParallelGroup, SequentialGroup verticalSequential, Component leftComponent, Component rightComponent)
	{
		SequentialGroup componentSequential2 = layout.createSequentialGroup();
		
		
		componentSequential2.addComponent(leftComponent);
		componentSequential2.addComponent(rightComponent);
		
		horizontalParallelGroup.addGroup(componentSequential2);
		
		
		ParallelGroup verticlaParallelGroupFinal2 = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
		verticalSequential.addGroup(verticlaParallelGroupFinal2);
		verticlaParallelGroupFinal2.addComponent(leftComponent);
		verticlaParallelGroupFinal2.addComponent(rightComponent);
		
	}
}
