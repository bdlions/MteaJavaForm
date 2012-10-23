package org.forms.form1.listoptions;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.forms.Form1;

@XmlType(propOrder = {"option"}, namespace = "listOptions")
public class ListOptions implements Cloneable
{
	private List<Option> option = new ArrayList<Option>() ;
	
	public Object clone(){
		try{
			ListOptions cloned = (ListOptions)super.clone();
			cloned.option = optionCloneList(option);
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	public List<Option> getOption() {
		return option;
	}
	public void setOption(List<Option> option) {
		this.option = option;
	}
	
	public static List<Option> optionCloneList(List<Option> list) {
	    List<Option> clone = new ArrayList<Option>(list.size());
	    for(Option item: list) 
	    	clone.add((Option)item.clone());
	    return clone;
	}

}
