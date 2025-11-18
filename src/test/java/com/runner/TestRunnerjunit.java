package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/login.feature" }, glue = { "com.stepDefinitionTestNG"}, plugin = {
		"pretty",
		"html:target/cucumber-report.html",
        "json:target/cucumber.json",
        "junit:target/cucumber.xml",
		"rerun:target/rerun.txt" }, monochrome = true)
public class TestRunnerjunit {
	
	

}
