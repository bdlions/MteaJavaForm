package org.forms.form2;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"operators", "attributesleft", "attributesright" }, namespace = "comparison")
public class Comparison 
{
	private Operators operators;
	private Attributes attributesleft;
	private Attributes attributesright;
	
	public Attributes getAttributesleft() {
		return attributesleft;
	}
	public Attributes getAttributesright() {
		return attributesright;
	}
	public Operators getOperators() {
		return operators;
	}
	public void setAttributesleft(Attributes attributesleft) {
		this.attributesleft = attributesleft;
	}
	public void setAttributesright(Attributes attributesright) {
		this.attributesright = attributesright;
	}
	public void setOperators(Operators operators) {
		this.operators = operators;
	}
}
