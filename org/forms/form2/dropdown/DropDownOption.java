package org.forms.form2.dropdown;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.forms.form1.dropdown.DropDownOptionElement;

@XmlType(propOrder = {"option" }, namespace = "dropdownOption")
public class DropDownOption
{
	private String name;
	private String i8n_entry;
	private String defaultValue;
	private String tooltip;
	
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	@XmlAttribute(name="default")
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setI8n_entry(String i8n_entry) {
		this.i8n_entry = i8n_entry;
	}
	@XmlAttribute(name="i8n-entry")
	public String getI8n_entry() {
		return i8n_entry;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	@XmlAttribute(name="tooltip")
	public String getTooltip() {
		return tooltip;
	}
	
	
	private List<DropDownOptionElement> option = new ArrayList<DropDownOptionElement>();
	public void setOption(List<DropDownOptionElement> option) {
		this.option = option;
	}
	public List<DropDownOptionElement> getOption() {
		return option;
	}
}
