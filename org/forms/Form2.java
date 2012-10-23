package org.forms;

import javax.xml.bind.annotation.*;

import org.forms.form1.listoptions.ListOptions;
import org.forms.form2.Comparison;
import org.forms.languages.Languages;

@XmlType(propOrder = {"comparison", "languages" }, namespace = "myform2")
@XmlRootElement(name="myform2")
public class Form2 implements Cloneable
{
	private String title;
	private Comparison comparison = new Comparison();
	private Languages languages = new Languages();

	public Object clone(){
		try{
			Form2 cloned = (Form2)super.clone();
		    cloned.title = title;
		    cloned.comparison = (Comparison) comparison.clone();
		    return cloned;
	    }
	    catch(CloneNotSupportedException e){
		    System.out.println(e);
		    return null;
	    }
	}
	
	public String getTitle() {
		return title;
	}
	
	@XmlAttribute(name="title")
	public void setTitle(String title) {
		this.title = title;
	}

	public Comparison getComparison() {
		return comparison;
	}
	
	public void setComparison(Comparison comparison) {
		this.comparison = comparison;
	}
	
	public Languages getLanguages() {
		return languages;
	}
	public void setLanguages(Languages languages) {
		this.languages = languages;
	}
}