package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = { "com.stepDefinitionTestNG",
		}, plugin = { "pretty", "html:target/cucumber-html-report" }, monochrome = true)

public class TestRunnerTestNG extends AbstractTestNGCucumberTests {


}
