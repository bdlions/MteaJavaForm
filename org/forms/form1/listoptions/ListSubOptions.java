package org.forms.form1.listoptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ListSubOptions 
{
	
	private String name;
	private List<Option> subOption = new ArrayList<Option>();
	
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
	
}
