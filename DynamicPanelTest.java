import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class DynamicPanelTest extends JFrame implements ActionListener {

	private JPanel contentPane;
	JComboBox comboBox;
	JPanel panel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DynamicPanelTest frame = new DynamicPanelTest();
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
	public DynamicPanelTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
	    panel = new JPanel();
	    
		contentPane.add(panel, BorderLayout.CENTER);
		
		String[] s = {"A", "B", "C", "D"};
		comboBox = new JComboBox(s);
		comboBox.addActionListener(this);
		//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setMinimumSize(new Dimension(200, 200));
		((JFrame)panel.getTopLevelAncestor()).pack();
		
		panel.add(comboBox);
		setResizable(true);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object selectedItem = comboBox.getSelectedItem();
		panel.removeAll();
		String[] s = {"A", "B", "C", "D"};
		comboBox = new JComboBox(s);
		comboBox.setSelectedItem(selectedItem);
		comboBox.addActionListener(this);
		panel.add(comboBox);
		
		// TODO Auto-generated method stub
		if(comboBox.getSelectedItem().toString() == "A")
		{
			panel.add(new Button("1"));
		}
		else if(comboBox.getSelectedItem().toString() == "B")
		{
			panel.add(new Button("1"));
			panel.add(new Button("2"));
		}
		else if(comboBox.getSelectedItem().toString() == "C")
		{
			panel.add(new Button("1"));
			panel.add(new Button("2"));
			panel.add(new Button("1333"));
		}
		else if(comboBox.getSelectedItem().toString() == "D")
		{
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			panel.add(new Button("4"));
			panel.add(new Button("6"));
			panel.add(new Button("7"));
			
		}
		//((JFrame)panel.getTopLevelAncestor()).pack();
		panel.revalidate();
		panel.repaint();
	}
}
