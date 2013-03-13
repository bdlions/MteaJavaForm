package org.forms;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.forms.languages.Language;
import org.forms.languages.LanguageEntry;
import java.util.*;
public class FormGenerator implements Cloneable
{
	private String language = "English";
	private Hashtable syntaxMapForm1 = new Hashtable();
	private Hashtable syntaxMapLabelToNameForm1 = new Hashtable();
	private Hashtable syntaxMapForm2 = new Hashtable();
	private Hashtable syntaxMapLabelToNameForm2 = new Hashtable();
	private Hashtable syntaxMapForm3 = new Hashtable();
	private Hashtable syntaxMapLabelToNameForm3 = new Hashtable();
	private Form1 form1 = new Form1();
	private Form2 form2 = new Form2();
	private Form3 form3 = new Form3();
	
	
	public Object clone(){
	  try{
		  FormGenerator cloned = (FormGenerator)super.clone();
		  cloned.language = language;
		  cloned.syntaxMapForm1 = (Hashtable) syntaxMapForm1.clone();
		  cloned.syntaxMapLabelToNameForm1 = (Hashtable) syntaxMapLabelToNameForm1.clone();
		  cloned.syntaxMapForm2 = (Hashtable) syntaxMapForm2.clone();
		  cloned.syntaxMapLabelToNameForm2 = (Hashtable) syntaxMapLabelToNameForm2.clone();
		  cloned.syntaxMapForm3 = (Hashtable) syntaxMapForm3.clone();
		  cloned.syntaxMapLabelToNameForm3 = (Hashtable) syntaxMapLabelToNameForm3.clone();
		  cloned.form1 = (Form1) form1.clone();
		  cloned.form2 = (Form2)form2.clone();
		  cloned.form3 = (Form3)form3.clone();;
		  //cloned.form1 = (Form1) form1.clone();
		  return cloned;
	  }
	  catch(CloneNotSupportedException e){
		  System.out.println(e);
		  return null;
	  }
	}
	public void setSyntaxMapLabelToNameForm2(Hashtable syntaxMapLabelToNameForm2) {
		this.syntaxMapLabelToNameForm2 = syntaxMapLabelToNameForm2;
	}
	
	public void setSyntaxMapLabelToNameForm3(Hashtable syntaxMapLabelToNameForm3) {
		this.syntaxMapLabelToNameForm3 = syntaxMapLabelToNameForm3;
	}
	
	public Hashtable getSyntaxMapLabelToNameForm2() {
		return syntaxMapLabelToNameForm2;
	}
	
	public Hashtable getSyntaxMapLabelToNameForm3() {
		return syntaxMapLabelToNameForm3;
	}
	
	public void setSyntaxMapLabelToNameForm1(Hashtable syntaxMapLabelToNameForm1) {
		this.syntaxMapLabelToNameForm1 = syntaxMapLabelToNameForm1;
	}
	
	public Hashtable getSyntaxMapLabelToNameForm1() {
		return syntaxMapLabelToNameForm1;
	}
	
	public Form1 getForm1() {
		return form1;
	}
	
	public Form2 getForm2() {
		return form2;
	}
	
	public Form3 getForm3() {
		return form3;
	}
	
	public Hashtable getSyntaxMapForm1() {
		return syntaxMapForm1;
	}
	
