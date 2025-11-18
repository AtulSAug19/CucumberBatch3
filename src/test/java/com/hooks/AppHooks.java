package com.hooks;

import com.Parameters.PropertyReader;
import com.setup.BaseSteps;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class AppHooks {

	BaseSteps driverFactory;

	@Before
	public void init() {
//		PropertyReader.init();
//		driverFactory = new BaseSteps();
//		String browser = PropertyReader.get("browser");
//		driverFactory.initDriver(browser);
//		BaseSteps.getDriver().get(PropertyReader.get("baseUrl"));
	}

	@After
	public void tearDown() {
//		BaseSteps.quitDriver();
	}

}
