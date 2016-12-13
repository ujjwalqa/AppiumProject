package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class ViewProfile
{
	WebDriver driver;

	/**
	 * Initializing all elements of this screen
	 * @param driver
	 */
	public ViewProfile(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}

	/*
	 * All elements deceleration will go here
	 */
	
	@FindBy(how=How.ID, using="de.komoot.android:id/user_name")
	WebElement lblUserName;

	@FindBy(how=How.ID, using="de.komoot.android:id/textview_number_follower")
	WebElement lblFollowersCount;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/textview_number_following")
	WebElement lblFollowingCount;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/buttonSettings")
	WebElement btnSettings;
	
	@FindBy(how=How.XPATH, using="//android.widget.TextView[@text='Log Out']")
	WebElement optionLogout;
	
	@FindBy(how=How.XPATH, using="//android.widget.ImageButton[@index='0']")
	WebElement btnBackImage;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabPlanning")
	WebElement navDiscover;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabPlanning")
	WebElement navPlanning;

	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabMap")
	WebElement navMap;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabRegions")
	WebElement navRegions;
	
	/*
	 * All actions will go here
	 */

	/**
	 * Click on View Map
	 */
	public void clickNavigateToPlanning() 
	{
		Utilities.waitForElement(navPlanning).click();
	}
	
	/**
	 * Click on View Map
	 */
	public void clickNavigateToMap() 
	{
		Utilities.waitForElement(navMap).click();
	}

	/**
	 * Click on View Discover
	 */
	public void clickNavigateToDiscover() 
	{
		Utilities.waitForElement(navDiscover).click();
	}

	/**
	 * Click on View Discover
	 */
	public void clickNavigateToRegions() 
	{
		Utilities.waitForElement(navRegions).click();
	}
	
	/**
	 * Get user name of logged user
	 */
	public String getUserName() 
	{
		return Utilities.waitForElement(lblUserName).getText();
	}
	
	/**
	 * Get count of followers
	 */
	public String getFollowersCount() 
	{
		return Utilities.waitForElement(lblFollowersCount).getText();
	}
	
	/**
	 * Get count of Followings
	 */
	public String getFollowingCount() 
	{
		return Utilities.waitForElement(lblFollowingCount).getText();
	}
	
	/**
	 * Click on the settings icon
	 */
	public ViewProfile clickSettings()
	{
		Utilities.waitForElement(btnSettings).click();
		return this;
	}
	
	/**
	 * Click on back icon image 
	 */
	public ViewProfile clickOnBack()
	{
		Utilities.waitForElement(btnBackImage).click();
		return this;
	}
	
	
	/**
	 * Click on Log out
	 */
	public void clickLogOut()
	{
		this.driver = Utilities.scrollTo(this.driver, "Log Out");
		Utilities.waitForElement(optionLogout).click();
	}
	
}
