package com.qa.shop.pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private final By emailId = By.xpath("//input[@name='email']");
	private final By pwd = By.xpath("//input[@name='password']");
	private final By forgotPwdLink = By.xpath("//input//following-sibling::a[text()='Forgotten Password']");
	private final By loginBtn = By.xpath("//input[@value='Login']");
	public static Logger log = LogManager.getLogger(LoginPage.class);
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Get the Page Title")
	public String getPageTitle() {
		String actualTitle = driver.getTitle();
		log.info("Page Title : " + actualTitle);
//		System.out.println("Page Title : " + actualTitle);
		return actualTitle;
	}
	
	@Step("Get the current URL")
	public String getCurrentUrl() {
		String actualUrl = driver.getCurrentUrl();
		log.info("URL : " + actualUrl);
//		System.out.println("URL : " + actualUrl);
		return actualUrl;
	}
	
	@Step("Get Forgot Password Link availability")
	public boolean isForgotPwdLink() {
		boolean status = driver.findElement(forgotPwdLink).isDisplayed();
		return status;
	}
	
	@Step("Login with credentials - UserName : {0} and Password : {1}")
	public MyAccountPage loginOpenCart(String userName, String password) {
//		System.out.println(userName);
//		System.out.println(password);
		driver.findElement(emailId).sendKeys(userName);
		driver.findElement(pwd).sendKeys(password);
		driver.findElement(loginBtn).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		String currentTitle = driver.getTitle();
//		System.out.println(currentTitle);
//		return driver.getTitle();
		return new MyAccountPage(driver); //Page Chaining
		
	}
	 

}
