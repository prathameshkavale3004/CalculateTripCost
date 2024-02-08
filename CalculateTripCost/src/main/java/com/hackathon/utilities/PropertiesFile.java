package com.hackathon.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFile {
 public static Properties useProperties() throws Exception {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\properties\\projectConfig.properties");
		Properties property = new Properties();
		property.load(file);
		return property;

	}
 
 	public static String getBrowser() throws Exception{
 		Properties property = PropertiesFile.useProperties();
		String browser = property.getProperty("browserName");
 		return browser;
 		
 	}
 	public static String getUrl() throws Exception
	{
		Properties property = PropertiesFile.useProperties();
		String url = property.getProperty("URL");
		return url;
	}
	
	public static String getDestination()throws Exception
	{
		Properties property = PropertiesFile.useProperties();
		String dest = property.getProperty("destination");
		return dest;
	}
	
	public static String getRequiredPeopleCount()throws Exception
	{
		Properties property = PropertiesFile.useProperties();
		String noOfPeople = property.getProperty("noOfPeople");
		return noOfPeople;
	}
}




