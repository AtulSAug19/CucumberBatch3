package com.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AdminPage extends BasePage {
	
	@FindBy(xpath="//a[text()='Users']")
	private WebElement usersSubMenu;
	
	public void selectMenuPage(String pageName)
	{
		menuPage(pageName).click();
	}

	public List<String> getAllElements() {
		return getAllElementsText(
				"//nav[@aria-label='Topbar Menu']/ul/li/span | //nav[@aria-label='Topbar Menu']/ul/li/a");
	}
	
	public void verifyUserSubMenu()
	{
		Assert.assertTrue(usersSubMenu.isDisplayed());
	}
	
	public List<String> getAllSubElements() {
		return getAllElementsText(
				"//ul[@class='oxd-dropdown-menu']/li/a");
	}

}
