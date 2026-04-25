package com.qa.shop.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsFactory {
	
	private Properties prop;
	
	public OptionsFactory(Properties prop) {
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("--Headless Mode----");
			co.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("--Incognito Mode----");
			co.addArguments("--incognito");
		}	
		return co;
	}
	
	public FirefoxOptions getFirefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("--Headless Mode----");
			fo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("--Incognito Mode----");
			fo.addArguments("--incognito");
		}	
		return fo;
	}
	
	public EdgeOptions getEdgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("--Headless Mode----");
			eo.addArguments("--headless");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("--InPrivate Mode----");
			eo.addArguments("--inprivate");
		}	
		return eo;
	}

}
