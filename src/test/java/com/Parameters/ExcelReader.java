package com.Parameters;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private Sheet sheet;
	private Workbook workbook;

	public ExcelReader(String filePath, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCellData(int rowNum, String columnName) {
		Row row = sheet.getRow(0);
		int colNum = -1;

		for (int i = 0; i < row.getLastCellNum(); i++) {
			if (row.getCell(i).getStringCellValue().trim().equals(columnName.trim())) {
				colNum = i;
			}
		}

		Row dataRow = sheet.getRow(rowNum);
		return dataRow.getCell(colNum).toString();
	}

//	// Returns list of maps: each map = columnName -> value
//	public List<Map<String, String>> getDataAsMap() {
//		List<Map<String, String>> data = new ArrayList<>();
//		if (sheet == null)
//			return data;
//		Row headerRow = sheet.getRow(0);
//		int lastRow = sheet.getLastRowNum();
//		int lastCol = headerRow.getLastCellNum();
//		for (int r = 1; r <= lastRow; r++) {
//			Row row = sheet.getRow(r);
//			if (row == null)
//				continue;
//			Map<String, String> rowData = new HashMap<>();
//			boolean allBlank = true;
//			for (int c = 0; c < lastCol; c++) {
//				Cell headerCell = headerRow.getCell(c);
//				if (headerCell == null)
//					continue;
//				String header = headerCell.getStringCellValue().trim();
//				Cell cell = row.getCell(c);
//				String value = getCellValueAsString(cell);
//				if (value != null && !value.isEmpty())
//					allBlank = false;
//				rowData.put(header, value);
//			}
//			if (!allBlank)
//				data.add(rowData);
//		}
//		return data;
//	}

//	private String getCellValueAsString(Cell cell) {
//		if (cell == null)
//			return "";
//		return switch (cell.getCellType()) {
//		case STRING -> cell.getStringCellValue();
//		case NUMERIC -> String.valueOf(cell.getNumericCellValue());
//		case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
//		case FORMULA -> cell.getCellFormula();
//		default -> "";
//		};
//	}

	public static List<Map<String, String>> getData(String excelPath, String sheetName) {
		List<Map<String, String>> dataList = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(excelPath); 
				Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null)
				return dataList;

			Row headerRow = sheet.getRow(0);
			int lastRow = sheet.getLastRowNum();
			int lastCol = headerRow.getLastCellNum();

			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				Map<String, String> map = new LinkedHashMap<>();
				for (int j = 0; j < lastCol; j++) {
					Cell headerCell = headerRow.getCell(j);
					if (headerCell == null)
						continue;
					String key = headerCell.getStringCellValue();
					Cell cell = row.getCell(j);
					String value = "";
					if (cell != null) {
						switch (cell.getCellType()) {
						case STRING:
							value = cell.getStringCellValue();
							break;
						case NUMERIC:
							value = String.valueOf(cell.getNumericCellValue());
							break;
						case BOOLEAN:
							value = String.valueOf(cell.getBooleanCellValue());
							break;
						case FORMULA:
							value = cell.getCellFormula();
							break;
						default:
							value = cell.toString();
						}
					}
					map.put(key, value);
				}
				dataList.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	public static Map<String, String> getGlobalRow(String excelPath, String sheetName) {
	    List<Map<String, String>> globalData = getData(excelPath, sheetName);
	    return globalData.get(1);
	}
	
	public static List<Map<String, String>> getRowByTestCaseID(String excelPath, String sheetName, String testCaseID) {
		List<Map<String, String>> allRows = getData(excelPath, sheetName);
		List<Map<String, String>> matchingRows = new ArrayList<>();
		
		if (allRows == null || allRows.isEmpty()) {
	        return matchingRows;
	    }
		
		for (Map<String, String> row : allRows) {
			String testID = row.getOrDefault("TestCaseID","").trim();
			if (testID != null && testID.equalsIgnoreCase(testCaseID)) {
				matchingRows.add(row);
			}
		}
		return matchingRows;
	}

	public static void writeResult(String excelPath, String sheetName, String tagName, String columnName,
			String value) {
		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null)
				return;

			Row headerRow = sheet.getRow(0);
			int lastCol = headerRow.getLastCellNum();
			int resultCol = -1;

			for (int i = 0; i < lastCol; i++) {
				Cell c = headerRow.getCell(i);
				if (c != null && c.getStringCellValue().equalsIgnoreCase(columnName)) {
					resultCol = i;
					break;
				}
			}

			if (resultCol == -1) {
				// create new column at the end
				resultCol = lastCol;
				Cell newHeader = headerRow.createCell(resultCol);
				newHeader.setCellValue(columnName);
			}

			int lastRow = sheet.getLastRowNum();
			for (int r = 1; r <= lastRow; r++) {
				Row row = sheet.getRow(r);
				if (row == null)
					continue;
				Cell tagCell = row.getCell(0); // assuming TestCaseID is first column, adapt if not
				if (tagCell != null && tagCell.getStringCellValue().equalsIgnoreCase(tagName)) {
					Cell resultCell = row.getCell(resultCol);
					if (resultCell == null)
						resultCell = row.createCell(resultCol);
					resultCell.setCellValue(value);
					break;
				}
			}

			try (FileOutputStream fos = new FileOutputStream(excelPath)) {
				workbook.write(fos);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
