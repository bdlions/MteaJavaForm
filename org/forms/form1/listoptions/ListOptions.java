package org.forms.form1.listoptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"option"}, namespace = "listOptions")
public class ListOptions 
{
	private List<Option> option = new ArrayList<Option>() ;
	public List<Option> getOption() {
		return option;
	}
	public void setOption(List<Option> option) {
		this.option = option;
	}
}
