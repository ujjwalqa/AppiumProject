package com.appium.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;



public class AndroidTest{

	public WebDriver driver = null;

	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;

	String cmd = "";


	public WebDriver getDriver() {
		return driver;
	}

	/*
	@BeforeClass
	public void testClassSetUp() 
	{
		CommandLine command = new CommandLine("cmd");
		command.addArgument("\\c");
		command.addArgument("C:/Program Files (x86)/Appium/node.exe");
		command.addArgument("C:/Program Files (x86)/Appium/node_modules/appium/bin/Appium.js");
		command.addArgument("--address");
		command.addArgument("http:\\127.0.0.1");
		command.addArgument("--port");
		command.addArgument("4723");
		command.addArgument("--no-reset");
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(15000);
			System.out.println("Appium server started.");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			// TODO: handle exception
		}

	}

	@AfterClass
	public void testClassCleanup()
	{
		CommandLine command = new CommandLine("taskkill");
		command.addArgument("\\F");
		command.addArgument("\\IM", false);
		command.addArgument("node.exe");
		
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(1);
		try {
			executor.execute(command, resultHandler);
			Thread.sleep(5000);
			System.out.println("Appium server stopped.");
		} catch (Exception e) {
			e.printStackTrace(System.err);
			// TODO: handle exception
		}
	}
	
	*//*  @BeforeClass
	public void testClassSetUp() throws Exception
	{
		System.out.println("In Test Class");
		try {
			//			appiumService = AppiumDriverLocalService.buildDefaultService();
			//			appiumService.start();
			//	        appiumServiceUrl = appiumService.getUrl().toString();

			String Appium_Node_Path="C:/Program Files (x86)/Appium/node.exe";
			String Appium_JS_Path="C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js";
			AppiumDriverLocalService appiumService;

			appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().
					usingPort(4723).usingDriverExecutable(new File(Appium_Node_Path)).
					withAppiumJS(new File(Appium_JS_Path)));
			appiumService.start();
			appiumServiceUrl = appiumService.getUrl().toString();
			Thread.sleep(5000);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			// TODO: handle exception
		}

	}
	 */

	@BeforeMethod
	public void setup() throws Exception
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();



		capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\ukumar\\Desktop\\Appium\\de.komoot.android-7.2.8-www.APK4Fun.com.apk");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidDevice");
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

		driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(groups="Functional", dataProvider = "credentials", dataProviderClass=TestDataProvider.class)
	public void validateInvalidEmail(String username, String Password)
	{
		// click on login here link
		driver.findElement(By.id("de.komoot.android:id/textview_login")).click();

		// Enter invalid email address
		driver.findElement(By.id("de.komoot.android:id/edittext_email")).sendKeys(username);

		//Enter Password
		driver.findElement(By.id("de.komoot.android:id/edittext_password")).sendKeys(Password);

		//Click on Login
		driver.findElement(By.id("de.komoot.android:id/button_login")).click();

		// waiting for message to appear
		WebElement successMessage = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));

		//Console output of test message in the alert
		System.out.println(successMessage.getText());

		// asserting the message displayed
		Assert.assertEquals("Email address invalid.", successMessage.getText());

		//Locating OK button
		WebElement alertOK = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1")));

		//just getting console output if OK button found or not
		if(alertOK != null)
			System.out.println("Alert OK button found");

		//click on OK button
		alertOK.click();
	}

	@Test(dataProvider = "credentials", dataProviderClass=TestDataProvider.class, enabled=false)
	public void SecondTestCase(String username, String Password)
	{
		// click on login here link
		driver.findElement(By.id("de.komoot.android:id/textview_login")).click();

		// Enter invalid email address
		driver.findElement(By.id("de.komoot.android:id/edittext_email")).sendKeys(username);

		//Enter Password
		driver.findElement(By.id("de.komoot.android:id/edittext_password")).sendKeys(Password);

		//Click on Login
		driver.findElement(By.id("de.komoot.android:id/button_login")).click();

		// waiting for message to appear
		WebElement successMessage = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/message")));

		//Console output of test message in the alert
		System.out.println(successMessage.getText());

		// asserting the message displayed
		Assert.assertEquals("Email address invalid.", successMessage.getText());

		//Locating OK button
		WebElement alertOK = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button1")));

		//just getting console output if OK button found or not
		if(alertOK != null)
			System.out.println("Alert OK button found");

		//click on OK button
		alertOK.click();
	}
	
}


