import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.forms.Form1;
import org.forms.FormGenerator;
import org.forms.form1.listoptions.ListSubOptions;
import org.forms.form1.listoptions.Option;
import org.forms.languages.LanguageEntry;

public class Form1Output extends JFrame {

	private JPanel contentPane;
	private Form1 form1;
	private FormGenerator formGenerator;
	
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
	public Form1Output(FormGenerator formGenerator) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.formGenerator = formGenerator;
		form1 = formGenerator.getForm1();
		
		JPanel panel = new JPanel(new GridBagLayout());
		
		
		contentPane.setPreferredSize(new Dimension(500, 250));

		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		GridBagConstraints constraints = new GridBagConstraints();
		
		
		for(Option option : form1.getListOptions().getOption())
		{
			addComponent(option, constraints, panel);
		}
	}
	
	public void addComponent(Option option, GridBagConstraints constraints, JPanel panel)
	{
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = getRow();
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		
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
		
		/*for (ListSubOptions listSubOption : option.getListSubOptions()) {
			String subOptionName = listSubOption.getName();
			if(option.getDefaultOption().equals(subOptionName))
			{
				for(Option subOption:listSubOption.getOption())
				{
					if(syntaxMap.containsKey(subOptionName))
					{
						LanguageEntry valueEntry = (LanguageEntry) syntaxMap.get(subOptionName);
						subOptionName = valueEntry.getLabel();
					}
					//JOptionPane.showMessageDialog(null, "option default value"+option.getDefaultOption()+";subOption name:"+subOptionName);
					if(subOptionName.equals(option.getDefaultOption()))
					addComponent(subOption, constraints, panel);
				}
			}
		}*/
	}

}
