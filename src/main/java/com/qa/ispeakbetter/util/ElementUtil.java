package com.qa.ispeakbetter.util;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.ispeakbetter.base.BasePage;

public class ElementUtil extends BasePage{
Select select;
	
	WebDriver driver;
	JavaScriptUtil jsUtil;
	Properties prop;
    //Constructor
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}
	/**
	 * 
	 * @return
	 */
	public String doGetPageTitle() {
		try {
		return driver.getTitle();
		}
		catch (Exception e) {
			System.out.println("some exception got occured while getting the title...");
		}
		return null;
	}
	public String doGetPageUrl() {
		try {
		return driver.getCurrentUrl();
		}
		catch (Exception e) {
			System.out.println("some exception got occured while getting URL...");
		}
		return null;
	}
	/**
	 * @author bobit
	 * this method is used to create the weblement on the basis of By locator
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
		 // we cant locate title with presenceOfElement
		WebElement element = null;
		try {
		element = driver.findElement(locator);
		if (highlightElement) {
			jsUtil.flash(element, driver);
			
		}
		}
		catch (Exception e) {
			System.out.println("some exception got occured while creating the web element...");
		}
		return element;
	}
	
	
	
	
public List<WebElement> getElements(By locator) {
		
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator)); // we cant locate title with presenceOfElement
		List<WebElement >elements = null;
		try {
		elements = driver.findElements(locator);
		}
		catch (Exception e) {
			System.out.println("some exception got occured while creating the web element...");
		}
		return elements;
	}
	/**
	 * this method is used to click the weblement on the basis of By locator
	 * @param locator
	 */
	public void doClick(By locator) {
		WebDriverWait wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
		try {
		   getElement(locator).click();
		}
		catch (Exception e) {
			System.out.println("some exception got occured while clicking the web element...");
		}
		
	}
	/**
	 * this method is used to send  value in a field
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		
		WebElement element = getElement(locator);
		try {
		
		element.clear();
		element.sendKeys(value);
		}
		catch (Exception e) {
			System.out.println("some exception got occured while entering values in a field...");
		}
	}
	/**
	 * isDisplayed
	 * @param locator
	 * @return
	 */
	public boolean doIsDisplayed(By locator) {
		try {
		return getElement(locator).isDisplayed();
		}
		catch (Exception e) {
			System.out.println("some exception got occured...");
		}
		return false;
	}
	/**
	 * isEnabled
	 * @param locator
	 * @return
	 */
	public boolean doIsEnabled(By locator) {
		try {
		return getElement(locator).isEnabled();
		}
		catch (Exception e) {
			System.out.println("some exception got occured...");
		}
		return false;
	}
	/**
	 * isSeelcted
	 * @param locator
	 * @return
	 */
	public boolean doIsSelected(By locator) {
		try {
		return getElement(locator).isSelected();
		}
		catch (Exception e) {
			System.out.println("some exception got occured...");
		}
		return false;
	}
	/**
	 * Gettext
	 * @param locator
	 * @return
	 */
	public String doGetText(By locator) {
		try {
		return getElement(locator).getText();
		}
		catch (Exception e) {
			System.out.println("some exception got occured while getting text...");
		}
		return null;

	}
	public void handleDropDownMenuByVisibleText(By locator, String text){
		select=new Select(driver.findElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void handleDropDownMenuByVisibleIndex(By locator, Integer value){
		select=new Select(driver.findElement(locator));
		select.selectByIndex(value);
	}
	
	public void handleDropDownMenuByValue(By locator, String element){
		select=new Select(driver.findElement(locator));
		select.selectByValue(element);;
	}
	public Select selectElement(By locator) {
		Select select=new Select(getElement(locator));
		return select;
	}
	
	
}
