package com.qa.ispeakbetter.tests;

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

public class LoginPageTest {
	WebDriver driver;
	Properties prop;
	BasePage base;
	HomePage homePage;
	LoginPage loginPage;
	Credentials usercred;

	@BeforeMethod
	public void setUp() { /// home - login -- selectphone - cart - payment

		base = new BasePage();
		prop = base.init_properties();
		String browsername = prop.getProperty("browser");
		driver = base.init_drver(browsername);
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
		usercred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
		loginPage = homePage.clickLogin(usercred);

	}

	@Test(priority = 1, description = "verify the current title")
	public void verifyTitle() {
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, CONSTANTS.LOGINPAGETITLE);
	}

	@Test(priority = 1, description = "verify if reedem button is enabled or not")
	public void verifyRedeemButtonEnabled() {
		Assert.assertTrue(loginPage.isRedeemButtonEnabled());
	}

	@Test
	public void clickToLibraryButton() {
		LibraryPage libraryPage = loginPage.navigateToLibrary();
		String headerText = libraryPage.getHeaderText();
		Assert.assertTrue(headerText.contains("Library"));
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
