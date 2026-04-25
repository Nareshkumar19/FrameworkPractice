package com.qa.shop.tests;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.shop.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("4: This feature is to develop Product details page")
@Epic("4: Product details page development")
@Story("104: Product details should be displayed")
public class ProductPageTest extends BaseTest{
	
	@Description("Pre-Requisite : Login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("QA Team")
	@BeforeClass
	public void setUpProductPage() {
		myAccountPage = loginPage.loginOpenCart(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Description("Validate product details")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("QA Team")
	@Test
	public void valiateProductDetails() {
		searchResultsPage = myAccountPage.searchProduct("macBook");
		productPage = searchResultsPage.clickFirstProduct();
		HashMap<String,String> productDetailsMap = productPage.getProductDetailsMap();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(productDetailsMap.get("Product Code"), "Product 16");
		softAssert.assertEquals(productDetailsMap.get("Reward Points"), "600");
		softAssert.assertEquals(productDetailsMap.get("Availability"), "Out Of Stock");
		
		softAssert.assertAll();
	}

}
