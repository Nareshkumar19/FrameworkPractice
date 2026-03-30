package com.qa.google.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.qa.google.exceptions.BrowserException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	/**
	 * This is used to initialize the browser
	 * @param browserName
	 */
	public WebDriver initDriver(Properties properties) {
		String browserName = properties.getProperty("browser");
		String url = properties.getProperty("url");
		
		switch(browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver());
			break;
		
		case "edge":
//			driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
			break;
		
		default:
			System.out.println("Please pass valid browser : " + browserName);
			throw new BrowserException("Invalid Browser : " + browserName);		
		}
		
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().deleteAllCookies();
//		return driver;
		
		getDriver().get(url);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
//	public Properties initProp() {
//		prop = new Properties();
//		try {
//			FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
//			prop.load(ip);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		return prop;
//	}
	
	public Properties initProp() {
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();
		try {
		if(envName == null) {
			System.out.println("Env is null, hence executing in QA by default");
			ip = new FileInputStream("./src/test/resources/config/qa.properties");
		}else {
			switch(envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.properties");
			break;
			
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.properties");
			break;
			}
		}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return prop;
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		return srcFile;
	}
	
	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
	}
	
	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
