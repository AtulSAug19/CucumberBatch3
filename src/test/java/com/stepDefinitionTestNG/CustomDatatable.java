package com.stepDefinitionTestNG;

import java.util.Map;

import com.stepDefinitionTestNG.FeaturePOJO;

import io.cucumber.java.DataTableType;

public class CustomDatatable {

	@DataTableType
	public FeaturePOJO transform(Map<String, String> row) {
		FeaturePOJO pojo = new FeaturePOJO();
		pojo.setName(row.get("name"));
		pojo.setEmail(row.get("email"));
		pojo.setCity(row.get("city"));
		return pojo;
	}

}
