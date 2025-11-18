package com.runner;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.Parameters.ExcelReader;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.stepDefinitionTestNG",
		}, plugin = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)

public class TestRunnerTestNG extends AbstractTestNGCucumberTests {

//	@Override
//	@DataProvider(parallel = false)
//	public Object[][] scenarios() {
//		// Use Excel to generate scenarios
//		String excelPath = "src/test/resources/TestData.xlsx";
//		ExcelReader excel = new ExcelReader(excelPath, "Login");
//		List<Map<String, String>> rows = excel.getDataAsMap();
//		Object[][] data = new Object[rows.size()][1];
//		for (int i = 0; i < rows.size(); i++) {
//			data[i][0] = rows.get(i);
//		}
//		return data;
//	}

}
