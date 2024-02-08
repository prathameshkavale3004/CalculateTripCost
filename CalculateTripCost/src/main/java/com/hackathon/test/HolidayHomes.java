package com.hackathon.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.hackathon.Pages.HolidayHomesPage;
import com.hackathon.utilities.DateFunction;
import com.hackathon.utilities.DriverSetup;
import com.hackathon.utilities.ExcelData;
import com.hackathon.utilities.ExtentReport;
import com.hackathon.utilities.PropertiesFile;
import com.hackathon.utilities.TakeScreenShot;

import java.util.List;

public class HolidayHomes extends ExtentReport {

	PropertiesFile propertiesFile;

	@BeforeTest(groups={"HolidayHome","Cruises"})
	public void getDriver() throws Exception {
		// Invoke browser
		ExtentReport.createTest("Browser invoking", "Invoking the browser using Driver setup class");
		DriverSetup.getWebDriver(PropertiesFile.getBrowser());
		ExtentReport.addTestInfo("Browser invoked successfully");
	}

	@Test(priority=0, groups={"HolidayHome","Cruises"})
	public void getTripadvisorHomePage() throws Exception {
		// Import PageFactory to get Web elements
		PageFactory.initElements(driver, HolidayHomesPage.class);

		// Import PageFactory to get Web elements
		ExtentReport.createTest("TripAdvisor homes page", "Open TripAdvisor homepage");
		driver.get(propertiesFile.getUrl());	
		ExtentReport.addTestInfo("TripAdvisor website opened");

		// taking screenshot of HomePage
		TakeScreenShot.CaptureScreenShot("TripAdvisor_HomePage");

		String url = driver.getCurrentUrl();
		System.out.println(url);
	}

	@Test(dependsOnMethods={"getTripadvisorHomePage"}, groups="HolidayHome")
	public void getSearch() throws Exception {

		Thread.sleep(3000);

		ExtentReport.createTest("Search for destination",
				"Search for Nairobi in search bar and go to Holiday Homes option");

		// adding destination in search bar
		HolidayHomesPage.searchBar.sendKeys(propertiesFile.getDestination());

		Thread.sleep(5000);

		// selecting nairobi loaction
		HolidayHomesPage.selectFirstResult.click();
		ExtentReport.addTestInfo("Destination searched as Nairobi");

		Thread.sleep(5000);

		// Move to Holiday Homes page
		HolidayHomesPage.holidayHomeOption.click();
		ExtentReport.addTestInfo("Moved to Holiday Homes page");

		// Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Holiday_Homes_Page");
	}

	@Test(dependsOnMethods={"getSearch"}, groups="HolidayHome")
	public void setDates() throws Exception {
		Thread.sleep(2000);
		ExtentReport.createTest("Add Dates", "Add check-in and check-out dates");

		// click on calendar option
		HolidayHomesPage.calenderButton.click();

		String checkin = DateFunction.selectCheckinDate();
		String checkout = DateFunction.selectCheckoutDate();

		Thread.sleep(2000);

		// add check-in date
		driver.findElement(By.xpath("//div[@aria-label='" + checkin + "']")).click();
		ExtentReport.addTestInfo("check-in date clicked");

		Thread.sleep(2000);

		// add check-out date
		driver.findElement(By.xpath("//div[@aria-label='" + checkout + "']")).click();
		ExtentReport.addTestInfo("check-out date clicked");

		Thread.sleep(2000);

		// Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Dates_Added");
	}

	@Test(dependsOnMethods={"setDates"}, groups="HolidayHome")
	public void setNoOfPeople() throws Exception {
		Thread.sleep(2000);
		ExtentReport.createTest("Guest and Rooms", "Add the no. of guests needed");
		
		//Click on add people button
		HolidayHomesPage.addPeopleButton.click();

		Thread.sleep(2000);
		int people = Integer.parseInt(HolidayHomesPage.currentNoOfPeople.getText());
		int requiredPeople = Integer.parseInt(PropertiesFile.getRequiredPeopleCount());

		//Increasing people count to given value(4)
		while (people < requiredPeople) {
			driver.findElement(By.xpath("//*[@id=\"BODY_BLOCK_JQUERY_REFLOW\"]/div[12]/div/div/div/div[1]/div[1]/div/div/div[2]/button[2]")).click();
			people++;
		}
		ExtentReport.addTestInfo("Reqired no of people guests added");
		
		Thread.sleep(2000);
		TakeScreenShot.CaptureScreenShot("Guest_Dropdown");		// Taking Screenshot
		
		//Click on apply button 
		HolidayHomesPage.applyButton.click();
		ExtentReport.addTestInfo("Apply button clicked");
		
		Thread.sleep(2000);
		
		// Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Add_Guests");
	}

