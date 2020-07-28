package com.testvagrant.api.pages;

import java.io.IOException;

import org.testng.log4testng.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testvagrant.helper.WeatherDataMap;
import com.testvagrant.pojo.WeatherPojo;
import com.testvagrant.stepdefinitions.APISteps;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class WeatherAPIPage {
	static Response response;
	static ValidatableResponse json;
	static RequestSpecification request;
	private static final Logger logger = Logger.getLogger(APISteps.class);
	String ENDPOINTS = "http://api.openweathermap.org/data/2.5/weather";

	public Response getapi(String cityName) {
		request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request = request.given().queryParam("q", cityName).queryParam("appid", "7fe67bf08c80ded756e598d6f8fedaea")
				.queryParam("units", "metric");
		response = request.given().get(ENDPOINTS);
		return response;
	}

	public void verifyResponse() {
		response.then().assertThat().statusCode(200);
	}

	public void retriveWeatherObjectFromRsponse() throws JsonParseException, JsonMappingException, IOException {
		WeatherPojo pojo = new ObjectMapper().readValue(response.asByteArray(), WeatherPojo.class);
		WeatherDataMap.add(pojo.getCityName() + "api", pojo);

	}

}
