package com.hackathon.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CruisesPage {
	@FindBy(xpath="//*[@id=\"component_1\"]/nav/div/div/div[1]/a[8]")
	public static WebElement cruisesHomePage;
	
	@FindBy(xpath="//*[@id=\"component_1\"]/div/div[2]/div/div[1]/div/button")
	public static WebElement cruiseLineDropdown;
	
	@FindBy(xpath="//*[@id=\"menu-item-17391425\"]")
	public static WebElement cruiseLineName;
	
	@FindBy(xpath="//*[@id=\"component_1\"]/div/div[2]/div/div[2]/div/button")
	public static WebElement cruiseShipDropdown;
	
	@FindBy(xpath="//*[@id=\"menu-item-15691669\"]")
	public static WebElement cruiseShipName;
	
	@FindBy(xpath="//*[@id=\"component_1\"]/div/div[2]/div/div[3]/span/button")
	public static WebElement searchButton;
	
	@FindBy (xpath ="//*[@id=\"HEADING\"]")
	public static WebElement shipName;
	
	@FindBy(xpath="//*[@id=\"ship_overview\"]/div/div/div/div[1]/div[1]/div[2]/div[1]")
	public static WebElement passengersDetails;
	
	@FindBy(xpath="//*[@id=\"ship_overview\"]/div/div/div/div[1]/div[1]/div[2]/div[4]")
	public static WebElement launchedYear;
	
	@FindBy (xpath="//*[@id=\"ship_reviews\"]/div/div[2]/div/div[1]/div[1]/div[3]/ul/li/label/span[1]")
	public static WebElement languageTag;
	
	@FindBy (xpath="//*[@id=\"ship_reviews\"]/div/div[2]/div/div[1]/div[1]/div[3]/ul/li/label/span[1]")
	public static List<WebElement> languageList;
	
}
