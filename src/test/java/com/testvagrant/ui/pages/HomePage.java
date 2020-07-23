package com.testvagrant.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import com.testvagrant.helper.WaitHelper;

public class HomePage {
	private static Logger log = Logger.getLogger(HomePage.class);
	public WebDriver driver;
	WaitHelper wait;
	private static HomePage Homepage_instance = null;

	@FindBy(id = "h_sub_menu")
	public WebElement morebutton;

	@FindBy(xpath = "//a[contains(text(),'WEATHER')]")
	public WebElement weatherButton;

	public static HomePage getInstance(WebDriver driver) {

		if (Homepage_instance == null)
			Homepage_instance = new HomePage(driver);

		return Homepage_instance;
	}

	private HomePage(WebDriver webdriver) {
		driver = webdriver;
		wait = new WaitHelper();
		PageFactory.initElements(driver, this);
	}

	public void clickOnMoreButton() {
		wait.waitForElement(driver, morebutton, 10);
		morebutton.click();
	}

	public void clickOnWeatherButton() {
		weatherButton.click();
	}
}
