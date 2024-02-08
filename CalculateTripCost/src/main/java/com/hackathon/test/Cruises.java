package com.hackathon.test;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.hackathon.Pages.CruisesPage;
import com.hackathon.utilities.ExcelData;
import com.hackathon.utilities.ExtentReport;
import com.hackathon.utilities.TakeScreenShot;

public class Cruises extends ExtentReport{
	
	static String parentWindowHashCode;

	@Test(dependsOnGroups="HolidayHome", groups="Cruises")
	public void cruisesHomePage() {
		
		//Import PageFactory to get Web elements
		PageFactory.initElements(driver, CruisesPage.class);
		
		// selecting 'Cruises' option
		ExtentReport.createTest("Cruises page","Move to Cruises page from Holiday Homes page");
		CruisesPage.cruisesHomePage.click();
		ExtentReport.addTestInfo("Clicked on 'Cruises' tab");
		
		//Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Cruises_Tab");
		
		ExtentReport.addTestInfo("Move to Holiday Homes page");
	}
	
	@Test(dependsOnMethods="cruisesHomePage", groups="cruises")
	public void searchCruise() throws InterruptedException {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Actions action = new Actions(driver);
		
		ExtentReport.createTest("Select Cruise details", "Select Cruise line,Cruise Ship and click search");
		//Click on "Cruise-Line" drop-down
		CruisesPage.cruiseLineDropdown.click();
		ExtentReport.addTestInfo("Clicked on 'Cruise line' drop-down");
		
		Thread.sleep(2000);
		//Click on 'Norwegian Cruise Line' from 'Cruise line' drop-down
		js.executeScript("arguments[0].click();", CruisesPage.cruiseLineName);
		action.sendKeys(Keys.HOME).build().perform();
		ExtentReport.addTestInfo("Clicked on 'Norwegian Cruise Line' from 'Cruise line' drop-down");
		
		CruisesPage.cruiseShipDropdown.click();
		ExtentReport.addTestInfo("Clicked on 'Cruise ship' drop-down");
		
		Thread.sleep(2000);
		//Click on 'Norwegian Bliss' from 'Cruise ship' drop-down
		js.executeScript("arguments[0].click();", CruisesPage.cruiseShipName);
		action.sendKeys(Keys.HOME).build().perform();
		ExtentReport.addTestInfo("Clicked on 'Norwegian Bliss' from 'Cruise ship' drop-down");
		
		//Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Select_Cruise");
		
		//click on search Button
		CruisesPage.searchButton.click();
		ExtentReport.addTestInfo("Click on search button");
		
	}
	
	@Test(dependsOnMethods="searchCruise", groups="cruises")
	public void getCruiseDetails() throws Exception {
		ExtentReport.createTest("Ship details", "Get Ship name,ship passengers and crew details");
		
		//saving parent window HashCode
		parentWindowHashCode = driver.getWindowHandle(); 
		//get all open window Hashcode in List
		Set<String> allWindows = driver.getWindowHandles(); 
		
		// get total no. of windows
		int windowCount = allWindows.size(); 		
		//System.out.println("Total windows count=" + windowCount);
		
		for (String child : allWindows) {
			if (!parentWindowHashCode.equalsIgnoreCase(child)) {
				// switch to child window
				driver.switchTo().window(child); 	
				Thread.sleep(1000);
				ExtentReport.addTestInfo("driver control is switched to 'Norwegian Bliss' page");
			}
		}
		
		//Taking screenshot
		TakeScreenShot.CaptureScreenShot("Cruises_Page");
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Actions action = new Actions(driver);
		
		js.executeScript("arguments[0].scrollIntoView();", CruisesPage.passengersDetails);
		Thread.sleep(2000);
		
		//Taking screenshot
		TakeScreenShot.CaptureScreenShot("Cruises_Details");
		Thread.sleep(1000);
		
		String shipName ="Shipname: "+CruisesPage.shipName.getText();
		String passengersAndCrew = CruisesPage.passengersDetails.getText();
		String launchedYear = CruisesPage.launchedYear.getText();
		
		//printing Details
		System.out.printf("\n\n");
		System.out.printf("---------------------------------Cruise Details---------------------------------\n\n");
		System.out.println(shipName);
		System.out.println(passengersAndCrew);
		System.out.println(launchedYear);
		
		//add this strings to excel
		ExcelData.write("CruiseOutput", 0, 0, "Cruise Details");
		ExcelData.write("CruiseOutput", 1, 0, shipName);
		ExcelData.write("CruiseOutput", 1, 1, passengersAndCrew);
		ExcelData.write("CruiseOutput", 1, 2, launchedYear);
		action.sendKeys(Keys.HOME).build().perform();
		
		ExtentReport.addTestInfo("Passengers, crew and launched year printed and added in excel data");
		
	}
	
	@Test(dependsOnMethods="getCruiseDetails", groups="cruises")
	public void getLanguageDetails() throws Exception {
		ExtentReport.createTest("Language details", "Get language details");
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Actions action = new Actions(driver);
		
		js.executeScript("arguments[0].scrollIntoView();", CruisesPage.languageTag);
		Thread.sleep(2000);
		
		//Taking screenshot
		TakeScreenShot.CaptureScreenShot("Language_Details");
		Thread.sleep(1000);
		
		ExcelData.write("CruiseOutput", 3, 0, "Language offered: ");
		System.out.println("Language offered: ");
		int sizeOflanguageList= CruisesPage.languageList.size();
		
		System.out.printf("\n\n");
		System.out.printf("---------------------------------Language Details---------------------------------\n\n");
		for(int i=0; i<sizeOflanguageList; i++) {
			String language = CruisesPage.languageList.get(i).getText();
			//Printing language
			System.out.println(language);
			//Add language details to excel
			ExcelData.write("CruiseOutput", 4, i, language);
		}
		
		ExtentReport.addTestInfo("Language details printed and added in excel data");
		
		//Close the child windpw
		driver.close();
		
		//Switch to parent window
		driver.switchTo().window(parentWindowHashCode);
		
		TakeScreenShot.CaptureScreenShot("Driver_SwitchTo_Parent_Window");
	}
	
	@AfterTest
	public void quit() throws InterruptedException {
		ExtentReport.createTest("Close browser", "Close the browser");
		Thread.sleep(1000);
		//close the browser
		driver.quit();
		ExtentReport.addTestInfo("Browser close successfully");
	}
	
}
