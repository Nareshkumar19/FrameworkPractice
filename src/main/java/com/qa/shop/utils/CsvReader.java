package com.qa.shop.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvReader {
	
	private static final String CSV_PATH = "./src/test/resources/testData/";
	private static List<String[]> rows;
	
	public static Object[][] getCsvData(String csvFileName){
		String csvFile = CSV_PATH + csvFileName + ".csv";
		
		CSVReader csvReader; //OpenCsv dependency has to be included
		
		try {
			csvReader = new CSVReader(new FileReader(csvFile));
			rows = csvReader.readAll();
			csvReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}
		
		Object data [][] = new Object[rows.size()][];
		
		for(int i=0; i <rows.size(); i++) {
			data[i] = rows.get(i);
		}
		return data;
	}
	
	
	
	

}
