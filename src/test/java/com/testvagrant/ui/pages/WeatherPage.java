package com.testvagrant.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.testvagrant.ui.helper.WaitHelper;

public class WeatherPage {
	private static Logger log = Logger.getLogger(HomePage.class);
	public WebDriver driver;
	WaitHelper wait;
	@FindBy(id = "searchBox")
	WebElement textboxSearchCity;
	@FindBy(xpath = "//b[contains(text(),'Humidity')]")
	WebElement textHumidityPercentage;
	@FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
	WebElement textTemperature;

	public static WeatherPage getInstance(WebDriver driver) {
		WeatherPage weatherpage_instance = null;
		if (weatherpage_instance == null)
			weatherpage_instance = new WeatherPage(driver);

		return weatherpage_instance;
	}

	private WeatherPage(WebDriver webdriver) {
		driver = webdriver;
		wait = new WaitHelper();
		PageFactory.initElements(driver, this);
	}

	public void enterCityName(String cityName) {
		wait.waitForElement(driver, textboxSearchCity, 10);

		textboxSearchCity.click();
		textboxSearchCity.sendKeys(cityName + " ");
	}

	public void pinCityName(String cityname) {
		WebElement city_checkbox = driver.findElement(By.id(cityname));
		wait.waitForElement(driver, city_checkbox, 5);
		city_checkbox.click();
	}

	public void verifyPinCity(String cityName) {
		List<WebElement> citylist = driver.findElements(By.xpath("//div[contains(text(),'" + cityName + "')]"));
		wait.waitForElement(driver, citylist.get(0), 5);
		Assert.assertEquals(citylist.size(), 1);
		driver.findElement(By.xpath("//div[contains(text(),'" + cityName + "')]")).click();

	}

	public void storeWeatherInfo(String cityName) {
		wait.waitForElement(driver, textHumidityPercentage, 5);
		System.out.println(textHumidityPercentage.getText());
		System.out.println(textTemperature.getText());

	}

	public static void main(String args[]) {
		String humidity = "Humidity: 71%";
		String arr[] = humidity.split(": ");

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

	}

}
