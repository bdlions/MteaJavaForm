import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.forms.Form1;
import org.forms.FormGenerator;
import org.forms.form1.dropdown.DropDownOption;
import org.forms.form1.dropdown.DropDownOptionElement;
import org.forms.form1.listoptions.Option;
import org.forms.form1.spinner.SpinnerOption;
import org.forms.form1.spinner.SpinnerOptionElement;
import org.forms.languages.LanguageEntry;

public class Form1Window extends JFrame {

	private JTextField textFieldLotReduction;
	private FormGenerator formGenerator;
	private JPanel contentPane;
	private JPanel panel;
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
					//Form1Window frame = new Form1Window("English"); 
					Form1Window frame = new Form1Window("Francais");
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
	public Form1Window(String language) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel = new JPanel(new GridBagLayout());

		// Creating group layout
		contentPane.setPreferredSize(new Dimension(500, 250));

		JScrollPane scrollBar = new JScrollPane();
		scrollBar
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollBar
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollBar, BorderLayout.CENTER);
		scrollBar.setViewportView(panel);

		// setting language in formgenerator
		formGenerator = new FormGenerator();
		formGenerator.setLanguage(language);
		formGenerator.generateForm1();

		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();

		LanguageEntry titleEntry = (LanguageEntry) syntaxMap.get("title");
		String title = titleEntry.getLabel();
		setTitle(title);

		JPanel buttonPanel = new JPanel();

		titleEntry = (LanguageEntry) syntaxMap.get("ok");
		String okLabel = titleEntry.getLabel();
		JButton okButton = new JButton(okLabel);
		
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Form1Output from1Output = new Form1Output(formGenerator);
				from1Output.setVisible(true);
				from1Output.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		titleEntry = (LanguageEntry) syntaxMap.get("cancel");
		String cancelLabel = titleEntry.getLabel();
		JButton cancelButton = new JButton(cancelLabel);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				disposePanel();
			}
		});

		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		contentPane.add(buttonPanel, BorderLayout.SOUTH);

		GridBagConstraints constraints = new GridBagConstraints();
		for (Option option : formGenerator.getForm1().getListOptions()
				.getOption()) {
			addComponent(option, constraints, panel);
		}
	}

	public void disposePanel() {
		this.dispose();
	}

	public List<String> getOptions(String name) {
		List<String> options = new ArrayList<String>();

		Form1 form1 = formGenerator.getForm1();

		for (Option option : form1.getListOptions().getOption()) {
			options = getOptionsAndSub(option, name);

			if (options.size() > 0) {
				return options;
			}
		}

		return options;
	}

	public List<String> getOptionsAndSub(Option option, String name) {
		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();
		LanguageEntry languageEntry = (LanguageEntry) syntaxMap.get(name);

		List<String> options = new ArrayList<String>();
		if (option.getName().equals(name)) {
			for (DropDownOption dropDownOption : option.getDropdownOption()) {
				for (DropDownOptionElement dropDownOptionElement : dropDownOption
						.getOption()) {
					if (syntaxMap.containsKey(dropDownOptionElement.getAs())) {
						languageEntry = (LanguageEntry) syntaxMap
								.get(dropDownOptionElement.getAs());
						options.add(languageEntry.getLabel());
					}
					if (dropDownOptionElement.getAs().equals("showvars")) {
						options.add("will add some variables later");
					}
				}
			}
			for (SpinnerOption spinnerOption : option.getSpinnerOption()) {
				int min = 0;
				int max = 0;
				for (SpinnerOptionElement spinnerOptionElement : spinnerOption
						.getOption()) {
					if (spinnerOptionElement.getMin() > 0) {
						min = spinnerOptionElement.getMin();
					}
					if (spinnerOptionElement.getMax() > 0) {
						max = spinnerOptionElement.getMax();
					}
				}
				for (int i = min; i <= max; i++) {
					options.add(i + "");
				}
				// if(spinnerOptionElement.getAs() != null &&
				// spinnerOptionElement.getAs().equals("showvars"))
				{
					options.add("will add some variables later");
				}
			}
			return options;
		} else if (option.getSuboption().size() > 0) {
			for (Option subOption : option.getSuboption()) {
				return getOptionsAndSub(subOption, name);
			}
		}

		return options;
	}

	public void addComponent(final Option option,
			GridBagConstraints constraints, final JPanel panel) {
		String type = option.getType();
		String name = option.getName();
		String labelText = option.getLabel();
		String tooltip = option.getTooltip();
		String defaultOption = option.getDefaultOption();

		Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();

		LanguageEntry languageEntry = (LanguageEntry) syntaxMap.get(name);
		JLabel leftComponent = new JLabel(languageEntry.getLabel());
		leftComponent.setName(name + "Label");

		Component rightComponent = null;

		if (type.equalsIgnoreCase("text")) {
			JTextField textField = new JTextField(defaultOption);
			textField.setToolTipText(tooltip);
			textField.setName(name);
			textField.addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					option.setDefaultOption(((JTextField) e.getSource())
							.getText());
				}

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub

				}
			});
			rightComponent = textField;
		} else if (type.equalsIgnoreCase("dropdown")
				|| type.equalsIgnoreCase("spinner")) {
			JComboBox comboBox = new JComboBox(getOptions(name).toArray());
			comboBox.setName(name);
			comboBox.setToolTipText(languageEntry.getTooltip());
			comboBox.setSelectedItem(defaultOption);
			
			comboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Hashtable syntaxMap = formGenerator.getSyntaxMapForm1();

					JComboBox combo = (JComboBox) e.getSource();
					String comboName = combo.getName();
					if(combo.getSelectedItem() != null)
					option.setDefaultOption(combo.getSelectedItem().toString());
					for (Option option : formGenerator.getForm1()
							.getListOptions().getOption()) {
						if (option.getName().equals(comboName)) {
							option.setDefaultOption(combo.getSelectedItem().toString());
							Option newSubOption = null;
							JComboBox subOptionCombo = null;
							JLabel subOptionLable = null;
							for (Option subOption : option.getSuboption()) {

								LanguageEntry languageEntry = (LanguageEntry) syntaxMap
										.get(subOption.getName());
								if (languageEntry.getLabel().equals(
										combo.getSelectedItem().toString())) {
									newSubOption = subOption;
									combo.setName(option.getName());
									option.setDefaultOption(subOption.getName());
								}
								for (Component component : panel
										.getComponents()) {
									if (subOption.getName().equals(
											component.getName())) {
										subOptionCombo = (JComboBox) component;
									}

									if ((subOption.getName() + "Label")
											.equals(component.getName())) {
										subOptionLable = (JLabel) component;
									}
								}
							}

							if (newSubOption != null) {
								subOptionCombo.removeAllItems();

								for (DropDownOption dropDownOption : newSubOption
										.getDropdownOption()) {
									for (DropDownOptionElement dropDownOptionElement : dropDownOption
											.getOption()) {
										String comboElementName = dropDownOptionElement
												.getAs();
										if (comboElementName != "") {
											LanguageEntry languageEntryCombo = (LanguageEntry) syntaxMap
													.get(dropDownOptionElement
															.getAs());
											if (languageEntryCombo.getLabel() != null
													|| languageEntryCombo
															.getLabel() != "") {
												comboElementName = languageEntryCombo
														.getLabel();
											}
										}
										subOptionCombo
												.addItem(comboElementName);
									}
								}
								if (subOptionLable != null) {
									subOptionLable.setName(newSubOption
											.getName() + "Label");
									subOptionLable.setText(combo
											.getSelectedItem().toString());
									subOptionLable.revalidate();
								}

								String defaultOption = newSubOption
										.getDefaultOption();
								if (defaultOption != "") {
									LanguageEntry defaultOptionEntry = (LanguageEntry) syntaxMap
											.get(newSubOption
													.getDefaultOption());
									defaultOption = defaultOptionEntry
											.getLabel();
								}

								subOptionCombo.setName(newSubOption.getName());
								subOptionCombo.setToolTipText(newSubOption
										.getTooltip());
								subOptionCombo.setSelectedItem(defaultOption);

								subOptionCombo.revalidate();
							}
						}

					}
				}
			});
			rightComponent = comboBox;
		} else if (type.equalsIgnoreCase("check")) {
			JCheckBox checkBox = new JCheckBox();
			checkBox.setToolTipText(tooltip);

			if (defaultOption.equals("checked")) {
				checkBox.setSelected(true);
			}
			checkBox.addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					// TODO Auto-generated method stub
					if (((JCheckBox) e.getSource()).isSelected()) {
						option.setDefaultOption("checked");
					} else {
						option.setDefaultOption("unchecked");
					}
				}
			});

			rightComponent = checkBox;
		}

		if (leftComponent != null && rightComponent != null) {

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

		if (option.getSuboption().size() > 0) {
			for (Option subOption : option.getSuboption()) {
				if (subOption.getName().equals(defaultOption)) {
					addComponent(subOption, constraints, panel);
				}
			}
		}
	}
	
}
