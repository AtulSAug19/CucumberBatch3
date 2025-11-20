package com.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
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
	public static List<Map<String, String>> data;

	public BasePage() {
		this.driver = BaseSteps.getDriver();

		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public List<String> getAllElementsText(String xpath) {
		List<WebElement> allMenuNames = driver.findElements(
				By.xpath(xpath));
		List<String> allElementText = new ArrayList<String>();
		for (WebElement text : allMenuNames) {
			allElementText.add(text.getText());
		}
		return allElementText;
	}

	public WebElement menuPage(String menuPageName) {
		String xpath = String.format("//span[normalize-space()='%s']", menuPageName);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForVisibility(driver.findElement(By.xpath(xpath)));
		return driver.findElement(By.xpath(xpath));
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

	protected void waitForClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
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
