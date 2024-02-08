package com.hackathon.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HolidayHomesPage {

	@FindBy(xpath="//*[@id=\"lithium-root\"]/main/div[3]/div/div/div/form/input[1]")
	public static WebElement searchBar;

	@FindBy(xpath="//*[@id='typeahead_results']/a")
	public static WebElement selectFirstResult;
	
	@FindBy (xpath="//*[@id=\"lithium-root\"]/main/div[1]/div/div[1]/nav/div/div/div[1]/a[6]")
	public static WebElement holidayHomeOption;
	
	@FindBy (xpath="//button[@aria-label='Enter the date range.']")
	public static WebElement calenderButton;
	
	@FindBy (xpath="//button[@aria-label='Enter the number of rooms and then enter the number of guests. The selected number of rooms is 0 and the selected number of guests is 2.']")
	public static WebElement addPeopleButton;
	
	@FindBy (xpath="//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[12]/div/div/div/div[1]/div[1]/div/div/div[2]/div/span")
	public static WebElement currentNoOfPeople;
	
	@FindBy (xpath="//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[12]/div/div/div/div[2]/div/button")
	public static WebElement applyButton;
	
	@FindBy (xpath="//button[@aria-label='Tripadvisor Sort: Tripadvisor Sort']")
	public static WebElement sortDropdown;
	
	@FindBy (id="menu-item-TRAVELERRATINGHIGH")
	public static WebElement optTravelerRating;
	
	//@FindBy (xpath = "//*[text()='Amenities']//following-sibling::div[5]")
	@FindBy (xpath = "//*[text()='Amenities']/../..//div/div/div[2]/button")
	public static WebElement showmore;
	
	//@FindBy (xpath = "(//*[contains(text(),'Elevator/Lift')])[1]")
	@FindBy (xpath = "//input[@name=\"amenities.27\"]")
	public static WebElement lift;
	
	@FindBy (xpath = "//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[14]/div/div[3]/div/div/button")
	public static WebElement liftApplyButton;
	
	@FindBy(xpath = "//*[@data-test-target='rentals-list'] //*[@target='_blank']")
	public static List<WebElement> homeNameList;
	
	@FindBy(xpath = "//*[contains(text(), '₹')]")
	public static List<WebElement> priceList;
	
	@FindBy(xpath = "//*[contains(text(), 'nights')]")
	public static List<WebElement> totalpriceList;
	
	/*@FindBy (xpath = "//img[@alt='Tripadvisor']")	//img[@alt='Tripadvisor']
	public static WebElement returnHomepage;*/
	
}
