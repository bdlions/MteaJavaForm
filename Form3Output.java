import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.forms.Form3;
import org.forms.form2.Attribute;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;

public class Form3Output extends JFrame {

	private JPanel contentPane;
	private int row = 0;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	

	/**
	 * Create the frame.
	 */
	public Form3Output(Form3 form2, JComboBox comboBoxOptionLeft, JComboBox comboBoxOperatorLeft,
			JComboBox comboBoxOptionLeftAttributeRight, 
			JComboBox  comboBoxComparison,JComboBox comboBoxOptionRightAttributeRight, 
			JComboBox comboBoxOperatorRight, JComboBox  comboBoxOptionRight ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel leftRightPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		JPanel rightLeftPanel = new JPanel(new GridBagLayout());
		JPanel comparisonPanel = new JPanel(new GridBagLayout());
		
		leftPanel.setBorder(BorderFactory.createTitledBorder("Left Attributes"));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Right Attributes"));
		leftRightPanel.setBorder(BorderFactory.createTitledBorder("Left Right Attributes"));
		rightLeftPanel.setBorder(BorderFactory.createTitledBorder("Right Left Attributes"));
		comparisonPanel.setBorder(BorderFactory.createTitledBorder("Comparison and operator"));
		
		panel.add(comparisonPanel);
		panel.add(leftPanel);
		panel.add(rightPanel);
		panel.add(leftRightPanel);
		panel.add(rightLeftPanel);
		
		
		contentPane.setPreferredSize(new Dimension(500, 250));

		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		GridBagConstraints constraints = new GridBagConstraints();
		
		for(Attribute attribute : form2.getComparison().getAttributesleft().getAttribute())
		{
			if(attribute.getName().equals(comboBoxOptionLeft.getSelectedItem().toString()))
			{
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					addComponent(dropDownOption.getName(), dropDownOption.getDefaultValue(), constraints, leftPanel);
				}
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					addComponent(spinnerOption.getName(), spinnerOption.getDefaultValue(), constraints, leftPanel);
				}
			}
		}
		
		setRow(0);
		for(Attribute attribute : form2.getComparison().getAttributesright().getAttribute())
		{
			if(attribute.getName().equals(comboBoxOptionRight.getSelectedItem().toString()))
			{
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					addComponent(dropDownOption.getName(), dropDownOption.getDefaultValue(), constraints, rightPanel);
				}
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					addComponent(spinnerOption.getName(), spinnerOption.getDefaultValue(), constraints, rightPanel);
				}
			}
		}
		
		setRow(0);
		for(Attribute attribute : form2.getComparison().getAttributesright().getAttribute())
		{
			if(attribute.getName().equals(comboBoxOptionLeftAttributeRight.getSelectedItem().toString()))
			{
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					addComponent(dropDownOption.getName(), dropDownOption.getDefaultValue(), constraints, leftRightPanel);
				}
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					addComponent(spinnerOption.getName(), spinnerOption.getDefaultValue(), constraints, leftRightPanel);
				}
			}
		}
		
		setRow(0);
		for(Attribute attribute : form2.getComparison().getAttributesright().getAttribute())
		{
			if(attribute.getName().equals(comboBoxOptionRightAttributeRight.getSelectedItem().toString()))
			{
				for(DropDownOption dropDownOption: attribute.getParameters().getDropdownOption())
				{
					addComponent(dropDownOption.getName(), dropDownOption.getDefaultValue(), constraints, rightLeftPanel);
				}
				for(SpinnerOption spinnerOption: attribute.getParameters().getSpinnerOption())
				{
					addComponent(spinnerOption.getName(), spinnerOption.getDefaultValue(), constraints, rightLeftPanel);
				}
			}
		}
		
		setRow(0);
		
		addComponent("Comparision is: ", comboBoxComparison.getSelectedItem().toString(), constraints, comparisonPanel);
		addComponent("Left operator is: ", comboBoxOperatorLeft.getSelectedItem().toString(), constraints, comparisonPanel);
		addComponent("Right operator is: ", comboBoxOperatorRight.getSelectedItem().toString(), constraints, comparisonPanel);
	}
	
	public void addComponent(String label, String value, GridBagConstraints constraints, JPanel panel)
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

}
