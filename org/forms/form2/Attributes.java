package org.forms.form2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.forms.form1.listoptions.Option;

@XmlType(propOrder = {"attribute"})
public class Attributes implements Cloneable
{
	private List<Attribute> attribute = new ArrayList<Attribute>();
	
	public Object clone(){
		try{
			Attributes cloned = (Attributes)super.clone();
		    cloned.attribute = attributeCloneList(attribute);
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	public List<Attribute> getAttribute() {
		return attribute;
	}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}
	
	public static List<Attribute> attributeCloneList(List<Attribute> list) {
	    List<Attribute> clone = new ArrayList<Attribute>(list.size());
	    for(Attribute item: list) 
	    	clone.add((Attribute)item.clone());
	    return clone;
	}
}
