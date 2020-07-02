package main.utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Database {
	private final StringProperty id;
	private final StringProperty date;
	private final StringProperty text;

	
	
	public Database(String id,String date,String text)
	{
		this.id=new SimpleStringProperty(id);
		this.date=new SimpleStringProperty(date);
		this.text=new SimpleStringProperty(text);
		
	
		
		
	}
	public String getid() {return id.get();}
	public String getdate() {return date.get();}
	public String gettext() {return text.get();}
	
	
	public void setid(String value)
	{
		id.set(value);
	}
	public void setdate(String value)
	{
		date.set(value);
	}
	public void settext(String value)
	{
		text.set(value);
	}

	public StringProperty idProperty()
	{
		return id;
	}
	public StringProperty dateProperty()
	{
		return date;
	}
	public StringProperty textProperty()
	{
		return text;
	}


}

