package org.forms.form3;

import javax.xml.bind.annotation.XmlType;
import org.forms.form2.*;
@XmlType(propOrder = {"arithmaticoperators", "operators", "leftoperatorattributesleft", "leftoperatorattributesright", "rightoperatorattributesleft", "rightoperatorattributesright"}, namespace = "comparison")
public class Comparison implements Cloneable
{
	private Operators arithmaticoperators;
	private Operators operators;
	private Attributes leftoperatorattributesleft = new Attributes();
	private Attributes leftoperatorattributesright = new Attributes();
	private Attributes rightoperatorattributesleft = new Attributes();
	private Attributes rightoperatorattributesright = new Attributes();
	
	public Object clone(){
		try{
			Comparison cloned = (Comparison)super.clone();
		    cloned.leftoperatorattributesleft = (Attributes) leftoperatorattributesleft.clone();
		    cloned.leftoperatorattributesright = (Attributes) leftoperatorattributesright.clone();
		    cloned.rightoperatorattributesleft = (Attributes) rightoperatorattributesleft.clone();
		    cloned.rightoperatorattributesright = (Attributes) rightoperatorattributesright.clone();
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	public Operators getArithmaticoperators() {
		return arithmaticoperators;
	}
	public Operators getOperators() {
		return operators;
	}
	public Attributes getLeftoperatorattributesleft() {
		return leftoperatorattributesleft;
	}
	public Attributes getLeftoperatorattributesright() {
		return leftoperatorattributesright;
	}
	public Attributes getRightoperatorattributesleft() {
		return rightoperatorattributesleft;
	}
	public Attributes getRightoperatorattributesright() {
		return rightoperatorattributesright;
	}
	public void setArithmaticoperators(Operators arithmaticoperators) {
		this.arithmaticoperators = arithmaticoperators;
	}
	public void setOperators(Operators operators) {
		this.operators = operators;
	}
	public void setLeftoperatorattributesleft(
			Attributes leftoperatorattributesleft) {
		this.leftoperatorattributesleft = leftoperatorattributesleft;
	}
	public void setLeftoperatorattributesright(
			Attributes leftoperatorattributesright) {
		this.leftoperatorattributesright = leftoperatorattributesright;
	}
	public void setRightoperatorattributesleft(
			Attributes rightoperatorattributesleft) {
		this.rightoperatorattributesleft = rightoperatorattributesleft;
	}
	public void setRightoperatorattributesright(
			Attributes rightoperatorattributesright) {
		this.rightoperatorattributesright = rightoperatorattributesright;
	}
	
}
