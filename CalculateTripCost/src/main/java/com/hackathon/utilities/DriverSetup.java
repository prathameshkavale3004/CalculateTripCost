package com.hackathon.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties prop;

	public static WebDriver getWebDriver(String browserName) throws InterruptedException {
		if (browserName.equalsIgnoreCase("chrome")) // To use Chrome driver
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//resources//drivers//chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) // To use Microsoft Edge Driver
		{
			driver = new EdgeDriver();

		} else {
			driver = null;
			System.out.println("Please provide the browser Name as Chrome/MSEdge");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.manage().deleteAllCookies();
		
		return driver;
		
		
	}
}
