package org.forms;

import javax.xml.bind.annotation.*;

import org.forms.form3.Comparison;
import org.forms.languages.Languages;

@XmlType(propOrder = {"comparison", "languages" }, namespace = "myform3")
@XmlRootElement(name="myform3")
public class Form3 
{
	private String title;
	private Comparison comparison = new Comparison();
	private Languages languages = new Languages();

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