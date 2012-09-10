package org.forms.form2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"parameters" }, namespace = "attribute")
public class Attribute 
{
	
	private String name;
	private String i8n_entry;
	
	private Parameters parameters;
	
	@XmlAttribute(namespace="i8n-entry")
	public String getI8n_entry() {
		return i8n_entry;
	}
	public void setI8n_entry(String i8n_entry) {
		this.i8n_entry = i8n_entry;
	}
	
	@XmlAttribute(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
	public Parameters getParameters() {
		return parameters;
	}
}
