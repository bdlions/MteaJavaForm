import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.ButtonGroup;

import org.forms.FormGenerator;
public class Main {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBoxMenuItem chckbxmntmEnglish;
	private JCheckBoxMenuItem chckbxmntmFrancis;
	
	private Form1Window form1Window = null;
	private Form2Window form2Window = null;
	private Form3Window form3Window = null;
	
	public boolean form1IsFirstTime = true;
	public boolean form2IsFirstTime = true;
	public boolean form3IsFirstTime = true;
	
	public FormGenerator formGenerator = null;
	
	public String form2LeftPanelSelectedItem = "";
	public String form2ComparisonSelectedItem = "";
	public String form2RightPanelSelectedItem = "";
	
	public String form3LeftOperatorLeftPanelSelectedItem = "";
	public String form3LeftOperatorRightPanelSelectedItem = "";
	public String form3LeftOperatorSelectedItem = "";
	public String form3RightOperatorLeftPanelSelectedItem = "";
	public String form3RightOperatorRightPanelSelectedItem = "";
	public String form3RightOperatorSelectedItem = "";
	public String form3ComparisonSelectedItem = "";
	/*
	 * Launching the application.
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

	/*
	 * Creating the application.
	 */
	public Main() {
		initialize();
	}

	/*
	 * Initializing the contents of the frame.
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
				//Getting user selected language to render form1
				String language = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					language = "Francais";
				}
				//initializing form1
				if(form1IsFirstTime == true)
				{
					String[] variables = {"var1","var2","var3"};
					form1Window = new Form1Window(language,variables,"form1.xml", Main.this);
					form1Window.setVisible(true);
					form1Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form1Window);
					form1IsFirstTime = false;
				}
				//reopening form1
				else
				{
					String[] variables = {"var1","var2","var3"};
					form1Window = new Form1Window(language,variables,"form1.xml", Main.this);
					form1Window.setVisible(true);
					form1Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form1Window);
					
					/*form1Window.setVisible(true);
					form1Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					form1Window.showForm1(language, Main.this);*/
					
				}
				
			}
		});
		mnOpen.add(mntmForm);
		
		JMenuItem mntmForm_1 = new JMenuItem("Form2");
		mntmForm_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Getting user selected language to render form2
				String language = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					language = "Francais";
				}
				//initializing form2
				if(form2Window == null)
				{
					String[] variables = {"var1","var2","var3"};
					form2Window = new Form2Window(language, variables,"form2.xml", Main.this);
					form2Window.setVisible(true);
					form2Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form2Window);
					form2IsFirstTime = false;
				}
				//reopening form1
				else
				{
					String[] variables = {"var1","var2","var3"};
					form2Window = new Form2Window(language, variables,"form2.xml", Main.this);
					form2Window.setVisible(true);
					form2Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form2Window);
					/*form2Window.setVisible(true);
					form2Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					form2Window.showForm2(language);*/
				}
				
			}
		});
		mnOpen.add(mntmForm_1);
		
		JMenuItem mntmForm_3 = new JMenuItem("Form3");
		mntmForm_3.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				//Getting user selected language to render form3
				String language = "English";
				if(chckbxmntmFrancis.isSelected())
				{
					language = "Francais";
				}
				//initializing form3
				if(form3Window == null)
				{
					String[] variables = {"var1","var2","var3"};
					form3Window = new Form3Window(language, variables,"form3.xml", Main.this);
					form3Window.setVisible(true);
					form3Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form3Window);
					form3IsFirstTime = false;
				}
				//reopening form1
				else
				{
					String[] variables = {"var1","var2","var3"};
					form3Window = new Form3Window(language, variables,"form3.xml", Main.this);
					form3Window.setVisible(true);
					form3Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					centreWindow(form3Window);
					/*form3Window.setVisible(true);
					form3Window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					form3Window.showForm3(language);*/
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

	/*
	 * Rendering the frame at the center of the screen 
	 */
	public static void centreWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
}
