package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id = "username")
	WebElement usernameInput;
	
	@FindBy(id = "password")
	WebElement passwordInput;
	
	@FindBy(id = "login")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

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

}
