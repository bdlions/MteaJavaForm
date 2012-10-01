import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.forms.Form1;
import org.forms.form1.listoptions.Option;

public class Form1Output extends JFrame {

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
	public Form1Output(Form1 form1) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

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
		panel.add(new Label(option.getLabel()), constraints);
		
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = getRow();
		panel.add(new Label(option.getDefaultOption()), constraints);

		setRow(getRow() + 1);
		
		for (Option subOption : option.getSuboption()) {
			if(subOption.getName().equals(option.getDefaultOption()))
			addComponent(subOption, constraints, panel);
		}
	}

}
