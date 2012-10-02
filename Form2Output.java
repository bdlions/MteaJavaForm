import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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

import org.forms.Form1;
import org.forms.Form2;
import org.forms.form1.listoptions.Option;
import org.forms.form2.Attribute;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;

public class Form2Output extends JFrame {

	private JPanel contentPane;
	private int row = 0;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form1Output frame = new Form1Output(new Form1());
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
	public Form2Output(Form2 form2, JComboBox comboBoxLeft, JComboBox comboBoxComparision,  JComboBox comboBoxRight ) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel leftPanel = new JPanel(new GridBagLayout());
		JPanel rightPanel = new JPanel(new GridBagLayout());
		JPanel comparisonPanel = new JPanel(new GridBagLayout());
		
		leftPanel.setBorder(BorderFactory.createTitledBorder("Left Attributes"));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Right Attributes"));
		comparisonPanel.setBorder(BorderFactory.createTitledBorder("Comparison"));
		
		panel.add(comparisonPanel);
		panel.add(leftPanel);
		panel.add(rightPanel);
		
		
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
			if(attribute.getName().equals(comboBoxLeft.getSelectedItem().toString()))
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
			if(attribute.getName().equals(comboBoxRight.getSelectedItem().toString()))
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
		addComponent("Comparision is: ", comboBoxComparision.getSelectedItem().toString(), constraints, comparisonPanel);
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
