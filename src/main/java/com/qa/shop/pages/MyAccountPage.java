package com.qa.shop.pages;

import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class MyAccountPage {
	
	WebDriver driver;
	
	private final By headers = By.xpath("//h2");
	private final By searchField = By.xpath("//input[@name='search']");
	
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Retrieve Account Page Title")
	public String getAccntPageTitle() {
		String actualTitle = driver.getTitle();
		System.out.println("Page Title : " + actualTitle);
		return actualTitle;
	}
	
	@Step("Extracting headers list")
	public List<String> getHeadersList() {
		List<WebElement> headerElements = driver.findElements(headers);
		List<String> headersList = new LinkedList<String>();
		for(WebElement header : headerElements) {
			headersList.add(header.getText());
		}
		
		return headersList;
	}
	
	@Step("Search with key : {0}")
	public SearchResultsPage searchProduct(String searchKey) {
		WebElement searchElement = driver.findElement(searchField);
		searchElement.clear();
		searchElement.sendKeys(searchKey);
		searchElement.sendKeys(Keys.ENTER);
		//Temporary code. To be replaced with explicit wait
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new SearchResultsPage(driver);
		
	}

}
