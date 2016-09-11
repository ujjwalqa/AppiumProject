package com.app.listeners;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.app.utilities.Utilities;
import com.appium.android.AndroidTest;


public class TestListeners implements ITestListener

{

	@Override
	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("On Finish");
	}

	@Override
	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("On Start");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult itr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailure(ITestResult itr) {

		// TODO Auto-generated method stub
		String insName = itr.getInstance().getClass().getSimpleName();
		if(insName.equalsIgnoreCase("AndroidTest"))
		{
			AndroidTest currentClass = (AndroidTest) itr.getInstance();
			WebDriver driver = currentClass.getDriver();
			Utilities.takeScreenshot(driver);
		}
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		System.out.println("On Test Start");
	}

	@Override
	public void onTestSuccess(ITestResult itr) {
		// TODO Auto-generated method stub
		String insName = itr.getInstance().getClass().getSimpleName();
		if(insName.equalsIgnoreCase("AndroidTest"))
		{
			AndroidTest currentClass = (AndroidTest) itr.getInstance();
			WebDriver driver = currentClass.getDriver();
			Utilities.takeScreenshot(driver);
		}

	}

}
