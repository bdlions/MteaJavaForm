package org.forms.form2;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;
import org.forms.form2.parameters.Option;

@XmlType(propOrder = {"option"}, namespace = "parameters")
public class Parameters 
{
	private List<Option> option = new ArrayList<Option>() ;
	public List<Option> getOption() {
		return option;
	}
	public void setOption(List<Option> option) {
		this.option = option;
	}
}