package com.qa.shop.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.shop.base.BaseTest;
import com.qa.shop.pages.MyAccountPage;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.shop.constants.ApplicationConstants.*;

@Feature("1: Login Feature")
@Epic("1: Design Login Page fuctionality")
@Story("101: Implement Login page functionality")
public class LoginTests extends BaseTest{
	
	@Description("This test is to validate Login Page Title")
	@Severity(SeverityLevel.MINOR)
	@Owner("QA Team")
	@Test(description = "Validate the Login Page Title")
	public void validateLoginPgTitle() {
		String actualTitle = loginPage.getPageTitle();
		Assert.assertEquals(actualTitle, LOGIN_TITLE);
		//Assert.assertEquals(actual,expected); - Follow this format
		
	}
	
	@Description("This is to validate the Login Page URL")
	@Severity(SeverityLevel.NORMAL)
	@Owner("QA Team")
	@Test(description = "validate the Login Page URL")
	public void validateLoginPgUrl() {
		String actualUrl = loginPage.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(LOGIN_PARTIAL_URL));
	}
	
	@Description("This is to validate Forgot passwork link")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("QA Team")
	@Test(description = "Validate if Forgot Passwork Link present")
	public void validateForgotPwdLink() {
		Assert.assertTrue(loginPage.isForgotPwdLink());
	}
	
	@Description("This is to validate Login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("QA Team")
	@Test(priority = Short.MAX_VALUE, description = "Validate Login with valid credentials")
	public void validateLogin() {
		myAccountPage = loginPage.loginOpenCart(prop.getProperty("userName"), prop.getProperty("password"));
//		Assert.assertEquals(actualTitle, MY_ACCOUNT_TITLE);
		//Allure.step("Login test with valid cred");
		Assert.assertEquals(myAccountPage.getAccntPageTitle(), MY_ACCOUNT_TITLE);
	}
	
	@Test(enabled = false)
	public void clickRegistration() {
		System.out.println("This is WIP");
	}

}
