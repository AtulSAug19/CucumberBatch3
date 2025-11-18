package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {
	
	private static ExtentReports extent;
	
	public synchronized static ExtentReports getInstance() {
		if (extent == null) {
		extent = new ExtentReports();
		String path = System.getProperty("user.dir") + "/target/extentreport/ExtentReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Selenium Cucumber Tests");
		spark.config().setTheme(Theme.STANDARD);
		extent.attachReporter(spark);
		}
		return extent;
		}

}
