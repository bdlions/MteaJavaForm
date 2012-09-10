package org.forms.form1.spinner;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(namespace = "option")
public class SpinnerOptionElement 
{
	private String as;
	private int min;
	private int max;
	
	@XmlAttribute(name="as")
	public String getAs() {
		return as;
	}
	
	public void setAs(String as) {
		this.as = as;
	}
	
	@XmlAttribute(name="max")
	public int getMax() {
		return max;
	}
	@XmlAttribute(name="min")
	public int getMin() {
		return min;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setMin(int min) {
		this.min = min;
	}
	
}
