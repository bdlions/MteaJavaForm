import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.swing.BoxLayout;
import org.forms.Form3;
import org.forms.FormGenerator;
import org.forms.form2.Attribute;
import org.forms.form2.Operand;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.dropdown.DropDownOptionElement;
import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;
import org.forms.form2.spinner.SpinnerOption;
import org.forms.form2.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

public class Form3Window extends JFrame {

	private JScrollPane scrollBar;

	private JPanel contentPane;
	private JPanel leftCardPanel;
	private JPanel leftCardPanelAttributeRight;
	private JPanel leftPanel;
	private JPanel leftPanelAttributeRight;
	private JPanel rightPanel;
	private JPanel rightPanelAttributeRight;
	private JPanel rightCardPanel;
	private JPanel rightCardPanelAttributeRight;

	private JPanel operatorPanelLeft;
	private JPanel comparisonPanel;
	private JPanel operatorPanelRight;

	private GroupLayout leftLayoutAttributeRight;
	private GroupLayout rightLayout;
	private GroupLayout rightLayoutAttributeRight;

	private FormGenerator formGenerator;
	private int heighestNumberOfComponent = 1;
	private int leftComponents = 1;
	private int rightComponents = 1;

	private JComboBox comboBoxOptionLeft;
	private JComboBox comboBoxOptionLeftAttributeRight;
	private JComboBox comboBoxOperatorLeft;
	private JComboBox comboBoxComparison;
	private JComboBox comboBoxOperatorRight;
	private JComboBox comboBoxOptionRight;
	private JComboBox comboBoxOptionRightAttributeRight;

	private Hashtable syntaxMap;
	private Hashtable syntaxMapLabelToName;
	private LanguageEntry languageEntry;
	private Attribute currentSelectedLeftOperatorLeftAttribute;
	private Attribute currentSelectedLeftOperatoRightAttribute;
	private Attribute currentSelectedRightOperatorLeftAttribute;
	private Attribute currentSelectedRightOperatorRightAttribute;
	private String currentPanel = "";
	private boolean blockComboChangeEvent = false;

	private int row = 0;

	private String leftOperatorLeftPanelSelectedItem = "";
	private String leftOperatorRightPanelSelectedItem = "";
	private String leftOperatorSelectedItem = "";
	private String rightOperatorLeftPanelSelectedItem = "";
	private String rightOperatorRightPanelSelectedItem = "";
	private String rightOperatorSelectedItem = "";
	private String comparisonSelectedItem = "";

	private String tempLeftOperatorLeftPanelSelectedItem = "";
	private String tempLeftOperatorRightPanelSelectedItem = "";
	private String tempLeftOperatorSelectedItem = "";
	private String tempRightOperatorLeftPanelSelectedItem = "";
	private String tempRightOperatorRightPanelSelectedItem = "";
	private String tempRightOperatorSelectedItem = "";
	private String tempComparisonSelectedItem = "";
	public FormGenerator tempFormGenerator;
	
	private String[] variables;
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	/*
	 * Creating the frame.
	 */
	public Form3Window(String language, String[] variables) {
		this.variables = variables;
		// initializing form3
		formGenerator = new FormGenerator();
		formGenerator.generateForm3();
		//initializing left operator panels, comparison panel and right operator panels initial selected item
		leftOperatorLeftPanelSelectedItem = (String) getLeftOperatorLeftAttributes()
				.toArray()[0];
		leftOperatorRightPanelSelectedItem = (String) getLeftOperatorRightAttributes()
				.toArray()[0];
		leftOperatorSelectedItem = (String) getArithmaticOperators().toArray()[0];
		rightOperatorLeftPanelSelectedItem = (String) getRightOperatorLeftAttributes()
				.toArray()[0];
		rightOperatorRightPanelSelectedItem = (String) getRightOperatorRightAttributes()
				.toArray()[0];
		rightOperatorSelectedItem = (String) getArithmaticOperators().toArray()[0];
		comparisonSelectedItem = (String) getOperators().toArray()[0];

		showForm3(language);
	}

