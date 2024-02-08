package com.hackathon.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelData {
	static Cell cell;

	public static void write(String sheetName, int row,int col,String value) throws Exception
	{
		File src = new File("./testData/TestOutput.xlsx");
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);	
		Sheet sheet = wb.getSheet(sheetName);  // get sheet

		if (sheet == null) {
			sheet = wb.createSheet(sheetName);
		}

		Row row1 = sheet.getRow(row);  // get row

		if (row1 == null) {
		    row1 = sheet.createRow(row);  // create new row if it doesn't exist
		}

		try {
		    cell = row1.createCell(col);  // create cell
		} catch (Exception e) {
		    cell = row1.getCell(col);  // get cell
		}

		cell.setCellValue(value);

		FileOutputStream fos = new FileOutputStream(src);
		wb.write(fos);
		wb.close();
	}
}
