package com.stepDefinitionTestNG;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.pages.AdminPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
	
	AdminPage adminPage = new AdminPage();
	
	@Given("user opens login page")
	public void user_opens_login_page() {
	    WebDriver driver = new ChromeDriver();
	    driver.get("http://automationbykrishna.com/");
	    }

	@When("user logs in with username {string} and password {string}")
	public void user_logs_in_with_username_and_password(String string, String string2) {
	    System.out.println("Username:"+string+"--"+"Password: "+string2);
	}

	@Then("login should be validated for {string}")
	public void login_should_be_validated_for(String string) {
	    System.out.println("Validated");
	}
	
	@Given("user navigate to Admin page")
	public void user_navigate_to_admin_page() {
		adminPage.selectMenuPage("Admin");
	}

	@Then("all below menu items gets displayed")
	public void all_below_menu_items_gets_displayed(DataTable dataTable) {
	   List<String> allMenuItems = dataTable.asList();
	   Assert.assertTrue(allMenuItems.containsAll( adminPage.getAllElements()));
	   System.out.println("All items verified");
	   }
	
	@Then("all below sub menu items gets displayed")
	public void all_below_sub_menu_items_gets_displayed(DataTable dataTable) {
	   List<String> allMenuItems = dataTable.asList();
	   Assert.assertTrue(allMenuItems.containsAll( adminPage.getAllSubElements()));
	   System.out.println("All items verified");
	   }

	@When("user click on User Management")
	public void user_click_on_user_management() {
		adminPage.selectMenuPage("User Management");
	}

	@Then("Users should be displayed")
	public void users_should_be_displayed() {
		adminPage.verifyUserSubMenu();
	}

	@Then("user click on Job")
	public void user_click_on_job() {
		adminPage.selectMenuPage("Job");
	}

}
