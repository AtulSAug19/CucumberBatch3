package com.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.stepDefinitionTestNG",
}, plugin = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)

public class TestRunnerParallelTest extends AbstractTestNGCucumberTests {

	
	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}
	
}
