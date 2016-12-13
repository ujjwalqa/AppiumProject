package com.app.android;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

import com.app.POM.DiscoverPage;
import com.app.POM.FirstLandingScreen;
import com.app.POM.LoginForm;
import com.app.POM.SignUp;
import com.app.utilities.OpenCommandPrompt;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;



public class AndroidTest{

	public WebDriver driver = null;

	AppiumDriverLocalService appiumService;
	String appiumServiceUrl;

	String cmd = "";


	public WebDriver getDriver() {
		return driver;
	}

	@Parameters({"appiumPort", "deviceUDID" ,"emulatorName", "emulatorPort"})
	@BeforeClass
	public void startAppiumAndEmulator(String appiumPort, String deviceUDID, String emulatorName, String emulatorPort) throws InterruptedException
	{
		int bootStrapPort = Integer.valueOf(appiumPort) + 2;
		
		if(deviceUDID == "")
		{
			String appiumStartServerCommand = "appium -U " + "emulator-"+emulatorPort +" -p "+ appiumPort +" --bootstrap-port "+ bootStrapPort +"";
			String emulatorStartCommand = "emulator -avd " + emulatorName +" -scale 0.7  -port " + emulatorPort ;

			OpenCommandPrompt.executeCommand(Arrays.asList(appiumStartServerCommand, emulatorStartCommand));

			Thread.sleep(50000); // Wait till emulator and appium server starts
		}
		else
		{
			String appiumStartServerCommand = "appium -U " + deviceUDID +" -p "+ appiumPort +" --bootstrap-port "+ bootStrapPort +"";
			//String emulatorStartCommand = "emulator -avd " + emulatorName +" -scale 0.7  -port " + emulatorPort ;

			OpenCommandPrompt.executeCommand(Arrays.asList(appiumStartServerCommand));

			Thread.sleep(20000); // Wait till device and appium server starts
		}

	}

	@AfterClass
	@Parameters({"appiumPort", "emulatorPort"})
	public void stopAppiumAndEmulator(String appiumPort, String emulatorPort)
	{
		String getPID = "netstat -o -n -a | findstr \"4723\"";
		OpenCommandPrompt.CloseExecuteCommand(Arrays.asList(getPID));
		
		String stopAppiumServerCommand = "taskkill /F /IM node.exe";
		//String stopAppiumServerCommand = "FOR /F 'usebackq tokens=5' %%i IN (`netstat -aon ^| find '" + appiumPort + "/`') DO taskkill //F //PID %%i";
		OpenCommandPrompt.executeCommand(Arrays.asList(stopAppiumServerCommand));

		String closeAllCommandPrompt = "taskkill /f /im cmd.exe";
		OpenCommandPrompt.executeCommand(Arrays.asList(closeAllCommandPrompt));

		String closeAllEmulator = "taskkill /f /im emulator-x86.exe";
		//String closeAllEmulator = "FOR /F 'usebackq tokens=5' %%i IN (`netstat -aon ^| find '" + emulatorPort + "/`') DO taskkill //F //PID %%i";
		OpenCommandPrompt.executeCommand(Arrays.asList(closeAllEmulator));
		

	}

	@Parameters({"appiumServer", "apkPath", "deviceUDID"})
	@BeforeMethod
	public void setup(String serverURL, String apkRelativePath, String deviceUDID) throws Exception
	{
		// Creating Absolute path using relative path retrieved from testng.xml file
		File apkFilePath = new File(apkRelativePath);

		DesiredCapabilities capabilities = new DesiredCapabilities();

		capabilities.setCapability(MobileCapabilityType.APP, apkFilePath);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidDevice");
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "Android");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 30);
		capabilities.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS, true);
		if(deviceUDID != "")
		{
			capabilities.setCapability(MobileCapabilityType.UDID, deviceUDID);
		}
			

		driver = new AndroidDriver<>(new URL(serverURL), capabilities);

	}

	@AfterMethod
	public void tearDown()
	{
		if (driver != null) {
			driver.quit();
		}
	}


	@Test(description="TestDescription", groups={"Negative", "TestFunctional"}, dataProvider = "credentials", dataProviderClass=TestDataProvider.class)
	public void validateInvalidEmail(String username, String password)
	{

		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.fillUsernamePassword(username, password);
		loginForm.clickOnLogin();
		Assert.assertEquals("Email address invalid.", loginForm.getAlertMessage());
		loginForm.clickAlertOK();
	}

	@Test(groups="Negative")
	public void DoNotEnterEmailPassword()
	{
		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.clickOnLogin();
		Assert.assertEquals("Please Enter Correct Email", loginForm.getAlertTitle());
		loginForm.clickAlertOK();
	}

	@Test(groups="Negative", dataProvider="validEmail", dataProviderClass=TestDataProvider.class)
	public void DoNotEnterPasswordButEmail(String validEmail)
	{
		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.fillUsername(validEmail);
		loginForm.clickOnLogin();
		Assert.assertEquals("Please Enter Password", loginForm.getAlertTitle());
		loginForm.clickAlertOK();
	}

	@Test(groups="Functional", dataProvider="validEmailPassword", dataProviderClass=TestDataProvider.class)
	public void checkLoginWithValidEmailPassword(String validEmail, String validPassword) throws Exception
	{
		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickLoginHere();

		LoginForm loginForm = new LoginForm(driver);
		loginForm.fillUsernamePassword(validEmail, validPassword);
		loginForm.clickOnLogin();
		
		DiscoverPage discoverPage = new DiscoverPage(driver);
		discoverPage.clickOsvCancelIcon();
		discoverPage.clickHighlights();
		Assert.assertEquals(discoverPage.getAvailableHighlights(), "0 Highlights around Current Location within:");
		String selectValueFromDdl = "Bike";
		discoverPage.selectSport(selectValueFromDdl);
		
		Assert.assertEquals(discoverPage.getSelectedOptionSportsFilter(), selectValueFromDdl);
		/*
		ViewProfile viewProfile = new ViewProfile(driver);
		viewProfile.clickSettings();
		viewProfile.clickOnBack();
		viewProfile.clickSettings();
		viewProfile.clickLogOut();
		*/
		
		Thread.sleep(5000);

		/*
		Assert.assertEquals("Please Enter Correct Password", loginForm.getAlertTitle());
		loginForm.clickAlertOK();
		*/
	}

	@Test(groups="Functional", dataProvider="signUpWithValidInformations", dataProviderClass=TestDataProvider.class)
	public void verifySignUpWithValidInformations(String name, String email, String password)
	{
		FirstLandingScreen firstLandingScreen = new FirstLandingScreen(driver);
		firstLandingScreen.clickSignUpWithEmail();

		SignUp signUp = new SignUp(driver);
		signUp.FillName(name).FillEmail(email).FillPassword(password).uncheckIWantNewsLetter().clickSignUp();
		Assert.assertEquals(signUp.getAllRightLetsGoText(), "Alright, Let's Go!");
		signUp.clickAllRightLetsGo();
	}


}


