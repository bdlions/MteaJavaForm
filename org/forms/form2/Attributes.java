package org.forms.form2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"attribute"})
public class Attributes 
{
	private List<Attribute> attribute = new ArrayList<Attribute>();
	public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}
}
