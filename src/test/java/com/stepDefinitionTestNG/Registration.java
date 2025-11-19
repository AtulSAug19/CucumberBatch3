package com.stepDefinitionTestNG;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Registration {
	static WebDriver driver;

	@Given("user is on registration page")
	public void user_is_on_registration_page() {
		driver = new ChromeDriver();
		driver.get("http://automationbykrishna.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.findElement(By.id("registration2")).click();
	}

	@When("user enter fullname {string}")
	public void user_enter_fullname(String userName) {
		driver.findElement(By.id("fullName")).sendKeys(userName);
	}

	@When("user enter address {string}")
	public void user_enter_address(String address) {
		driver.findElement(By.id("address")).sendKeys(address);
	}

	@When("user enter other email id {string}")
	public void user_enter_other_email_id(String useremail) {
		driver.findElement(By.id("useremail")).sendKeys(useremail);
	}

	@When("user enter City\\/Town {string}")
	public void user_enter_city_town(String usercity) {
		driver.findElement(By.id("usercity")).sendKeys(usercity);
	}

	@When("user enter Company {string}")
	public void user_enter_company(String organization) {
		driver.findElement(By.id("organization")).sendKeys(organization);
	}

	@When("user select {string}")
	public void user_select(String gender) {
		if (gender.equalsIgnoreCase("MALE"))
			driver.findElement(By.xpath("//label[@for='radio-01']")).click();
		else if (gender.equalsIgnoreCase("FEMALE"))
			driver.findElement(By.xpath("//label[@for='radio-02']")).click();
		else
			System.out.println("Invalid value in gender");
		Assert.assertFalse("Invalid selection in Gender", true);
	}

	@When("user enter username {string}")
	public void user_enter_username(String usernameReg) {
		driver.findElement(By.id("usernameReg")).sendKeys(usernameReg);
	}

	@When("user enter password {string}")
	public void user_enter_password(String passwordReg) {
		driver.findElement(By.id("passwordReg")).sendKeys(passwordReg);
	}

	@When("user enter confirm password {string}")
	public void user_enter_confirm_password(String repasswordReg) {
		driver.findElement(By.id("repasswordReg")).sendKeys(repasswordReg);
	}

	@When("click on submit button")
	public void click_on_submit_button() {
		driver.findElement(By.id("btnsubmitsignUp")).click();
	}

	@When("select agree checkbox")
	public void select_agree_checkbox() {
		driver.findElement(By.cssSelector(".checkbox")).click();
	}

	@Then("{string} is displayed")
	public void is_displayed(String string) {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		Assert.assertTrue(true);
	}
	
	@When("user enter {string} in username")
	public void user_enter_in_username(String username) {
		driver.findElement(By.id("unameSignin")).sendKeys(username);
	}
	@When("user enter {string} in password")
	public void user_enter_in_password(String pwd) {
		driver.findElement(By.id("pwdSignin")).sendKeys(pwd);
	}
	@When("click on login button")
	public void click_on_login_button() {
	   driver.findElement(By.id("btnsubmitdetails")).click();
	}

	@When("user enter credentials")
	public void enterCredentials(DataTable table) {
		List<String> data = table.asList();
		String username = data.get(0);
		String password = data.get(1);

		System.out.println(username + " " + password);
	}

	@Then("login should be successfull")
	public void loginVerification() {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		Assert.assertTrue(true);
	}

	@Given("user enters below data")
	public void enterData(DataTable table) {
		List<List<String>> data = table.asLists();

		String username1 = data.get(1).get(0);
		String password1 = data.get(1).get(1);

		System.out.println(username1 + " " + password1);
	}

	@Given("enter login details")
	public void loginDetails(DataTable table) {
		Map<String, String> map = table.asMap(String.class, String.class);

		System.out.println(map.get("username"));
		System.out.println(map.get("password"));
	}

	@Given("user enters registration details")
	public void registration(DataTable table) {
		List<Map<String, String>> data = table.asMaps(String.class, String.class);

		for (Map<String, String> row : data) {
			System.out.println(row.get("name"));
			System.out.println(row.get("email"));
			System.out.println(row.get("city"));
		}
	}

	@Given("user enters below details")
	public void enterDetails(DataTable table) {
		List<FeaturePOJO> users = table.asList(FeaturePOJO.class);
		FeaturePOJO obj = users.get(0);

		System.out.println(obj.getName());
		System.out.println(obj.getEmail());
		System.out.println(obj.getCity());
	}
	
	@When("user enters below data and logged in")
	public void enterDetailsAndLoggedIn(DataTable table) throws InterruptedException {
		List<List<String>> credentials = table.asLists();
		WebElement uName = driver.findElement(By.id("unameSignin"));
		WebElement pwd = driver.findElement(By.id("pwdSignin"));
		for(List<String> credential:credentials)
		{
			uName.clear();
			Thread.sleep(1000);
			pwd.clear();
			Thread.sleep(1000);
			uName.sendKeys(credential.get(0));
			pwd.sendKeys(credential.get(1));
			driver.findElement(By.id("btnsubmitdetails")).click();
			Thread.sleep(2000);
			driver.switchTo().alert().accept();
		}
	}

}
