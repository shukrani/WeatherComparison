package com.testvagrant.stepdefinitions;

import com.opencsv.CSVWriter;
import com.testvagrant.helper.CsvFileUtils;
import com.testvagrant.pojo.WeatherPojo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APISteps {
	static Response response;
	static ValidatableResponse json;
	static RequestSpecification request;

	String ENDPOINTS = "http://api.openweathermap.org/data/2.5/weather";

	@When("a user retrieves the weather information by {string}")
	public void a_user_retrieves_the_weather_information_by(String cityName) {
		request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request = request.given().queryParam("q", cityName).queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea")
				.queryParam("units", "metric");
		response = request.given().get(ENDPOINTS);

	}

	@Then("the status code is {int}")
	public void the_status_code_is(Integer successCode) {
		response.then().assertThat().statusCode(200);

	}

	@Then("response return weather object")
	public void response_return_weather_object() {
		WeatherPojo pojo = new WeatherPojo(Double.parseDouble(response.path("main.temp").toString()),
				Double.parseDouble(response.path("main.humidity").toString()), response.path("name").toString());
		System.out.println(pojo.toString());
		CSVWriter writer = CsvFileUtils.getCSVWriter(Constants.API_RESEULT_FILE);
		CsvFileUtils.writeWeatherObject(writer, pojo);
		// apiResultCsv.writeWeatherObject(pojo);
	}

}
