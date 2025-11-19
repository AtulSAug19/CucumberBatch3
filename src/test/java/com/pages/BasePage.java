package com.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Parameters.ExcelReader;
import com.Parameters.PropertyReader;
import com.hooks.AppHooks;
import com.setup.BaseSteps;

public class BasePage {

	protected WebDriver driver;
	private WebDriverWait wait;
	public static Map<String, String> data;

	public BasePage() {
		this.driver = BaseSteps.getDriver();

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public static void loadData() {
		String path = PropertyReader.get("TestDataPath");
		String sheet = PropertyReader.get("TestDataSheetName");
		String tag = AppHooks.currentTag; // set by Hooks

		data = ExcelReader.getRowByTestCaseID(path, sheet, tag);
		if (data == null) {
			throw new RuntimeException("No test data found for tag: " + tag);
		}
	}

	protected void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void click(WebElement element) {
		waitForVisibility(element);
		element.click();
	}

	protected void type(WebElement element, String text) {
		waitForVisibility(element);
		element.clear();
		element.sendKeys(text);
	}

	protected void openPage(String url) {
		driver.get(url);
	}
}
