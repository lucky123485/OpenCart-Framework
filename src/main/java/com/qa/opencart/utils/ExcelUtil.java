package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static final String TEST_DATA_SHEET_PATH= "./src/test/resource/testdata/opencartTestData1.xlsx"; 
	private static Workbook book;
	private static Sheet sheet;
	
	
	public Object[] []  getTestData(String sheetName){
		System.out.println("reading test data Frome the sheet:"  +sheetName);
		
		Object data[] []=null;
		
		try {
			FileInputStream ip=new FileInputStream(TEST_DATA_SHEET_PATH);
			try {
				book=WorkbookFactory.create(ip);
				sheet=book.getSheet(sheetName);
				data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				for(int i=0;i<sheet.getLastRowNum(); i++) {
					for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++) {
						
					}
				}
				
			} catch (InvalidFormatException e) {
								e.printStackTrace();
			} catch (IOException e) {
								e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
						e.printStackTrace();
		}
		
		return data;
		
		
	}

}