	/*
	 * Rendering form3
	 */
	public void showForm3(String language) {
		//cloning initial object content to a temporary object
		tempFormGenerator = (FormGenerator) formGenerator.clone();
		tempLeftOperatorLeftPanelSelectedItem = leftOperatorLeftPanelSelectedItem;
		tempLeftOperatorRightPanelSelectedItem = leftOperatorRightPanelSelectedItem;
		tempLeftOperatorSelectedItem = leftOperatorSelectedItem;
		tempRightOperatorLeftPanelSelectedItem = rightOperatorLeftPanelSelectedItem;
		tempRightOperatorRightPanelSelectedItem = rightOperatorRightPanelSelectedItem;
		tempRightOperatorSelectedItem = rightOperatorSelectedItem;
		tempComparisonSelectedItem = comparisonSelectedItem;
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);		
		// setting language to render form3
		formGenerator.setLanguage(language);
		formGenerator.updateLanguageMapForm3();
		// retrieving language info which is stored in a hash table
		syntaxMap = formGenerator.getSyntaxMapForm3();
		syntaxMapLabelToName = formGenerator.getSyntaxMapLabelToNameForm3();

		String title = formGenerator.getForm3().getTitle();
		if (syntaxMap.containsKey(title)) {
			languageEntry = (LanguageEntry) syntaxMap.get(title);
			title = languageEntry.getLabel();
		}
		// setting form title
		setTitle(title);

		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	//rolling back to the initial opening state of this form
            	formGenerator = (FormGenerator) tempFormGenerator.clone();            	
            }
        });
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getSize().width;
				int height = getSize().height;

				Dimension dimension = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int screenWidth = dimension.width;
				int screenHeight = dimension.height;

				int currentHeight = height > screenHeight ? screenHeight
						: height;
				int currentWidth = width > screenWidth ? screenWidth : width;

				setSize(new Dimension(currentWidth, currentHeight));
				super.componentResized(e);
			}

		});

		// panel to render form3
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 0, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel buttonPanel = new JPanel();
		// ok and cancel buttons panel
		String okLabel = "ok";
		if (syntaxMap.containsKey("okLabel")) {
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("ok");
			okLabel = titleEntry.getLabel();
		}
		JButton okButton = new JButton(okLabel);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//hiding current form and rendering output
				setVisible(false);				
				// rendering output of form3
				Form3Output from3Output = new Form3Output(formGenerator
						.getForm3(), comboBoxOptionLeft, comboBoxOperatorLeft,
						comboBoxOptionLeftAttributeRight, comboBoxComparison,
						comboBoxOptionRightAttributeRight,
						comboBoxOperatorRight, comboBoxOptionRight,
						formGenerator);
				from3Output.setVisible(true);
				from3Output.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				Main.centreWindow(from3Output);
			}
		});

		String cancelLabel = "cancel";
		if (syntaxMap.containsKey("cancel")) {
			LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("cancel");
			cancelLabel = titleEntry.getLabel();
		}
		JButton cancelButton = new JButton(cancelLabel);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// initializing form2
				formGenerator = new FormGenerator();
				formGenerator.generateForm3();

				leftOperatorLeftPanelSelectedItem = (String) getLeftOperatorLeftAttributes()
						.toArray()[0];
				leftOperatorRightPanelSelectedItem = (String) getLeftOperatorRightAttributes()
						.toArray()[0];
				leftOperatorSelectedItem = (String) getArithmaticOperators().toArray()[0];
				rightOperatorLeftPanelSelectedItem = (String) getRightOperatorLeftAttributes()
						.toArray()[0];
				rightOperatorRightPanelSelectedItem = (String) getRightOperatorRightAttributes()
						.toArray()[0];
				rightOperatorSelectedItem = (String) getArithmaticOperators().toArray()[0];
				comparisonSelectedItem = (String) getOperators().toArray()[0];
				// hiding this window
				buttonCancelPressed();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		// adding vertical and horizontal scroll bars
		scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		//left arithmetic operator left panel
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		//left arithmetic operator panel
		operatorPanelLeft = new JPanel();
		operatorPanelLeft.setLayout(new BorderLayout());
		
		//left arithmetic operator right panel
		leftPanelAttributeRight = new JPanel();
		leftPanelAttributeRight.setLayout(new BorderLayout());

		// comparison panel
		comparisonPanel = new JPanel();
		comparisonPanel.setLayout(new BorderLayout());
		
		//right arithmetic operator left panel		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		//right arithmetic operator panel
		operatorPanelRight = new JPanel();
		operatorPanelRight.setLayout(new BorderLayout());
		
		//right arithmetic operator right panel
		rightPanelAttributeRight = new JPanel();
		rightPanelAttributeRight.setLayout(new BorderLayout());

		
		// adding 7 panel in the container
		panel.add(leftPanel);
		panel.add(operatorPanelLeft);
		panel.add(leftPanelAttributeRight);
		panel.add(comparisonPanel);
		panel.add(rightPanel);
		panel.add(operatorPanelRight);
		panel.add(rightPanelAttributeRight);

		//left arithmetic operator left panel selected item combo box
		comboBoxOptionLeft = new JComboBox(getLeftOperatorLeftAttributes()
				.toArray());
		leftPanel.add(comboBoxOptionLeft, BorderLayout.PAGE_START);
		leftCardPanel = new JPanel(new CardLayout());
		leftPanel.add(leftCardPanel, BorderLayout.CENTER);
		comboBoxOptionLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				leftOperatorLeftPanelSelectedItem = comboBoxOptionLeft
						.getSelectedItem().toString();
				setLeftComponent(comboBoxOptionLeft.getSelectedItem()
						.toString());
			}
		});
		comboBoxOptionLeft.setSelectedItem(leftOperatorLeftPanelSelectedItem);

		//left arithmetic operator right panel selected item combo box		
		comboBoxOptionLeftAttributeRight = new JComboBox(
				getLeftOperatorRightAttributes().toArray());
		leftPanelAttributeRight.add(comboBoxOptionLeftAttributeRight,
				BorderLayout.PAGE_START);
		leftCardPanelAttributeRight = new JPanel(new CardLayout());
		leftPanelAttributeRight.add(leftCardPanelAttributeRight,
				BorderLayout.CENTER);
		comboBoxOptionLeftAttributeRight
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						leftOperatorRightPanelSelectedItem = comboBoxOptionLeftAttributeRight
								.getSelectedItem().toString();
						setLeftComponentAttributeRight(comboBoxOptionLeftAttributeRight
								.getSelectedItem().toString());
					}
				});
		comboBoxOptionLeftAttributeRight
				.setSelectedItem(leftOperatorRightPanelSelectedItem);

		//left arithmetic operator panel selected item combo box		
		comboBoxOperatorLeft = new JComboBox(getArithmaticOperators().toArray());
		operatorPanelLeft.add(comboBoxOperatorLeft, BorderLayout.PAGE_START);
		comboBoxOperatorLeft.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				leftOperatorSelectedItem = comboBoxOperatorLeft
						.getSelectedItem().toString();
			}
		});
		comboBoxOperatorLeft.setSelectedItem(leftOperatorSelectedItem);

		//comparison panel selected item combo box		
		comboBoxComparison = new JComboBox(getOperators().toArray());
		comparisonPanel.add(comboBoxComparison, BorderLayout.PAGE_START);
		comboBoxComparison.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				comparisonSelectedItem = comboBoxComparison.getSelectedItem()
						.toString();
			}
		});
		comboBoxComparison.setSelectedItem(comparisonSelectedItem);

		//right arithmetic operator panel selected item combo box		
		comboBoxOperatorRight = new JComboBox(getArithmaticOperators()
				.toArray());
		operatorPanelRight.add(comboBoxOperatorRight, BorderLayout.PAGE_START);
		comboBoxOperatorRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rightOperatorSelectedItem = comboBoxOperatorRight
						.getSelectedItem().toString();
			}
		});
		comboBoxOperatorRight.setSelectedItem(rightOperatorSelectedItem);

		//right arithmetic operator left panel selected item combo box	
		comboBoxOptionRight = new JComboBox(getRightOperatorLeftAttributes()
				.toArray());
		rightPanel.add(comboBoxOptionRight, BorderLayout.PAGE_START);
		rightCardPanel = new JPanel(new CardLayout());
		rightPanel.add(rightCardPanel, BorderLayout.CENTER);
		comboBoxOptionRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rightOperatorLeftPanelSelectedItem = comboBoxOptionRight
						.getSelectedItem().toString();
				setRightComponent(comboBoxOptionRight.getSelectedItem()
						.toString());
			}
		});
		comboBoxOptionRight.setSelectedItem(rightOperatorLeftPanelSelectedItem);

		//right arithmetic operator right panel selected item combo box	
		comboBoxOptionRightAttributeRight = new JComboBox(
				getRightOperatorRightAttributes().toArray());
		rightPanelAttributeRight.add(comboBoxOptionRightAttributeRight,
				BorderLayout.PAGE_START);
		rightCardPanelAttributeRight = new JPanel(new CardLayout());
		rightPanelAttributeRight.add(rightCardPanelAttributeRight,
				BorderLayout.CENTER);
		comboBoxOptionRightAttributeRight
				.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						rightOperatorRightPanelSelectedItem = comboBoxOptionRightAttributeRight
								.getSelectedItem().toString();
						setRightComponentAttributeRight(comboBoxOptionRightAttributeRight
								.getSelectedItem().toString());
					}
				});
		comboBoxOptionRightAttributeRight
				.setSelectedItem(rightOperatorRightPanelSelectedItem);
		this.pack();
	}

	public void buttonCancelPressed() {
		//rolling back to the initial opening state of this form
		formGenerator = (FormGenerator) tempFormGenerator.clone();		
		leftOperatorLeftPanelSelectedItem = tempLeftOperatorLeftPanelSelectedItem;
		leftOperatorRightPanelSelectedItem = tempLeftOperatorRightPanelSelectedItem;
		leftOperatorSelectedItem = tempLeftOperatorSelectedItem;
		rightOperatorLeftPanelSelectedItem = tempRightOperatorLeftPanelSelectedItem;
		rightOperatorRightPanelSelectedItem = tempRightOperatorRightPanelSelectedItem;
		rightOperatorSelectedItem = tempRightOperatorSelectedItem;
		comparisonSelectedItem = tempComparisonSelectedItem;
		
		// hiding this window
		this.setVisible(false);
	}
	
	/*
	 * This method returns comparison panel operators
	 * */
	public List<String> getOperators() {
		List<String> operators = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Operand oprand : form3.getComparison().getOperators().getOp()) {
			operators.add(oprand.getName());
		}
		return operators;
	}

	/*
	 * This method returns arithmetic panel operators
	 * */
	public List<String> getArithmaticOperators() {
		List<String> operators = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Operand oprand : form3.getComparison().getArithmaticoperators()
				.getOp()) {
			operators.add(oprand.getName());
		}
		return operators;
	}

	/*
	 * This method returns left arithmetic operator left panel item list
	 * */
	public List<String> getLeftOperatorLeftAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Attribute attribute : form3.getComparison()
				.getLeftoperatorattributesleft().getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method returns left arithmetic operator right panel item list
	 * */
	public List<String> getLeftOperatorRightAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Attribute attribute : form3.getComparison()
				.getLeftoperatorattributesright().getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method returns right arithmetic operator left panel item list
	 * */
	public List<String> getRightOperatorLeftAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Attribute attribute : form3.getComparison()
				.getRightoperatorattributesleft().getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method returns right arithmetic operator right panel item list
	 * */
	public List<String> getRightOperatorRightAttributes() {
		List<String> attributes = new ArrayList<String>();
		Form3 form3 = formGenerator.getForm3();
		for (Attribute attribute : form3.getComparison()
				.getRightoperatorattributesright().getAttribute()) {
			attributes.add(attribute.getName());
		}
		return attributes;
	}

	/*
	 * This method renders left arithmetic operator left panel
	 * */
	private void setLeftComponent(String cardName) {

		leftCardPanel.removeAll();
		leftCardPanel.validate();
		leftCardPanel.repaint();

		JPanel cp = new JPanel(new GridBagLayout());
		cp.setName("LeftOperatorLeftPanel");
		leftCardPanel.add(cp, cardName + "right");

		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm3().getComparison()
				.getLeftoperatorattributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "LeftOperatorLeftPanel";
				currentSelectedLeftOperatorLeftAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cp);
				}
			}
		}

		leftCardPanel.validate();
		leftCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method renders left arithmetic operator right panel
	 * */
	private void setLeftComponentAttributeRight(String cardName) {

		leftCardPanelAttributeRight.removeAll();
		leftCardPanelAttributeRight.validate();
		leftCardPanelAttributeRight.repaint();

		JPanel cp = new JPanel(new GridBagLayout());
		cp.setName("LeftOperatorRightPanel");
		leftCardPanelAttributeRight.add(cp, cardName + "right");

		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm3().getComparison()
				.getLeftoperatorattributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "LeftOperatorRightPanel";
				currentSelectedLeftOperatoRightAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cp);
				}
			}
		}

		leftCardPanelAttributeRight.validate();
		leftCardPanelAttributeRight.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method renders right arithmetic operator left panel
	 * */
	private void setRightComponent(String cardName) {
		rightCardPanel.removeAll();
		rightCardPanel.validate();
		rightCardPanel.repaint();

		JPanel cpRight = new JPanel(new GridBagLayout());
		cpRight.setName("RightOperatorLeftPanel");
		rightCardPanel.add(cpRight, cardName + "right");

		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm3().getComparison()
				.getRightoperatorattributesleft().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "RightOperatorLeftPanel";
				currentSelectedRightOperatorLeftAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cpRight);
				}
			}
		}

		rightCardPanel.validate();
		rightCardPanel.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method renders right arithmetic operator right panel
	 * */
	private void setRightComponentAttributeRight(String cardName) {
		rightCardPanelAttributeRight.removeAll();
		rightCardPanelAttributeRight.validate();
		rightCardPanelAttributeRight.repaint();

		JPanel cpRight = new JPanel(new GridBagLayout());
		cpRight.setName("RightOperatorRightPanel");
		rightCardPanelAttributeRight.add(cpRight, cardName + "right");

		setRow(0);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Attribute attribute : formGenerator.getForm3().getComparison()
				.getRightoperatorattributesright().getAttribute()) {
			String attributeName = attribute.getName();
			if (syntaxMap.containsKey(attributeName)) {
				languageEntry = (LanguageEntry) syntaxMap.get(attributeName);
				attributeName = languageEntry.getLabel();
			}
			if (attributeName.equals(cardName)) {
				currentPanel = "RightOperatorRightPanel";
				currentSelectedRightOperatorRightAttribute = attribute;
				for (Option option : attribute.getParameters().getOption()) {
					addComponent(option, constraints, cpRight);
				}
			}
		}

		rightCardPanelAttributeRight.validate();
		rightCardPanelAttributeRight.repaint();
		scrollBar.validate();
		scrollBar.repaint();
	}

	/*
	 * This method adds an option to the panel
	 */
	public void addComponent(final Option option,
			GridBagConstraints constraints, final JPanel panel) {
		// retrieving option properties
		String type = option.getType();
		String name = option.getName();
		String labelText = option.getLabel();
		String tooltip = option.getTooltip();
		String defaultOption = option.getDefaultOption();

		String leftComponentText = labelText;
		String tooltipText = tooltip;
		if (syntaxMap.containsKey(name)) {
			languageEntry = (LanguageEntry) syntaxMap.get(name);
			leftComponentText = languageEntry.getLabel();
			tooltipText = languageEntry.getTooltip();
		}
		String defaultOptionText = defaultOption;
		if (syntaxMap.containsKey(defaultOptionText)) {
			languageEntry = (LanguageEntry) syntaxMap.get(defaultOptionText);
			defaultOptionText = languageEntry.getLabel();
		}

		JLabel leftComponent = new JLabel(leftComponentText);
		leftComponent.setName(name + "Label");

		Component rightComponent = null;
		// option's type is text	
		if (type.equalsIgnoreCase("text")) {
			JTextField textField = new JTextField(defaultOption);
			textField.setToolTipText(tooltipText);
			textField.setName(name);
			textField.addKeyListener(new KeyListener() {
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// current text is stored in option object
					option.setDefaultOption(((JTextField) e.getSource())
							.getText());
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
				}
			});
			rightComponent = textField;
		} 
		// option's type is dropdown / spinner
		else if (type.equalsIgnoreCase("dropdown")
				|| type.equalsIgnoreCase("spinner")) {

			JComboBox comboBox = new JComboBox(getOptions(name).toArray());
			comboBox.setName(name);
			comboBox.setToolTipText(tooltipText);
			comboBox.setSelectedItem(defaultOptionText);
			option.setDefaultOption(defaultOptionText);
			comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// dropdown / spinner selected item is changed
					JComboBox combo = (JComboBox) e.getSource();
					if (combo.getSelectedItem() != null) {
						String selectedItemName = combo.getSelectedItem()
								.toString();
						if (syntaxMapLabelToName.containsKey(selectedItemName)) {
							selectedItemName = (String) syntaxMapLabelToName
									.get(selectedItemName);
						}
						// updating selected item in option object
						option.setDefaultOption(selectedItemName);
						// removing all components from this panel
						panel.removeAll();
						panel.validate();
						panel.repaint();

						//updating specific panel
						if (panel.getName().equals("LeftOperatorLeftPanel")) {
							setLeftComponent(comboBoxOptionLeft
									.getSelectedItem().toString());
						} else if (panel.getName().equals(
								"LeftOperatorRightPanel")) {
							setLeftComponentAttributeRight(comboBoxOptionLeftAttributeRight
									.getSelectedItem().toString());
						} else if (panel.getName().equals(
								"RightOperatorLeftPanel")) {
							setRightComponent(comboBoxOptionRight
									.getSelectedItem().toString());
						} else if (panel.getName().equals(
								"RightOperatorRightPanel")) {
							setRightComponentAttributeRight(comboBoxOptionRightAttributeRight
									.getSelectedItem().toString());
						}
					}
				}
			});
			rightComponent = comboBox;
		} 
		// option's type is checkbox
		else if (type.equalsIgnoreCase("check")) {
			JCheckBox checkBox = new JCheckBox();
			checkBox.setToolTipText(tooltipText);
			checkBox.setName(name);
			if (defaultOption.equals("checked")) {
				checkBox.setSelected(true);
			}
			checkBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					AbstractButton abstractButton = (AbstractButton) e
							.getSource();
					//updating option object
					boolean selected = abstractButton.getModel().isSelected();
					if (((JCheckBox) e.getSource()).isSelected()) {
						option.setDefaultOption("checked");
						panel.removeAll();
					} else {
						option.setDefaultOption("unchecked");
						panel.removeAll();
					}
					// removing all components from this panel
					panel.removeAll();
					panel.validate();
					panel.repaint();
					//updating specific panel
					if (panel.getName().equals("LeftOperatorLeftPanel")) {
						setLeftComponent(comboBoxOptionLeft.getSelectedItem()
								.toString());
					} else if (panel.getName().equals("LeftOperatorRightPanel")) {
						setLeftComponentAttributeRight(comboBoxOptionLeftAttributeRight
								.getSelectedItem().toString());
					} else if (panel.getName().equals("RightOperatorLeftPanel")) {
						setRightComponent(comboBoxOptionRight.getSelectedItem()
								.toString());
					} else if (panel.getName()
							.equals("RightOperatorRightPanel")) {
						setRightComponentAttributeRight(comboBoxOptionRightAttributeRight
								.getSelectedItem().toString());
					}
				}
			});

			rightComponent = checkBox;
		}
		//adding this option to the panel
		if (leftComponent != null && rightComponent != null) {

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = getRow();
			panel.add(new JLabel("	"), constraints);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 1;
			constraints.gridy = getRow();
			panel.add(new JLabel("	"), constraints);

			setRow(getRow() + 1);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 0;
			constraints.gridy = getRow();
			panel.add(leftComponent, constraints);

			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.gridx = 1;
			constraints.gridy = getRow();
			panel.add(rightComponent, constraints);

			setRow(getRow() + 1);

		}
		// if an option has sub option then we are adding sub option to the panel
		if (option.getListSubOptions().size() > 0) {
			for (ListSubOptions listSuboption : option.getListSubOptions()) {
				if (listSuboption.getName().equals(defaultOption)) {
					for (Option subOption : listSuboption.getSubOption()) {
						addComponent(subOption, constraints, panel);
					}
				}
			}
		}
		pack();

	}

	/*
	 * This method returns options for combo box / spinner
	 */
	public List<String> getOptions(String name) {
		List<String> options = new ArrayList<String>();
		Attribute currentAttribute = null;
		if (currentPanel == "LeftOperatorLeftPanel") {
			currentAttribute = currentSelectedLeftOperatorLeftAttribute;
		} else if (currentPanel == "LeftOperatorRightPanel") {
			currentAttribute = currentSelectedLeftOperatoRightAttribute;
		} else if (currentPanel == "RightOperatorLeftPanel") {
			currentAttribute = currentSelectedRightOperatorLeftAttribute;
		} else if (currentPanel == "RightOperatorRightPanel") {
			currentAttribute = currentSelectedRightOperatorRightAttribute;
		}

		if (currentAttribute != null) {
			for (Option option : currentAttribute.getParameters().getOption()) {
				options = getOptionsAndSub(option, name);

				if (options.size() > 0) {
					return options;
				}
			}
		}
		return options;
	}

	/*
	 * This method checks sub options to get options for combo box / spinner
	 */
	public List<String> getOptionsAndSub(Option option, String name) {
		List<String> options = new ArrayList<String>();
		if (option.getName().equals(name)) {
			for (DropDownOption dropDownOption : option.getDropdownOption()) {
				// this is a drop down option
				for (DropDownOptionElement dropDownOptionElement : dropDownOption
						.getOption()) {
					//adding variable as dropdown element from variable list
					if (dropDownOptionElement.getAs().equals("showvars")) 
					{
						for(int counter = 0 ; counter < variables.length ; counter++)
						{
							options.add(variables[counter]);
						}
					} 
					//adding dropdown element defined in xml file					
					else {
						String dropDownElementText = dropDownOptionElement
								.getAs();
						if (syntaxMap.containsKey(dropDownElementText)) {
							languageEntry = (LanguageEntry) syntaxMap
									.get(dropDownElementText);
							dropDownElementText = languageEntry.getLabel();
						}
						options.add(dropDownElementText);
					}
				}
			}
			for (SpinnerOption spinnerOption : option.getSpinnerOption()) {
				// this is a spinner option
				int min = 0;
				int max = 0;
				boolean hasShowVars = false;
				for (SpinnerOptionElement spinnerOptionElement : spinnerOption
						.getOption()) {
					if (spinnerOptionElement.getMin() > 0) {
						min = spinnerOptionElement.getMin();
					}
					if (spinnerOptionElement.getMax() > 0) {
						max = spinnerOptionElement.getMax();
					}
					if(spinnerOptionElement.getAs()!= null && spinnerOptionElement.getAs().equals("showvars"))
					{
						hasShowVars = true;
					}
				}
				for (int i = min; i <= max; i++) {
					options.add(i + "");
				}
				//adding variable as spinner element from variable list
				if(hasShowVars)
				{
					for(int counter = 0 ; counter < variables.length ; counter++)
					{
						options.add(variables[counter]);
					}
				}
			}
			return options;
		} 
		// checking sup options
		else if (option.getListSubOptions().size() > 0) {
			for (ListSubOptions listSuboption : option.getListSubOptions()) {
				for (Option subOption : listSuboption.getSubOption()) {
					if (subOption.getName().equals(name)) {
						return getOptionsAndSub(subOption, name);
					}
				}
			}
		}
		return options;
	}
}
