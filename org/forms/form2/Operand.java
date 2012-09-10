package org.forms.form2;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "op")
public class Operand 
{
	
	private String name;
	private String symbol;
	private String i8n_entry;
	
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
	
	@XmlAttribute(name="symbol")
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	
	
}
