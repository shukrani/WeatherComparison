package com.testvagrant.ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.testvagrant.helper.WaitHelper;
import com.testvagrant.pojo.WeatherPojo;

public class WeatherPage {
	String humidity = "";
	String temp = "";
	private static Logger log = Logger.getLogger(HomePage.class);
	public WebDriver driver;
	WaitHelper wait;
	@FindBy(id = "searchBox")
	WebElement textboxSearchCity;
	@FindBy(xpath = "//b[contains(text(),'Humidity')]")
	WebElement textHumidityPercentage;
	@FindBy(xpath = "//b[contains(text(),'Temp in Degrees')]")
	WebElement textTemperature;
	@FindBy(className = "tempRedText")
	List<WebElement> temptextlist;

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

	public void verifyWeatherPage() {
		wait.waitForElement(driver, textboxSearchCity, 10);
		wait.waitForElement(driver, temptextlist.get(0), 10);
	}

	public void enterCityName(String cityName) {
		textboxSearchCity.click();
		textboxSearchCity.sendKeys(cityName + " ");
	}

	public void pinCityName(String cityname) {
		WebElement city_checkbox = driver.findElement(By.id(cityname));
		wait.waitForElement(driver, city_checkbox, 10);
		city_checkbox.click();
	}

	public void verifyPinCity(String cityName) {
		List<WebElement> citylist = driver.findElements(By.xpath("//div[contains(text(),'" + cityName + "')]"));
		Assert.assertEquals(citylist.size(), 1);
		driver.findElement(By.xpath("//div[contains(text(),'" + cityName + "')]")).click();

	}

	public WeatherPojo convertoWeatherPojo(String cityName) {
		wait.waitForElement(driver, textHumidityPercentage, 5);
		humidity = textHumidityPercentage.getText();
		temp = textTemperature.getText();
		temp = temp.replaceAll("[^\\d]", "");
		humidity = humidity.replaceAll("[^\\d]", "");
		WeatherPojo pojo = new WeatherPojo(Double.parseDouble(temp), Double.parseDouble(humidity), cityName);
		return pojo;

	}

}
