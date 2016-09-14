package com.app.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utilities

{
	/**
	 * 
	 * @param driver
	 * @param testStatus Failed/Passed
	 */
	
	public static void takeScreenshot(WebDriver driver, String testStatus)
	{
		String destDir;
		DateFormat dateFormat;

		// Set folder name to store screenshots.
		destDir = "screenshots/"+testStatus;
		// Capture screenshot.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Set date format to set It as screenshot file name.
		dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		// Create folder under project with name "screenshots" provided to destDir.
		new File(destDir).mkdirs();
		// Set file name using current date time.
		String destFile = dateFormat.format(new Date()) + ".png";

		try {
			// Copy paste file at destination folder location
			  FileUtils.copyFile(scrFile, new File(destDir + "/" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

