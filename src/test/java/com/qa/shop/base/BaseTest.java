package com.qa.shop.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.shop.factory.DriverFactory;
import com.qa.shop.pages.LoginPage;
import com.qa.shop.pages.MyAccountPage;
import com.qa.shop.pages.ProductPage;
import com.qa.shop.pages.SearchResultsPage;

//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected MyAccountPage myAccountPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductPage productPage;
	
	@BeforeTest
	@Parameters({"browser"})
	public void startUp(@Optional("chrome") String browserName) {	
		df = new DriverFactory();
		prop = df.initProperities();
		
		if(browserName !=null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown() {
		if(driver !=null) {
			driver.quit();
		}
	}
	
	@AfterMethod //Runs after each @Test method
	public void captureScreenshot(ITestResult result) { //ITestResult is from TestNG
		if(!result.isSuccess()) { //During Failure cases
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
	}

}
