package org.forms.form2.parameters;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;
import org.forms.form2.dropdown.DropDownOption;
import org.forms.form2.spinner.SpinnerOption;


@XmlType(propOrder = { "dropdownOption", "spinnerOption", "listSubOptions"}, namespace = "option")
public class Option implements Cloneable
{
	private String name;
	private String label;
	private String type;
	private String defaultOption;
	private String tooltip;
	private String i8n_entry;
	
	private ArrayList<DropDownOption> dropdownOption = new ArrayList<DropDownOption>();
	private ArrayList<SpinnerOption> spinnerOption = new ArrayList<SpinnerOption>();
	private ArrayList<ListSubOptions> listSubOptions = new ArrayList<ListSubOptions>();
	
	public Object clone(){
		try{
			Option cloned = (Option)super.clone();
			cloned.name = name;
			cloned.label = label;
			cloned.type = type;
			cloned.defaultOption = defaultOption;
			cloned.tooltip = tooltip;
			cloned.listSubOptions = listSubOptionsCloneList(listSubOptions);
			return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	@XmlAttribute(name="i8n-entry")
	public String getI8n_entry() {
		return i8n_entry;
	}
	
	@XmlAttribute(name="defaultOption")
	public String getDefaultOption() {
		return defaultOption;
	}
		
	@XmlAttribute(name="label")
	public String getLabel() {
		return label;
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	@XmlAttribute(name="tooltip")
	public String getTooltip() {
		return tooltip;
	}
	@XmlAttribute(name="type")
	public String getType() {
		return type;
	}

	public void setI8n_entry(String i8n_entry) {
		this.i8n_entry = i8n_entry;
	}
	public void setDefaultOption(String defaultOption) {
		this.defaultOption = defaultOption;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDropdownOption(ArrayList<DropDownOption> dropdownOption) {
		this.dropdownOption = dropdownOption;
	}
	public void setSpinnerOption(ArrayList<SpinnerOption> spinnerOption) {
		this.spinnerOption = spinnerOption;
	}
	public ArrayList<DropDownOption> getDropdownOption() {
		return dropdownOption;
	}
	public ArrayList<SpinnerOption> getSpinnerOption() {
		return spinnerOption;
	}
	
	public void setListSubOptions(ArrayList<ListSubOptions> listSubOptions) {
		this.listSubOptions = listSubOptions;
	}
	
	public ArrayList<ListSubOptions> getListSubOptions() {
		return listSubOptions;
	}
	public static ArrayList<ListSubOptions> listSubOptionsCloneList(ArrayList<ListSubOptions> list) {
		ArrayList<ListSubOptions> clone = new ArrayList<ListSubOptions>(list.size());
	    for(ListSubOptions item: list) 
	    	clone.add((ListSubOptions)item.clone());
	    return clone;
	}
}
