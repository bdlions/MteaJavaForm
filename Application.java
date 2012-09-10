import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.forms.FormGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Application {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnOpen = new JMenu("Open");
		mnFile.add(mnOpen);
		
		JMenuItem mntmForm = new JMenuItem("Form1");
		mntmForm.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Form1Window form1Window = new Form1Window("English");
				form1Window.setVisible(true);
				form1Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mnOpen.add(mntmForm);
		
		JMenuItem mntmForm_1 = new JMenuItem("Form2");
		mntmForm_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				Form2Window form2Window = new Form2Window("English");
				form2Window.setVisible(true);
				form2Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mnOpen.add(mntmForm_1);
		
		
		
	}

}
