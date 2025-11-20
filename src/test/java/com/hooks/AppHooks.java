package com.hooks;

import com.Parameters.PropertyReader;
import com.setup.BaseSteps;
import com.utility.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class AppHooks {

	BaseSteps driverFactory;
	public static String currentTag = "";
	public static Scenario currentScenario = null;

	@Before(order=1)
	public void init() {
		PropertyReader.init();
		driverFactory = new BaseSteps();
		String browser = PropertyReader.get("browser");
		driverFactory.initDriver(browser);
		BaseSteps.getDriver().get(PropertyReader.get("baseUrl"));
	}
	
	 @Before(order=0)
	    public void getScenarioTag(Scenario scenario) {
		 	currentScenario = scenario;
		 	if (scenario.getSourceTagNames() != null && !scenario.getSourceTagNames().isEmpty()) {
		 		currentTag = scenario.getSourceTagNames().iterator().next().replace("@", "");
		 	}
	        System.out.println("Running with Tag: " + currentTag);
	    }

	@After
	public void tearDown(Scenario scenario) {
		if(scenario.isFailed())
		{
			ScreenshotUtil.take(scenario.getSourceTagNames().iterator().next().toString());
		}
		BaseSteps.quitDriver();
	}

}
