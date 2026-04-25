package com.qa.shop.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class ProductPage {
	
	WebDriver driver;
	private HashMap<String,String> prodDetailsMap = new HashMap<String,String>();
	
	private static final By productDetailsElements = By.xpath("//h1//following-sibling::ul[@class='list-unstyled'][1]//li");
	
	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Retrieve product details")
	public HashMap<String,String> getProductDetailsMap() {
		List<WebElement> prodDetails = driver.findElements(productDetailsElements);
		for(WebElement detail : prodDetails) {
			String [] detailSplit = detail.getText().split(":");
			prodDetailsMap.put(detailSplit[0].trim(), detailSplit[1].trim());
		}
		System.out.println(prodDetailsMap);
		return prodDetailsMap;
	}
	
	

}
