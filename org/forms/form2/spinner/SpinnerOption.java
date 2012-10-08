package org.forms.form2.spinner;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"option" }, name = "spinnerOption")
public class SpinnerOption {
	private List<SpinnerOptionElement> option = new ArrayList<SpinnerOptionElement>();
	public void setOption(List<SpinnerOptionElement> option) {
		this.option = option;
	}
	public List<SpinnerOptionElement> getOption() {
		return option;
	}
}