	@Test(dependsOnMethods={"setNoOfPeople"}, groups="HolidayHome")
	public void setSortByTravellerRating() throws Exception {
		ExtentReport.createTest("Traveller rating sort","Sort the result with highest traveller rating");
		
		//Click on SortBy option
		HolidayHomesPage.sortDropdown.click();
		ExtentReport.addTestInfo("Sorting dropdown clicked");
		
		Thread.sleep(2000);
		TakeScreenShot.CaptureScreenShot("SortBy_dropdown");	// Taking Screenshot
		
		//Click on Traveler Rating option
		HolidayHomesPage.optTravelerRating.click();
		ExtentReport.addTestInfo("Traveller rating value is clicked and list is sorted");
		Thread.sleep(2000);
		
		// Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Travellers_Rating");
	}

	@Test(dependsOnMethods={"setSortByTravellerRating"}, groups="HolidayHome")
	public void selectLiftOption() throws Exception {
		ExtentReport.createTest("Elevator/Lift access","To select the check-box 'Elevator/Lift Access under Amenites");
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		Actions action = new Actions(driver);

		// Click on  showmore
		js.executeScript("arguments[0].scrollIntoView();", HolidayHomesPage.showmore);
		js.executeScript("arguments[0].click();", HolidayHomesPage.showmore); 
		action.sendKeys(Keys.HOME).build().perform(); // Move to top of the page
		ExtentReport.addTestInfo( "'Show more' under Amenities clicked");

		// click on Elevator/lift access check-box
		js.executeScript("arguments[0].scrollIntoView();", HolidayHomesPage.lift);
		js.executeScript("arguments[0].click();", HolidayHomesPage.lift); 
		TakeScreenShot.CaptureScreenShot("Amenities_options");
		Thread.sleep(2000);
		action.sendKeys(Keys.HOME).build().perform(); // Move to top of the page
		ExtentReport.addTestInfo("Elevator/Lift Access check-box under Amenities is clicked");

		// click apply button
		js.executeScript("arguments[0].scrollIntoView();", HolidayHomesPage.liftApplyButton);
		js.executeScript("arguments[0].click();", HolidayHomesPage.liftApplyButton); 																		
		Thread.sleep(2000);
		action.sendKeys(Keys.HOME).build().perform();
		ExtentReport.addTestInfo("Apply button clicked");
		
		// Taking Screenshot
		TakeScreenShot.CaptureScreenShot("Lift_Access");
	}

	@Test(dependsOnMethods={"selectLiftOption"}, groups="HolidayHome")
	public void fetchData() throws Exception {
		ExtentReport.createTest("Result page","Add in excel and Print the Hotel name,price/night and total cost");
		
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("document.body.style.zoom = '50%'");
		Thread.sleep(5000);
		TakeScreenShot.CaptureScreenShot("Result_Page");	// Taking Screenshot
		js.executeScript("document.body.style.zoom = '100%'");

		System.out.printf("\n\n");
		System.out.printf("---------------------------------Holiday Homes List---------------------------------\n\n");
		
		int j = 1;
		for (int i = 1; i < 4; i++) {
			// adding respected data to its respected String
			String index = +i + ". Hotel name: ";
			ExtentReport.addTestInfo("Holiday Home No: "+(i));
			
			String houseName = HolidayHomesPage.homeNameList.get(i - 1).getText();
			ExtentReport.addTestInfo("Hotel name: " +houseName);
			
			String price = HolidayHomesPage.priceList.get(j++).getText();
			j++;
			ExtentReport.addTestInfo("Price Per Night: " + price);
			
			String totalCost = HolidayHomesPage.totalpriceList.get(i).getText();
			ExtentReport.addTestInfo("Total Price : " + totalCost);
			
			System.out.println(index + "\n" + houseName + "\nPrice Per Night: " + price + "\nTotal Price: " + totalCost);
			System.out.println();

			//add Strings to excel file
			int r = i - 1;
			ExcelData.write("HolidayHomeOutput", r, 0, index);
			ExcelData.write("HolidayHomeOutput", r, 1, houseName);
			ExcelData.write("HolidayHomeOutput", r, 2, price);
			ExcelData.write("HolidayHomeOutput", r, 3, totalCost);
		}
		ExtentReport.addTestInfo("Result added in excel and printed in concole.");
	}
	
	/*@Test(dependsOnMethods ={"fetchData"}, groups="regression")
	public static void returnHomePage() {
		//return to homepage by clicking logo of tripadvisor
		ExtentReport.createTest("Return homepage","Return to homepage by clicking on 'TripAdvisor.in' logo");
		HolidayHomesPage.returnHomepage.click();		
		//System.out.println("Successfully returned to the Homepage");
		ExtentReport.addTestInfo("Successfully returned to the homepage");
	}*/
}