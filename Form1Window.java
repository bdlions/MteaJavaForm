import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import org.forms.Form1;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOption;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.listoptions.Option;
import org.forms.form1.spinner.SpinnerOption;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;


public class Form1Window extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLotReduction;
	private FormGenerator formGenerator;

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
		//setting language in formgenerator
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm1();
		
		
		initComponent();
	}
	
	
	private void initComponent()
	{
		
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		
		LanguageEntry titleEntry = (LanguageEntry)syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 380);
		getContentPane().setLayout(null);
		
		LanguageEntry playSoundEntry = (LanguageEntry)syntaxMap.get("PlaySound");
		JLabel lblNewLabel = new JLabel(playSoundEntry.getLabel());
		lblNewLabel.setBounds(44, 42, 289, 16);
		getContentPane().add(lblNewLabel);
		
		LanguageEntry tradingHourStart = (LanguageEntry)syntaxMap.get("TradingHourStart");
		JLabel label = new JLabel(tradingHourStart.getLabel());
		label.setBounds(44, 84, 289, 16);
		getContentPane().add(label);
		
		LanguageEntry lotReductionFactor = (LanguageEntry)syntaxMap.get("LotReductionFactor");
		JLabel label_1 = new JLabel(lotReductionFactor.getLabel());
		label_1.setBounds(44, 130, 289, 16);
		getContentPane().add(label_1);
		
		LanguageEntry buySellordersincludeTPSL = (LanguageEntry)syntaxMap.get("BuySellordersincludeTPSL");
		JLabel label_2 = new JLabel(buySellordersincludeTPSL.getLabel());
		label_2.setBounds(44, 180, 289, 16);
		getContentPane().add(label_2);
		
		LanguageEntry mm = (LanguageEntry)syntaxMap.get("MM");
		JLabel label_3 = new JLabel(mm.getLabel());
		label_3.setBounds(44, 233, 289, 16);
		getContentPane().add(label_3);
		
		LanguageEntry half = (LanguageEntry)syntaxMap.get("half");
		JLabel label_4 = new JLabel(half.getLabel());
		label_4.setBounds(44, 284, 289, 16);
		getContentPane().add(label_4);
		
		JComboBox comboBoxPlaySound = new JComboBox(getOptions("PlaySound").toArray());
		comboBoxPlaySound.setBounds(345, 42, 80, 22);
		getContentPane().add(comboBoxPlaySound);
		
		JComboBox comboBoxTradingHour = new JComboBox(getOptions("TradingHourStart").toArray());
		comboBoxTradingHour.setBounds(345, 84, 80, 22);
		
		getContentPane().add(comboBoxTradingHour);
		
		textFieldLotReduction = new JTextField();
		textFieldLotReduction.setBounds(345, 119, 203, 22);
		getContentPane().add(textFieldLotReduction);
		textFieldLotReduction.setColumns(10);
		
		JCheckBox chckbxNewCheckBoxBuySell = new JCheckBox();
		chckbxNewCheckBoxBuySell.setBounds(341, 174, 113, 25);
		getContentPane().add(chckbxNewCheckBoxBuySell);
		
		JComboBox comboBoxMM = new JComboBox(getOptions("MM").toArray());
		comboBoxMM.setBounds(345, 233, 80, 22);
		getContentPane().add(comboBoxMM);
		
		JComboBox comboBoxHalfOption = new JComboBox(getOptions("half").toArray());
		comboBoxHalfOption.setBounds(345, 284, 80, 22);
		getContentPane().add(comboBoxHalfOption);
		contentPane = new JPanel();
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
}
