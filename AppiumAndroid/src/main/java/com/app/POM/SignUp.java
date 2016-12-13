package com.app.POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.app.utilities.Utilities;

public class SignUp 
{
	WebDriver driver;

	public SignUp(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	/*
	 * All web elements declaration will go here
	 */

	@FindBy(how=How.ID, using="de.komoot.android:id/edittext_display_name")
	WebElement txtName;

	@FindBy(how=How.ID, using="de.komoot.android:id/edittext_email")
	WebElement txtEmail;

	@FindBy(how=How.ID, using="de.komoot.android:id/edittext_password")
	WebElement txtPassword;

	@FindBy(how=How.ID, using="de.komoot.android:id/checkbox_product_updates")
	WebElement chkIWantNewsLetter;

	@FindBy(how=How.ID, using="de.komoot.android:id/button_register")
	WebElement btnSignUp;

	@FindBy(how=How.ID, using="de.komoot.android:id/textview_accept_conditions")
	WebElement linkTermsOfService;
	
	@FindBy(how=How.ID, using="de.komoot.android:id/oia_lets_go_tb")
	WebElement btnAllRightLetsGo;

	@FindBy(how=How.ID, using="android:id/alertTitle")
	WebElement alertTitle;

	@FindBy(how=How.ID, using="android:id/message")
	WebElement alertMessage;

	@FindBy(how=How.ID, using="android:id/button1")
	WebElement alertOK;

	/*
	 * All Actions will go here
	 */

	/**
	 * Fill Name on the Sign Up page
	 * @param name
	 * @return
	 */
	public SignUp FillName(String name) 
	{
		Utilities.waitForElement(txtName).sendKeys(name);
		return this;
	}

	/**
	 * Fill Email on the Sign Up page
	 * @param name
	 * @return
	 */
	public SignUp FillEmail(String email) 
	{
		Utilities.waitForElement(txtEmail).sendKeys(email);
		return this;
	}

	/**
	 * Fill password on the Sign Up page
	 * @param name
	 * @return
	 */
	public SignUp FillPassword(String password) 
	{
		Utilities.waitForElement(txtPassword).sendKeys(password);
		return this;
	}

	/**
	 * Check I want the news letter options
	 * @return
	 */
	public SignUp checkIWantNewsLetter() 
	{
		if(Utilities.waitForElement(chkIWantNewsLetter).getAttribute("checked") == null)
			Utilities.waitForElement(chkIWantNewsLetter).click();
	
		return this;
	}

	/**
	 * Un check I want the news letter options
	 * @return
	 */
	public SignUp uncheckIWantNewsLetter() 
	{
		if(Utilities.waitForElement(chkIWantNewsLetter).getAttribute("checked") != null)
			Utilities.waitForElement(chkIWantNewsLetter).click();
		return this;
	}

	/**
	 * Click on Sign button
	 * @return
	 */
	public SignUp clickSignUp() 
	{
		Utilities.waitForElement(btnSignUp).click();
		return this;
	}
	
	/**
	 * Click on Term of Service
	 * @return
	 */
	public SignUp clickTermsOfService() 
	{
		Utilities.waitForElement(linkTermsOfService).click();
		return this;
	}
	
	/**
	 * Click on AllRightLetsGo button
	 * @return
	 */
	public SignUp clickAllRightLetsGo()
	{
		Utilities.waitForElement(btnAllRightLetsGo).click();
		return this;
	}
	
	/**
	 * Get text from AllRightLetsGo
	 * @return
	 */
	public String getAllRightLetsGoText() 
	{
		return Utilities.waitForElement(btnAllRightLetsGo).getText();
	}

	/**
	 * Get the text from validation pop message if appear
	 * @return {@link String}
	 */
	public String getAlertTitle() 
	{
		return Utilities.waitForElement(alertTitle).getText();
	}

	/**
	 * Get the text from validation pop message if appear
	 * @return {@link String}
	 */
	public String getAlertMessage() 
	{
		return Utilities.waitForElement(alertMessage).getText();
	}

	/**
	 * Click on OK of validation message if appear other wise throw time out error
	 * @return {@link SignUp}
	 */
	public SignUp clickAlertOK()
	{
		Utilities.waitForElement(alertOK).click();
		return this;
	}
	
}
