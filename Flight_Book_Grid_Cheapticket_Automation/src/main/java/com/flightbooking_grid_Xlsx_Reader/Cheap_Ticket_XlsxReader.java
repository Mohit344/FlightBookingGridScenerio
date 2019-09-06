/**
 * xlsx file is created for using as a data provider
 * 
 */
package com.flightbooking_grid_Xlsx_Reader;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

/**
 * 
 * @author Mohit.Jaiswal
 * Using .xlsx file for using as a data_provider
 */
public class Cheap_Ticket_XlsxReader {

	/**
	 * @param file
	 * @return
	 * @throws IOException
	 */
		public static String[][] returnLocator(String file) throws IOException {
			FileInputStream fileInput = new FileInputStream(file); // read the xlsx file  
			XSSFWorkbook book = new XSSFWorkbook(fileInput);
			Sheet sheet = book.getSheetAt(0);
			int row1 = sheet.getLastRowNum();
			row1 += 1;
			int col = sheet.getRow(0).getLastCellNum();
			String array[][] = new String[row1][col];
			int count = 0;
			for (Row row : sheet) {
			int count1 = 0;
			for (Cell cell : row) {
			String Data = cell.toString();
			array[count][count1] = Data;
			count1++;
			}
			count++;
			}
			return array;
			}
	
		/**
		 * @return
		 * @throws IOException
		 */
			@DataProvider(name="XcelDataProvider")   // annotation DataProvider is use
			public  String[][] getdata() throws IOException {

			   return Cheap_Ticket_XlsxReader.returnLocator("./src/test/resources/TestData/testData.xlsx");
			}
			
	}


