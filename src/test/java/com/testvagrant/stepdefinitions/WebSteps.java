package com.testvagrant.stepdefinitions;

import org.openqa.selenium.WebDriver;

import com.testvagrant.ui.helper.BaseWeb;
import com.testvagrant.ui.pages.HomePage;
import com.testvagrant.ui.pages.WeatherPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WebSteps {
	private HomePage homePage;
	private WeatherPage weatherPage;
	public static WebDriver driver = BaseWeb.getDriver();

	public WebSteps() {
		homePage = HomePage.getInstance(driver);
		weatherPage = WeatherPage.getInstance(driver);
	}

	@When("^I select weather section$")
	public void i_select_weather_section() throws Throwable {
		homePage.clickOnMoreButton();
		homePage.clickOnWeatherButton();

	}

	@Then("^weather section gets open$")
	public void weather_section_gets_open() throws Throwable {

	}

	@When("I  pin {string} city")
	public void i_pin_city(String cityname) {
		weatherPage.enterCityName(cityname);
		weatherPage.pinCityName(cityname);

	}

	@Then("selected {string} display on the map")
	public void selected_display_on_the_map(String cityname) {
		weatherPage.verifyPinCity(cityname);
	}

	@And("I can check the detailed weather information for the {string}")
	public void i_can_check_the_detailed_weather_information_for_the(String cityname) throws Throwable {
		weatherPage.storeWeatherInfo(cityname);
	}

}