package com.qa.shop.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.shop.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.shop.constants.ApplicationConstants.*;

import java.util.List;

@Feature("2: This feature is to develop My Accounts page")
@Epic("2: My accounts page development")
@Story("102: My accounts page should display User account details")
public class MyAccountTest extends BaseTest{
	

	@Description("Pre-Requisite : Login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("QA Team")
	@BeforeClass
	public void setUpAccountPage() {
		myAccountPage = loginPage.loginOpenCart(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	@Description("Validate My Account Title")
	@Severity(SeverityLevel.NORMAL)
	@Owner("QA Team")
	@Test
	public void validateAccountPageTitle() {
		Assert.assertEquals(myAccountPage.getAccntPageTitle(), MY_ACCOUNT_TITLE);
	}
	
	@Description("Validate My Account Headers List")
	@Severity(SeverityLevel.NORMAL)
	@Owner("QA Team")
	@Test
	public void validateHeaders() {
		List<String> actualHeaders = myAccountPage.getHeadersList();
		Assert.assertEquals(actualHeaders, ACCOUNT_HEADERS_LIST);
	}
	
	
}
