package org.forms.form2;

import javax.xml.bind.annotation.XmlType;

import org.forms.Form2;

@XmlType(propOrder = {"operators", "attributesleft", "attributesright" }, namespace = "comparison")
public class Comparison implements Cloneable
{
	private Operators operators;
	private Attributes attributesleft = new Attributes();
	private Attributes attributesright = new Attributes();
	
	public Object clone(){
		try{
			Comparison cloned = (Comparison)super.clone();
		    cloned.attributesleft = (Attributes) attributesleft.clone();
		    cloned.attributesright = (Attributes) attributesright.clone();
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
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
