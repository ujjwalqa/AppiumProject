package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class DiscoverPage
{

	WebDriver driver;

	/**
	 * Initializing all elements of this screen
	 * @param driver
	 */
	public DiscoverPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(this.driver, this);

	}

	/*
	 * All elements deceleration will go here
	 */

	@FindBy(how=How.ID, using="de.komoot.android:id/osv_cancel_iv")
	WebElement osvCancelIcon;

	@FindBy(how=How.ID, using="de.komoot.android:id/ishv_location_tv")
	WebElement linkChangeLocation;

	@FindBy(how=How.ID, using="de.komoot.android:id/ifhv_sports_button_ttv")
	WebElement ddlAllSports;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/layout_map_position_item")
	WebElement optionChooseOnMap;

	@FindBy(how=How.ID, using="de.komoot.android:id/layout_current_position_item")
	WebElement optionseCurrentLocation;

	@FindBy(how=How.XPATH, using="//android.widget.ImageButton[@index='0']")
	WebElement btnBackImage;

	@FindBy(how=How.ID, using="de.komoot.android:id/itbv_collection_tab_text_ttv")
	WebElement linkCollection;

	@FindBy(how=How.ID, using="de.komoot.android:id/itbv_places_tab_text_ttv")
	WebElement linkHighlights;

	@FindBy(how=How.ID, using="de.komoot.android:id/itbv_routes_tab_text_ttv")
	WebElement linkTours;

	@FindBy(how=How.ID, using="de.komoot.android:id/ifhv_elements_text")
	WebElement lblAvailableHighlights;

	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabPlanning")
	WebElement navPlanning;

	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabMap")
	WebElement navMap;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabProfile")
	WebElement navProfile;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/imageViewTabRegions")
	WebElement navRegions;
	/*
	 * All actions will go here
	 */

	/**
	 * Cancel the osv after login
	 */
	public DiscoverPage clickOsvCancelIcon() 
	{
		Utilities.waitForElement(osvCancelIcon).click();
		return this;
	}

	/**
	 * Click on choose on map option
	 * @return {@link DiscoverPage}
	 */
	public DiscoverPage selectChooseOnMap()
	{
		Utilities.waitForElement(optionChooseOnMap).click();
		return this;
	}

	/**
	 * Click on use current location
	 * @return {@link DiscoverPage}
	 */
	public DiscoverPage selectUseCurrentLocation()
	{
		Utilities.waitForElement(optionseCurrentLocation).click();
		return this;
	}	
	
	/**
	 * Click on back icon image 
	 */
	public DiscoverPage clickOnBack()
	{
		Utilities.waitForElement(btnBackImage).click();
		return this;
	}

	/**
	 * Click on Collection to view the list of collections
	 * This is the default selection after landing to this page post successful login
	 * @return {@link DiscoverPage}
	 */
	public DiscoverPage clickCollection()
	{
		Utilities.waitForElement(linkCollection).click();
		return this;
	}

	/**
	 * Click on Discover to view the list of Discoveries
	 * @return {@link DiscoverPage}
	 */
	public DiscoverPage clickHighlights()
	{
		Utilities.waitForElement(linkHighlights).click();
		return this;
	}

	/**
	 * Click on Discover to view the list of Discoveries
	 * @return {@link DiscoverPage}
	 */
	public DiscoverPage clickTours()
	{
		Utilities.waitForElement(linkTours).click();
		return this;
	}
	
	/**
	 * Click and select values Sport filter drop down
	 */
	public DiscoverPage selectSport(String value) 
	{
		Utilities.waitForElement(ddlAllSports).click();
		this.driver = Utilities.selectValueFromDropdown(this.driver, value);
		return this;
	}

	/**
	 * Verify the selected value in the drop down
	 */
	public String getSelectedOptionSportsFilter() 
	{
		return Utilities.waitForElement(ddlAllSports).getText();
	}
	
	/**
	 * Get the available highlights
	 */
	public String getAvailableHighlights() 
	{
		return Utilities.waitForElement(lblAvailableHighlights).getText();
	}
	
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
	 * Click on View Profile
	 */
	public void clickNavigateToProfile() 
	{
		Utilities.waitForElement(navProfile).click();
	}

	/**
	 * Click on View Regions
	 */
	public void clickNavigateToRegions() 
	{
		Utilities.waitForElement(navRegions).click();
	}
}
