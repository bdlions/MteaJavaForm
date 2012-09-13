import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.GroupLayout.Alignment;


public class TableLayoutTest extends JFrame {

	private JPanel contentPane;
	private JLabel label1 = new JLabel("Primary:");
    private JTextField field1 = new JTextField(16);
    private JLabel label2 = new JLabel("Secondary:");
    private JTextField field2 = new JTextField(16);
    private JLabel label3 = new JLabel("Tertiary:");
    private JTextField field3 = new JTextField(16);
    
    private SequentialGroup horizontalSequentialGroup;
    private SequentialGroup verticalSequentialGroup;
    private ParallelGroup firstColumn;
    private ParallelGroup secondColumn;
    private GroupLayout layout;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableLayoutTest frame = new TableLayoutTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void GroupPanel() 
	{
		

    }

	/**
	 * Create the frame.
	 */
	public TableLayoutTest()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		layout = new GroupLayout(contentPane);          

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        horizontalSequentialGroup = layout.createSequentialGroup();
        verticalSequentialGroup = layout.createSequentialGroup();
        
        layout.setHorizontalGroup(horizontalSequentialGroup);
        layout.setVerticalGroup(verticalSequentialGroup);
        
        firstColumn = layout.createParallelGroup(GroupLayout.Alignment.TRAILING);
        secondColumn = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        
        
       addComponent(label1, field1);
       addComponent(label2, field2);
       //addComponent(label3, field3);
     
        
        /*layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(field1)
                .addComponent(field2)
                .addComponent(field3))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label1)
                .addComponent(field1))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label2)
                .addComponent(field2))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label3)
                .addComponent(field3))
        );
        */
		contentPane.setLayout(layout);
		
		
       
	}
	
	
	private void addComponent(Component label, Component value)
	{
        
        ParallelGroup row = layout.createParallelGroup(GroupLayout.Alignment.BASELINE);
        
        firstColumn.addComponent(label);
        secondColumn.addComponent(value);
        

        horizontalSequentialGroup.addGroup(firstColumn);
        horizontalSequentialGroup.addGroup(secondColumn);
        
        
        row.addComponent(label);
        row.addComponent(value);
        verticalSequentialGroup.addGroup(row);
        
	}
}
