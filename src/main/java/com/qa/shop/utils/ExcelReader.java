package com.qa.shop.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	private static final String excelPath = "./src/test/resources/testData/TestData.xlsx";

	private static Workbook workbook;
	private static Sheet sheet;
	
	//Excel methods are not suitable for complete parallel execution. So, method can be used static.
	public static Object[][] getExcelTestData(String sheetName) {
		Object [][] data = null;
		FileInputStream fp;
		try {
			fp = new FileInputStream(excelPath);
			workbook = WorkbookFactory.create(fp);
			sheet = workbook.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			//sheet.getLastRowNum() returns Total rows - 1
			//sheet.getRow(0).getLastCellNum() returns Total columns
			
			for(int i=0; i< sheet.getLastRowNum(); i++) {
				for(int j=0; j< sheet.getRow(0).getLastCellNum(); j++) {
					data [i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
