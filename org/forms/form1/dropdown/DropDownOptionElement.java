package org.forms.form1.dropdown;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "option")
public class DropDownOptionElement 
{
	private String as;
	
	@XmlAttribute(name="as")
	public String getAs() {
		return as;
	}
	
	public void setAs(String as) {
		this.as = as;
	}
}
