package com.setup;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseSteps {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initDriver(String browser) {
		if (browser == null || browser.isEmpty())
			browser = "chrome";
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			// options.addArguments("--headless=new"); // enable if desired
			options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
			tlDriver.set(new ChromeDriver(options));
		} else {
			throw new IllegalArgumentException("Only chrome implemented in template");
		}
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void quitDriver() {
		if (tlDriver.get() != null) {
			tlDriver.get().quit();
			tlDriver.remove();
		}
	}

	public static String takeScreenshot(String name) {
		try {
			WebDriver driver = getDriver();
			if (driver == null)
				return null;
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/target/screenshots/" + name + ".png";
			File dest = new File(path);
			dest.getParentFile().mkdirs();
			Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
