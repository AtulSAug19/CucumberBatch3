package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/OrangeHRMLogin.feature", 
glue = { "com.stepDefinitionTestNG","com.hooks"
		}, plugin = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)

public class TestRunnerTestNG extends AbstractTestNGCucumberTests {


}
