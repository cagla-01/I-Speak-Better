package com.qa.ispeakbetter.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ispeakbetter.base.BasePage;
import com.qa.ispeakbetter.page.HomePage;
import com.qa.ispeakbetter.page.LibraryPage;
import com.qa.ispeakbetter.page.LoginPage;
import com.qa.ispeakbetter.util.CONSTANTS;
import com.qa.ispeakbetter.util.Credentials;

public class LibraryPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	HomePage homePage;
	LoginPage loginPage;
	LibraryPage libraryPage;
	Credentials usercred;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browsername = prop.getProperty("browser");
		driver = basePage.init_drver(browsername);
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
		usercred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		loginPage = homePage.clickLogin(usercred);
		libraryPage = loginPage.navigateToLibrary();
	}

	@Test(priority = 2, description = "verify if chat box works")
	public void chatWithRobot() {
		String waitingText = libraryPage.chatWithRobot();
		System.out.println(waitingText);
		Assert.assertTrue(waitingText.contains(CONSTANTS.CHATWAITINGAREA));
	}

	@Test(priority = 3, description = "go to login(home) page")
	public void goToLoginPage() {
		boolean checkLogo = libraryPage.checkLogoAndgoToLogin();
		Assert.assertTrue(checkLogo);
		String greetingText = loginPage.getAccountName();
		Assert.assertTrue(greetingText.contains(CONSTANTS.GREETINGNAME));
	}

	@Test(priority = 1, description = "get header links")
	public void HeaderLinks() {
		List<String> headerLinksList = libraryPage.getHeadBarList();
		String headerLinks = headerLinksList.toString();
		Assert.assertEquals(headerLinks, CONSTANTS.HEADERLIST);

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
