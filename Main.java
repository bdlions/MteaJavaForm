import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;

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
	
	private Form1Window form1Window = null;
	private Form2Window form2Window = null;
	private Form3Window form3Window = null;
	
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
		frame.setLocationRelativeTo(null);
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
				String language = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					language = "Francais";
				}
				if(form1Window == null)
				{
					form1Window = new Form1Window(language);
					form1Window.setVisible(true);
					form1Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					centreWindow(form1Window);
				}
				else
				{
					form1Window.setVisible(true);
					//form1Window.getFormGenerator().setLanguage(language);
					//form1Window.getFormGenerator().updateSyntaxMapForm1();
					//form1Window.showForm1();
					//form1Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					//centreWindow(form1Window);
				}
				
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
				if(form2Window == null)
				{
					form2Window = new Form2Window(lanugage);
					form2Window.setVisible(true);
					form2Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					centreWindow(form2Window);
				}
				else
				{
					form2Window.setVisible(true);
				}
				
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
				if(form3Window == null)
				{
					form3Window = new Form3Window(lanugage);
					form3Window.setVisible(true);
					form3Window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					centreWindow(form3Window);
				}
				else
				{
					form3Window.setVisible(true);
				}
				
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

	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

}
