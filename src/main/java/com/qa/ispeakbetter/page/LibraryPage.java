package com.qa.ispeakbetter.page;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.ispeakbetter.util.ElementUtil;


public class LibraryPage {
	WebDriver driver;
	ElementUtil elementUtil;

	By headerText = By.xpath("//h3[@class='panel-title']");
//	By chatFrame= By.id("siqiframe");
	By chatWindowBtn = By.id("zsiq_agtpic");
	By texthere = By.cssSelector("#msgarea");
	By clickToSendText = By.id("sqico-send");
	By waitingAreaText = By.id("info_banr"); // Please wait while our support representative attends your chat.
	By iSpeakLogo = By.xpath("//img[@id='logo' and @src=\"img/logo2.png\"]");

	public LibraryPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public String getHeaderText() { // loginpagetest te cagirildi
		return elementUtil.getElement(headerText).getText();
	}

	public List<String> getHeadBarList() {
		List<WebElement> headerBarElements = elementUtil
				.getElements(By.xpath("//ul[@class='nav navbar-nav navbar-right']//li"));
		ArrayList<String> headerBar = new ArrayList<String>();
		for (WebElement elements : headerBarElements) {
			headerBar.add(elements.getText());
		}

		List<String> header = headerBar; // just tried to store as a List
		System.out.println(header + "  printed out header bar links");
		return header;

	}

	public String chatWithRobot() {

		elementUtil.doClick(chatWindowBtn);
		driver.switchTo().frame("siqiframe");
		elementUtil.doSendKeys(texthere, "hi there how are you?");
		elementUtil.doClick(clickToSendText);
		String text= elementUtil.doGetText(waitingAreaText);
		return text;
	}

	public boolean checkLogoAndgoToLogin() {
		boolean logoEnable = elementUtil.getElement(iSpeakLogo).isEnabled();
		elementUtil.doClick(iSpeakLogo);
		return logoEnable;
	}

}
