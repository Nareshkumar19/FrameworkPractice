package com.qa.google.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.google.base.BaseTest;
import com.qa.google.constants.AppConstants;

public class LoginPageTest extends BaseTest{
	
	@Test(description = "Validate the loginpage title")
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
		ChainTestListener.log("Actual title : " + title + "\nExpected title : " + AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test(description = "Forgot pwd link")
	public void forgotPwdLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkAvailable());
	}
	
	@Test(enabled = false, priority = Short.MAX_VALUE)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.HOME_PAGE_TITLE);
	}
	
	//mvn clean install -Denv="qa"
	//mvn install
	//mvn test

}
