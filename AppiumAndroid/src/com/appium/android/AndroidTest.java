package com.appium.android;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;



public class AndroidTest {

	public AndroidDriver driver;

	@BeforeMethod
	public void setup() throws Exception
	{
		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.APP, "C:\\Users\\ukumar\\Desktop\\Appium\\de.komoot.android-7.2.8-www.APK4Fun.com.apk");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidDevice");
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

	@Test(groups="perf", dataProvider = "credentials", dataProviderClass=TestDataProvider.class)
	public void firstTestCase(String username, String Password)
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


