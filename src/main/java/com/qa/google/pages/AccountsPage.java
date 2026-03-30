package com.qa.google.pages;

import org.openqa.selenium.WebDriver;

public class AccountsPage {
	
	WebDriver driver;
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}

	public String getAccountsPageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		return title;	
	}
	

}
