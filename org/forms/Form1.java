package org.forms;
import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import org.forms.form1.listoptions.ListOptions;
import org.forms.languages.Languages;


@XmlType(propOrder = {"listOptions", "languages" }, namespace = "myform1")
@XmlRootElement(name="myform1")
public class Form1 
{
	private String title;
	private ListOptions listOptions = new ListOptions();
	private Languages languages = new Languages();

	public String getTitle() {
		return title;
	}
	
	@XmlAttribute(name="title")
	public void setTitle(String title) {
		this.title = title;
	}

	public void setListOptions(ListOptions listOptions) {
		this.listOptions = listOptions;
	}
	public ListOptions getListOptions() {
		return listOptions;
	}
	public Languages getLanguages() {
		return languages;
	}
	public void setLanguages(Languages languages) {
		this.languages = languages;
	}
}