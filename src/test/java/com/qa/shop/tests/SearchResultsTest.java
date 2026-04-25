package com.qa.shop.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.shop.base.BaseTest;
import com.qa.shop.utils.CsvReader;
import com.qa.shop.utils.ExcelReader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.qa.shop.constants.ApplicationConstants.*;

@Feature("3: This feature is to develop Search page")
@Epic("3: Search page development")
@Story("103: Search functionality")
public class SearchResultsTest extends BaseTest{
	
	@Description("Pre-Requisite : Login with valid credentials")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("QA Team")
	@BeforeClass
	public void setUpSearchPage() {
		myAccountPage = loginPage.loginOpenCart(prop.getProperty("userName"), prop.getProperty("password"));
	}
	
	
	@DataProvider
	public Object[][] searchData() {
		return new Object [][] {
			{"macbook", "3"},
			{"iphone", "2"},
			{"mac", "4"}
		};
	}
	
	@DataProvider
	public Object[][] searchExcelData(){
		return ExcelReader.getExcelTestData(EXCEL_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] searchCsvData(){
		return CsvReader.getCsvData(CSV_FILE_NAME);
	}
	
	@Description("Validate Search functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Owner("QA Team")
	@Test(dataProvider = "searchData")
	public void validateSearchResults(String searchKey, String expResultsCount) {
		searchResultsPage = myAccountPage.searchProduct(searchKey);	
		int resultsCount = searchResultsPage.getSearchResultsCount();
		ChainTestListener.log("Captured Results count : " + resultsCount);
		Assert.assertEquals(String.valueOf(resultsCount), expResultsCount);	
		ChainTestListener.log("Matched the expecetd Results count");
	}
	
	//org.testng.internal.reflect.MethodMatcherException - Data provider mismatch
	
}
