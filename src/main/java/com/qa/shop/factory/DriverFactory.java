package com.qa.shop.factory;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.shop.customExceptions.BrowserException;
import com.qa.shop.customExceptions.EnvironmentException;

import io.qameta.allure.Step;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsFactory options;
	public static ThreadLocal<WebDriver> tLocalDriver = new ThreadLocal<WebDriver>();
	public static Logger log = LogManager.getLogger(DriverFactory.class);
	//4 states of Log : info, warning, error, fatal
	
	@Step("Properties : {0}")
	public WebDriver initDriver(Properties prop) {
		
		options = new OptionsFactory(prop);
		String browserName = prop.getProperty("browser");
		switch (browserName.toLowerCase().trim()) {
			
		case "chrome":
			tLocalDriver.set(new ChromeDriver(options.getChromeOptions()));
//			driver = new ChromeDriver(options.getChromeOptions());
		break;
		
		case "edge":
			tLocalDriver.set(new EdgeDriver(options.getEdgeOptions()));
//			driver = new EdgeDriver(options.getEdgeOptions());
		break;
		
		case "firefox":
			tLocalDriver.set(new FirefoxDriver(options.getFirefoxOptions()));
//			driver = new FirefoxDriver(options.getFirefoxOptions());
		break;
		//Cannot execute Safari in headless or incognito
		
		default:
			log.error("-------- Incorrect Browser : " + browserName + " --------");
//			System.out.println("-------- Incorrect Browser : " + browserName + " --------");
			throw new BrowserException("Incorrect Browser : " + browserName);	
		}
//		driver.get(prop.getProperty("applicationUrl"));
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		return driver;			
		
		getDriver().get(prop.getProperty("applicationUrl"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public Properties initProperities() {
		String environment = System.getProperty("env", "qa");
		prop = new Properties();
		FileInputStream file = null;
		try {
			
			switch (environment.toLowerCase().trim()){	
				case "qa":
					file = new FileInputStream("./src/test/resources/properties/qa.config.properties");
				break;
				
				case "uat":
					file = new FileInputStream("./src/test/resources/properties/uat.config.properties");
				break;
				
				default:
					log.error("=======Invalid Environment : " + environment + "=======");
					throw new EnvironmentException("=======Invalid Environment : " + environment + "=======");
			}
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("Properties : " + prop);
		return prop;
	}
	
	public static WebDriver getDriver() {
		return tLocalDriver.get();
	}
	
	public static File getScreenshotFile() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); //TakesScreenshot Interface implementd by RemoteWebDriver
	}
	
	public static byte[] getScreenshotBytes() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
	}



}
