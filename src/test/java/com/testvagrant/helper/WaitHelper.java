package com.testvagrant.helper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class WaitHelper {
	Logger logger = Logger.getLogger(WaitHelper.class);
	static WebDriverWait wait;

	public void waitForElement(WebDriver driver, WebElement element, long timeOutInSeconds) {
		logger.debug("Waitting for elelment");
		wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		logger.debug("Element is visible now");
	}

	public void pause(Integer milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void waitForSeconds(WebDriver driver, long timeOutInSeconds) {
		logger.debug("Waitting for elelment");
		wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
			wait.wait(timeOutInSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Element is visible now");
	}
}
