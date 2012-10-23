package org.forms.form2.parameters;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.forms.form2.parameters.ListSubOptions;
import org.forms.form2.parameters.Option;

@XmlType
public class ListSubOptions implements Cloneable
{
	
	private String name;
	private List<Option> subOption = new ArrayList<Option>();
	
	public Object clone(){
		try{
			ListSubOptions cloned = (ListSubOptions)super.clone();
			cloned.subOption = optionCloneList(subOption);
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="suboption")
	public List<Option> getSubOption() {
		return subOption;
	}
	public void setSubOption(List<Option> subOption) {
		this.subOption = subOption;
	}
	public static List<Option> optionCloneList(List<Option> list) {
	    List<Option> clone = new ArrayList<Option>(list.size());
	    for(Option item: list) 
	    	clone.add((Option)item.clone());
	    return clone;
	}
}
