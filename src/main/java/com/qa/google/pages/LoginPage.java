package com.qa.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	private WebDriver driver;
	
	private final By email = By.xpath("//input[@name=\"email\"]");
	private final By password = By.xpath("//input[@name=\"password\"]");
	private final By loginBtn = By.xpath("//input[@value=\"Login\"]");
	private final By forgotPwdLnk = By.linkText("Forgotten Password1");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getLoginPageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	public String getLoginPageUrl() {
		String url = driver.getCurrentUrl();
		System.out.println(url);
		return url;
	}
	
	public boolean isForgotPwdLinkAvailable() {
		return driver.findElement(forgotPwdLnk).isDisplayed();
	}
	
	public AccountsPage doLogin(String userName, String pwd) {
		driver.findElement(email).sendKeys(userName);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String title = driver.getTitle();
		System.out.println(title);
		return new AccountsPage(driver);
	}
}
