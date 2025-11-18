package com.stepDefinitionTestNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {
	
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

}
