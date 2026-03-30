package com.qa.google.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.google.base.BaseTest;
import com.qa.google.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void setupAccountsPage() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountsPageTitleTest() {
		String title = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}

}
