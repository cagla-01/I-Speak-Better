package com.qa.ispeakbetter.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.qa.ispeakbetter.util.ElementUtil;


public class LoginPage {

	// Locatars
	// Constructors
	// Page Methods

	WebDriver driver;
	ElementUtil elementUtil;

	By redeemButton = By.xpath("//a[contains(text(),'Redeem demo class')]");
	By welcomeText = By.id("dashboard_goodday");
	By libraryBtn = By.linkText("Library");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);

	}

	public String getAccountName() {
		String text = elementUtil.doGetText(welcomeText);
		System.out.println(text);
		return text;
	}

	public String getPageTitle() {
		return elementUtil.doGetPageTitle();
	}

	public boolean isRedeemButtonEnabled() {

		return elementUtil.getElement(redeemButton).isEnabled();

	}

	public LibraryPage navigateToLibrary() {
		elementUtil.doClick(libraryBtn);
		return new LibraryPage(driver);
	}
}
