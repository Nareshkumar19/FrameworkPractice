package com.qa.shop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class SearchResultsPage {
	
	WebDriver driver;
	
	
	private static final By searchResults = By.xpath("//div[@class='product-thumb']");
	private static final By firstResult = By.xpath("(//div[@class='product-thumb'])[1]");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Retrieve the Search results count")
	public int getSearchResultsCount() {
		int resultsCount = driver.findElements(searchResults).size();
		System.out.println("Results count : " + resultsCount);
		return resultsCount;
	}
	
	@Step("Click the first product in the search result")
	public ProductPage clickFirstProduct( ) {
		driver.findElement(firstResult).click();
		return new ProductPage(driver);
	}

	
	
}