	public Hashtable getSyntaxMapForm2() {
		return syntaxMapForm2;
	}
	public Hashtable getSyntaxMapForm3() {
		return syntaxMapForm3;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	
	/*
	 * This method loads form1 xml file and stores into object
	 * */
	public void generateForm1(String form1XmlName)
	{
		try 
		{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/"+form1XmlName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Form1.class);
			 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			form1 = (Form1) jaxbUnmarshaller.unmarshal(inputStream);
			
			syntaxMapForm1 = new Hashtable();
			syntaxMapLabelToNameForm1 = new Hashtable();
			List<Language> languages =  form1.getLanguages().getLanguage();
			for ( Language language : languages) 
			{
				if(getLanguage().equalsIgnoreCase(language.getName()))
				{
					for(LanguageEntry entry: language.getEntry())
					{
						syntaxMapForm1.put(entry.getName(), entry);
						syntaxMapLabelToNameForm1.put(entry.getLabel(), entry.getName());
					}
				}
			}
		} 
		catch (JAXBException exception) 
		{
			
		}
	}	
	
	/*
	 * This method loads form2 xml file and stores into object
	 * */
	public void generateForm2(String form2XmlName)
	{
		try 
		{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/"+form2XmlName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Form2.class);
			 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			form2 = (Form2) jaxbUnmarshaller.unmarshal(inputStream);
			
			syntaxMapForm2 = new Hashtable();
			
			List<Language> languages =  form2.getLanguages().getLanguage();
			for ( Language language : languages) 
			{
				if(getLanguage().equalsIgnoreCase(language.getName()))
				{
					for(LanguageEntry entry: language.getEntry())
					{
						syntaxMapForm2.put(entry.getName(), entry);
					}
				}
			}
		} 
		catch (JAXBException exception) 
		{
			
		}
	}
	
	/*
	 * This method loads form3 xml file and stores into object
	 * */
	public void generateForm3(String form3XmlName)
	{
		try 
		{
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("resource/"+form3XmlName);
			JAXBContext jaxbContext = JAXBContext.newInstance(Form3.class);
			 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			form3 = (Form3) jaxbUnmarshaller.unmarshal(inputStream);
			
			syntaxMapForm3 = new Hashtable();
			
			List<Language> languages =  form3.getLanguages().getLanguage();
			for ( Language language : languages) 
			{
				if(getLanguage().equalsIgnoreCase(language.getName()))
				{
					for(LanguageEntry entry: language.getEntry())
					{
						syntaxMapForm3.put(entry.getName(), entry);
					}
				}
			}
		} 
		catch (JAXBException exception) 
		{
			
		}
	}
	
	/*
	 * This method updates from1 language
	 * */
	public void updateLanguageMapForm1()
	{
		syntaxMapForm1 = new Hashtable();
		syntaxMapLabelToNameForm1 = new Hashtable();
		List<Language> languages =  form1.getLanguages().getLanguage();
		for ( Language language : languages) 
		{
			if(getLanguage().equalsIgnoreCase(language.getName()))
			{
				for(LanguageEntry entry: language.getEntry())
				{
					syntaxMapForm1.put(entry.getName(), entry);
					syntaxMapLabelToNameForm1.put(entry.getLabel(), entry.getName());
				}
			}
		}
	}
	
	/*
	 * This method updates from2 language
	 * */	
	public void updateLanguageMapForm2()
	{
		syntaxMapForm2 = new Hashtable();
		syntaxMapLabelToNameForm2 = new Hashtable();
		List<Language> languages =  form2.getLanguages().getLanguage();
		for ( Language language : languages) 
		{
			if(getLanguage().equalsIgnoreCase(language.getName()))
			{
				for(LanguageEntry entry: language.getEntry())
				{
					syntaxMapForm2.put(entry.getName(), entry);
					syntaxMapLabelToNameForm2.put(entry.getLabel(), entry.getName());
				}
			}
		}
	}
	
	/*
	 * This method updates from3 language
	 * */
	public void updateLanguageMapForm3()
	{
		syntaxMapForm3 = new Hashtable();
		syntaxMapLabelToNameForm3 = new Hashtable();
		List<Language> languages =  form3.getLanguages().getLanguage();
		for ( Language language : languages) 
		{
			if(getLanguage().equalsIgnoreCase(language.getName()))
			{
				for(LanguageEntry entry: language.getEntry())
				{
					syntaxMapForm3.put(entry.getName(), entry);
					syntaxMapLabelToNameForm3.put(entry.getLabel(), entry.getName());
				}
			}
		}
	}
	
}
