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
import org.forms.Form3;
import org.forms.FormGenerator;
import org.forms.form2.Attribute;
import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;
import org.forms.languages.LanguageEntry;

public class Form3Output extends JFrame {
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
	public Form3Output(Form3 form3, JComboBox comboBoxOptionLeft, JComboBox comboBoxOperatorLeft,
			JComboBox comboBoxOptionLeftAttributeRight, 
			JComboBox  comboBoxComparison,JComboBox comboBoxOptionRightAttributeRight, 
			JComboBox comboBoxOperatorRight, JComboBox  comboBoxOptionRight , FormGenerator formGenerator ) {
		setBounds(100, 100, 459, 416);
		//panel to render form3 output
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.formGenerator = formGenerator;
		syntaxMap = formGenerator.getSyntaxMapForm3();

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel leftRightPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		JPanel rightLeftPanel = new JPanel(new GridBagLayout());
		JPanel comparisonPanel = new JPanel(new GridBagLayout());
		JPanel leftOperatorPanel = new JPanel(new GridBagLayout());
		JPanel rightOperatorPanel = new JPanel(new GridBagLayout());
		
		leftPanel.setBorder(BorderFactory.createTitledBorder("Left Operator Left Attributes"));
		leftOperatorPanel.setBorder(BorderFactory.createTitledBorder("Left operator"));		
		leftRightPanel.setBorder(BorderFactory.createTitledBorder("Left Operator Right Attributes"));
		comparisonPanel.setBorder(BorderFactory.createTitledBorder("Comparison"));
		rightLeftPanel.setBorder(BorderFactory.createTitledBorder("Right Operator Left Attributes"));
		rightOperatorPanel.setBorder(BorderFactory.createTitledBorder("Right operator"));		
		rightPanel.setBorder(BorderFactory.createTitledBorder("Right Operator Right Attributes"));
		
		
		panel.add(leftPanel);
		panel.add(leftOperatorPanel);
		panel.add(leftRightPanel);		
		panel.add(comparisonPanel);
		panel.add(rightLeftPanel);
		panel.add(rightOperatorPanel);
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
		String leftOperatorLeftPanelSelectedComboItem = comboBoxOptionLeft.getSelectedItem().toString();
		// adding each component of left arithmetic operator left panel to the output panel
		for (Attribute attribute : formGenerator.getForm3().getComparison().getLeftoperatorattributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(leftOperatorLeftPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, leftPanel);
				}
			}
		}
		
		setRow(0);
		String leftOperatorRightPanelSelectedComboItem = comboBoxOptionLeftAttributeRight.getSelectedItem().toString();
		// adding each component of left arithmetic operator right panel to the output panel
		for (Attribute attribute : formGenerator.getForm3().getComparison().getLeftoperatorattributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(leftOperatorRightPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, leftRightPanel);
				}
			}
		}
		
		setRow(0);
		String rightOperatorLeftPanelSelectedComboItem = comboBoxOptionRight.getSelectedItem().toString();
		// adding each component of right arithmetic operator left panel to the output panel
		for (Attribute attribute : formGenerator.getForm3().getComparison().getRightoperatorattributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(rightOperatorLeftPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, rightLeftPanel);
				}
			}
		}
		
		setRow(0);
		String rightOperatorRightPanelSelectedComboItem = comboBoxOptionRightAttributeRight.getSelectedItem().toString();
		// adding each component of rihgt arithmetic operator right panel to the output panel
		for (Attribute attribute : formGenerator.getForm3().getComparison().getRightoperatorattributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if(syntaxMap.containsKey(attributeName))
			{
				LanguageEntry attributeNameEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = attributeNameEntry.getLabel();				
			}
			if(attributeName.equals(rightOperatorRightPanelSelectedComboItem))
			{
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, rightPanel);
				}
			}
		}
		
		setRow(0);	
		// adding comparison selected item to the output panel
		addOperatorComponent("Comparision is: ", comboBoxComparison.getSelectedItem().toString(), constraints, comparisonPanel);
		setRow(0);
		// adding left arithmetic operator selected item to the output panel
		addOperatorComponent("Left operator is: ", comboBoxOperatorLeft.getSelectedItem().toString(), constraints, leftOperatorPanel);
		setRow(0);
		// adding right arithmetic operator selected item to the output panel
		addOperatorComponent("Right operator is: ", comboBoxOperatorRight.getSelectedItem().toString(), constraints, rightOperatorPanel);
	}
	
	/*
	 * This method adds operators to the output panel
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
