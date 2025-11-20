package com.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BasePage {

	@FindBy(name = "username")
	WebElement usernameInput;

	@FindBy(name = "password")
	WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//h5[text()='Login']")
	WebElement loginTitle;

	@FindBy(xpath = "//span[text()='Dashboard']")
	WebElement dashboardLink;

	@FindBy(css = ".oxd-topbar-header-userarea i")
	WebElement menuItemForLogout;

	@FindBy(xpath = "//a[text()='Logout']")
	WebElement logoutLink;

	public void enterUsername(String user) {
		usernameInput.clear();
		usernameInput.sendKeys(user);
	}

	public void enterPassword(String pass) {
		passwordInput.clear();
		passwordInput.sendKeys(pass);
	}

	public void clickLogin() {
		loginBtn.click();
	}

	public void verifyLoginPage() {
		Assert.assertTrue(loginTitle.isDisplayed());
	}

	public void verifyDashboardPage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		waitForClickable(dashboardLink);
		if (dashboardLink.isDisplayed()) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

	public void logout() {
		menuItemForLogout.click();
		if (logoutLink.isDisplayed()) {
			logoutLink.click();
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}

	}

}
