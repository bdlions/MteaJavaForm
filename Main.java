import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.forms.FormGenerator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;


public class Main {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBoxMenuItem chckbxmntmEnglish;
	private JCheckBoxMenuItem chckbxmntmFrancis;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
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
	public Main() {
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
				String lanugage = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					lanugage = "Francais";
				}
				Form1Window form1Window = new Form1Window(lanugage);
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
				String lanugage = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					lanugage = "Francais";
				}
				Form2Window form2Window = new Form2Window(lanugage);
				form2Window.setVisible(true);
				form2Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mnOpen.add(mntmForm_1);
		
		JMenuItem mntmForm_3 = new JMenuItem("Form3");
		mntmForm_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				String lanugage = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					lanugage = "Francais";
				}
				Form3Window form3Window = new Form3Window(lanugage);
				form3Window.setVisible(true);
				form3Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		mnOpen.add(mntmForm_3);
		
		JMenu mnLanguage = new JMenu("Language");
		menuBar.add(mnLanguage);
		
		chckbxmntmEnglish = new JCheckBoxMenuItem("English");
		buttonGroup.add(chckbxmntmEnglish);
		mnLanguage.add(chckbxmntmEnglish);
		
		chckbxmntmFrancis = new JCheckBoxMenuItem("Francis");
		buttonGroup.add(chckbxmntmFrancis);
		mnLanguage.add(chckbxmntmFrancis);
		
		
		
	}

}
