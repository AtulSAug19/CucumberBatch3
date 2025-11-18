package com.Parameters;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.cucumber.java.sl.Ce;

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

	// Returns list of maps: each map = columnName -> value
	public List<Map<String, String>> getDataAsMap() {
		List<Map<String, String>> data = new ArrayList<>();
		if (sheet == null)
			return data;
		Row headerRow = sheet.getRow(0);
		int lastRow = sheet.getLastRowNum();
		int lastCol = headerRow.getLastCellNum();
		for (int r = 1; r <= lastRow; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			Map<String, String> rowData = new HashMap<>();
			boolean allBlank = true;
			for (int c = 0; c < lastCol; c++) {
				Cell headerCell = headerRow.getCell(c);
				if (headerCell == null)
					continue;
				String header = headerCell.getStringCellValue().trim();
				Cell cell = row.getCell(c);
				String value = getCellValueAsString(cell);
				if (value != null && !value.isEmpty())
					allBlank = false;
				rowData.put(header, value);
			}
			if (!allBlank)
				data.add(rowData);
		}
		return data;
	}

	private String getCellValueAsString(Cell cell) {
		if (cell == null)
			return "";
		return switch (cell.getCellType()) {
		case STRING -> cell.getStringCellValue();
		case NUMERIC -> String.valueOf(cell.getNumericCellValue());
		case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
		case FORMULA -> cell.getCellFormula();
		default -> "";
		};
	}

}
