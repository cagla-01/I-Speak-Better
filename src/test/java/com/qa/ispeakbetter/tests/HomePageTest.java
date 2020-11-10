package com.qa.ispeakbetter.tests;


import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.qa.ispeakbetter.base.BasePage;
import com.qa.ispeakbetter.page.HomePage;
import com.qa.ispeakbetter.page.LoginPage;
import com.qa.ispeakbetter.util.CONSTANTS;
import com.qa.ispeakbetter.util.Credentials;



public class HomePageTest {	
	WebDriver driver;
	Properties prop;
	BasePage base;
	HomePage homePage;
	LoginPage loginPage;
	Credentials usercred;

	@BeforeMethod

	public void setUP() {

		base = new BasePage();
		prop = base.init_properties();
		String browsername = prop.getProperty("browser");
		driver = base.init_drver(browsername);
		driver.get(prop.getProperty("url"));
		homePage = new HomePage(driver);
		usercred=new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1, description = "Gettin Title from IspeakBetter", enabled=false)
	public void verifyHomePageTitle() {
		String title = homePage.getTitle();
		System.out.println("Getting Title: " + title);
		Assert.assertEquals(title, CONSTANTS.HOMEPAGETITLE);
	}

	@Test(priority = 2, description = "Gettin Url from IspeakBetter", enabled=false)
	public void verifyUrl() {
		String url = homePage.getUrl();
		System.out.println("Getting Url: " + url);
		Assert.assertEquals(url, CONSTANTS.ISPEAKURL);

	}

	@Test(priority=3, description="loginPage page", enabled=false)
	public void doLogin() {

		loginPage = homePage.clickLogin(usercred);
		String accountName = loginPage.getAccountName();
		Assert.assertTrue(accountName.contains(CONSTANTS.GREETINGNAME));

	}
	
	@DataProvider
	public Object[][] getPackagesData(){
		Object [][] data = {{"30", "2 weeks", "1 Class", "Business English", "24"},{"30", "4 weeks", "2 Classes", "Children English", "84"},
				{"60", "4 weeks", "3 Classes", "Conversational English", "216"},{"60", "8 weeks", "4 Classes", "IELTS Academic", "512"},
				{"60", "12 weeks", "5 Classes", "IELTS General Training", "960"}};
						
		return data;
	}
	

	@Test(priority = 5, dataProvider="getPackagesData", description = "fill out the form for program", enabled=true)
	public void fillOutProgram(String durationText, String subClassesText, String classWeekText, String programTest, String price1) throws InterruptedException {
		String price=homePage.BuyProgram(durationText, subClassesText, classWeekText, programTest, price1);
		
		Assert.assertTrue(CONSTANTS.PACKAGE_COST.contains(price));


	}
	
	@DataProvider
	public Object[][] usingInvalidCredantials(){
		Object [][] data = {{"cagla@gmail.com", "1234td"},
						{" ", "asdf34"},
						{"defne@gmail.com"," "},
						{"cagla", "cagla"},
						{" ", " "}};
		return data;
	}
	

		@Test(priority=4, dataProvider="usingInvalidCredantials", enabled=false)
		public void lgin_InvalidTestCase(String username, String pwd){
			usercred.setAppUsername(username);
			usercred.setAppPassword(pwd);
			homePage.clickLogin(usercred);
		
	}
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();

		// unit test - developer -
		// smoke - basic functionals of the page -build verifaction
		// regression - for the new compenent --
		// end to end(System )
//	}
//	
	}
}



