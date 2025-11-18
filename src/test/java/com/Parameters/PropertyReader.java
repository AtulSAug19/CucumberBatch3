package com.Parameters;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	
	  static Properties prop;

	    public static void init() {
	        try {
	            prop = new Properties();
	            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	            prop.load(fis);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static String get(String key) {
	    	if (prop.isEmpty()) 
	    		init();
	        return prop.getProperty(key);
	    }

}
