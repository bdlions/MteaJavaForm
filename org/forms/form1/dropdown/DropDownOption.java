package org.forms.form1.dropdown;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"option" }, name = "dropdownOption")
public class DropDownOption
{
	private List<DropDownOptionElement> option = new ArrayList<DropDownOptionElement>();
	public void setOption(List<DropDownOptionElement> option) {
		this.option = option;
	}
	public List<DropDownOptionElement> getOption() {
		return option;
	}
}
