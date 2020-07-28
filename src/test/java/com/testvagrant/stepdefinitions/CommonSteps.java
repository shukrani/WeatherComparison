package com.testvagrant.stepdefinitions;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.Assert;

import com.testvagrant.helper.WeatherDataMap;
import com.testvagrant.pojo.WeatherPojo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	boolean status;

	@When("^user comparing weather data received from UI and API on the basis of (.+) and (.+) for the (.+)$")
	public void user_comparing_weather_data_received_from_ui_and_api_on_the_basis_of_and_for_the(String tempvariance,
			String humidityvariance, String cityname) throws Throwable {

		WeatherPojo uiweatherObj = WeatherDataMap.getValue(cityname + "ui");
		WeatherPojo apiweatherObj = WeatherDataMap.getValue(cityname + "api");
		logger.log(Level.INFO, "Weather object captured from ui=" + uiweatherObj.toString());
		logger.log(Level.INFO, "Weather object captured from api=" + apiweatherObj.toString());

		status = uiweatherObj.isEquals(apiweatherObj, Double.parseDouble(tempvariance),
				Double.parseDouble(humidityvariance));
	}

	@Then("^user is able to see the correct results$")
	public void user_is_able_to_see_the_correct_results() throws Throwable {
		if (status) {
			Assert.assertTrue(status, "Both Weather Objects are Equal");
		} else {
			Assert.assertTrue(status, "Both Weather Objects are not Equal");
		}
	}

}
