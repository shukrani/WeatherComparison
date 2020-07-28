package com.testvagrant.stepdefinitions;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.testvagrant.api.pages.WeatherAPIPage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class APISteps {
	WeatherAPIPage weatherapipage = new WeatherAPIPage();

	@When("a user retrieves the weather information by {string}")
	public void a_user_retrieves_the_weather_information_by(String cityName) {
		weatherapipage.getapi(cityName);
	}

	@Then("the status code is {int}")
	public void the_status_code_is(Integer successCode) {
		weatherapipage.verifyResponse();
	}

	@Then("response return weather object")
	public void response_return_weather_object() throws JsonParseException, JsonMappingException, IOException {
		weatherapipage.retriveWeatherObjectFromRsponse();
	}

}
