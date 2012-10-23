import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.forms.Form2;
import org.forms.FormGenerator;
import org.forms.form2.Attribute;
import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;
import org.forms.languages.LanguageEntry;

public class Form2Output extends JFrame {
	private FormGenerator formGenerator;
	private JPanel contentPane;
	private int row = 0;
	private Hashtable syntaxMap;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	

	/*
	 * Creating the frame.
	 */
	public Form2Output(Form2 form2, JComboBox comboBoxLeft, JComboBox comboBoxComparision,  JComboBox comboBoxRight, FormGenerator formGenerator ) {
		setBounds(100, 100, 459, 416);
		//panel to render form2 output
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.formGenerator = formGenerator;
		form2 = formGenerator.getForm2();
		syntaxMap = formGenerator.getSyntaxMapForm2();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		JPanel comparisonPanel = new JPanel(new GridBagLayout());
		
		leftPanel.setBorder(BorderFactory.createTitledBorder("Left Attributes"));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Right Attributes"));
		comparisonPanel.setBorder(BorderFactory.createTitledBorder("Comparison"));
		
		panel.add(leftPanel);		
		panel.add(comparisonPanel);
		panel.add(rightPanel);
		
		
		contentPane.setPreferredSize(new Dimension(500, 250));
		// adding vertical and horizontal scroll bars	
		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		GridBagConstraints constraints = new GridBagConstraints();		
		
		setRow(0);
		String leftPanelSelectedComboItem = comboBoxLeft.getSelectedItem().toString();
		// adding each component of left panel to the output panel
		for (Attribute attribute : formGenerator.getForm2().getComparison().getAttributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(leftPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, leftPanel);
				}
			}
		}		
		setRow(0);
		String rightPanelSelectedComboItem = comboBoxRight.getSelectedItem().toString();
		// adding each component of right panel to the output panel
		for (Attribute attribute : formGenerator.getForm2().getComparison().getAttributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(rightPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, rightPanel);
				}
			}
		}		
		setRow(0);
		// adding comparison selected item to the output panel
		addOperatorComponent("Comparision is: ", comboBoxComparision.getSelectedItem().toString(), constraints, comparisonPanel);		
	}
	
	/*
	 * This method adds comparison panel to the output panel
	 * */
	public void addOperatorComponent(String label, String value, GridBagConstraints constraints, JPanel panel)
	{
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = getRow();
		panel.add(new Label(label), constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = getRow();
		panel.add(new Label(value), constraints);

		setRow(getRow() + 1);
		
		
	}
	
	/*
	 * This method adds an option to the panel
	 */
	public void addComponent(Option option, GridBagConstraints constraints, JPanel panel)
	{
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = getRow();
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm2();
		
		panel.add(new Label(option.getName()), constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = getRow();
		
		if(syntaxMap.containsKey(option.getDefaultOption()))
		{
			LanguageEntry valueEntry = (LanguageEntry) syntaxMap.get(option.getDefaultOption());
			panel.add(new Label(valueEntry.getLabel()), constraints);
		}
		else
		{
			panel.add(new Label(option.getDefaultOption()), constraints);
		}
		setRow(getRow() + 1);
		//adding sub option of this option in output panel
		if (option.getListSubOptions().size() > 0) {
			for (ListSubOptions listSuboption : option.getListSubOptions()) {
				if (listSuboption.getName().equals(option.getDefaultOption())) {
					for (Option subOption : listSuboption.getSubOption()) {
						addComponent(subOption, constraints, panel);
					}
				}
			}
		}
	}
}
