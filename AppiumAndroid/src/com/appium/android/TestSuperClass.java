package com.appium.android;

import org.openqa.selenium.WebDriver;

public class TestSuperClass 
{
	public WebDriver driver = null;
	
	public TestSuperClass(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public WebDriver getDriver() {
		return driver;
	}

	

}
