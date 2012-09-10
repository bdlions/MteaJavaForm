package org.forms.form2;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlType;

import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;

@XmlType(propOrder = { "dropdownOption", "spinnerOption"}, namespace = "parameters")
public class Parameters 
{
	private ArrayList<DropDownOption> dropdownOption = new ArrayList<DropDownOption>();
	private ArrayList<SpinnerOption> spinnerOption = new ArrayList<SpinnerOption>();
	
	public ArrayList<DropDownOption> getDropdownOption() {
		return dropdownOption;
	}
	public ArrayList<SpinnerOption> getSpinnerOption() {
		return spinnerOption;
	}
	public void setDropdownOption(ArrayList<DropDownOption> dropdownOption) {
		this.dropdownOption = dropdownOption;
	}
	public void setSpinnerOption(ArrayList<SpinnerOption> spinnerOption) {
		this.spinnerOption = spinnerOption;
	}
}
