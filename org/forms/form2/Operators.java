package org.forms.form2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"op" },namespace = "operators")
public class Operators
{
	private List<Operand> op = new ArrayList<Operand>();
	
	public List<Operand> getOp() {
		return op;
	}
	public void setOp(List<Operand> op) {
		this.op = op;
	}
}
