package com.stepDefinitionTestNG;

import com.pages.BasePage;
import com.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	LoginPage login = new LoginPage();
	
	@Given("fixture is loaded")
	public void fixture_is_loaded() {
	    BasePage.loadData();
	}
	
	@Given("user navigate to login page")
	public void user_navigate_to_login_page() {
	    login.verifyLoginPage();
	}
	@When("enter username in username field")
	public void enter_username_in_username_field() {
	    login.enterUsername(BasePage.data.get(0).get("Username"));
	}
	@When("enter password in password field")
	public void enter_password_in_password_field() {
	    login.enterPassword(BasePage.data.get(0).get("Password"));
	}
	@When("Click on Login button")
	public void click_on_login_button() {
	    login.clickLogin();
	}
	@Then("Dashboard page should be displayed")
	public void dashboard_page_should_be_displayed() {
	   login.verifyDashboardPage();
	}
	
	@When("user enter credentials and login")
	public void user_enter_credentials_and_login() {
		for(int i=0;i<BasePage.data.size();i++) {
		    login.enterUsername(BasePage.data.get(i).get("Username"));
		    login.enterPassword(BasePage.data.get(i).get("Password"));
		    login.clickLogin();
		    login.verifyDashboardPage();
		    login.logout();
		}
	}
	
	@When("User logged out from application")
	public void user_logged_out_from_application() {
	login.logout();
	}


}
