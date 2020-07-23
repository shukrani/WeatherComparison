package com.testvagrant.stepdefinitions;

import org.testng.Assert;

import com.testvagrant.helper.CsvFileUtils;
import com.testvagrant.pojo.WeatherPojo;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CommonSteps {

	boolean status;

	// static CsvFileUtils apiResultCsv = new
	// CsvFileUtils("src/test/resources/apiWeatherlist.txt");
	// static CsvFileUtils webResultCsv = new
	// CsvFileUtils("src/test/resources/webWeatherlist.txt");

	@When("^user comparing weather data received from UI and API on the basis of (.+) and (.+) for the (.+)$")
	public void user_comparing_weather_data_received_from_ui_and_api_on_the_basis_of_and_for_the(String tempvariance,
			String humidityvariance, String cityname) throws Throwable {
		WeatherPojo uiWeatherObj = CsvFileUtils.readWeatherObject(CsvFileUtils.getCSVReader(Constants.WEB_RESULT_FILE));
		WeatherPojo apiWeatherObj = CsvFileUtils
				.readWeatherObject(CsvFileUtils.getCSVReader(Constants.API_RESEULT_FILE)); // apiResultCsv.readWeatherObject();
		System.out.println(uiWeatherObj.toString());
		System.out.println(apiWeatherObj.toString());
		status = isEqual(apiWeatherObj, uiWeatherObj, Double.parseDouble(tempvariance),
				Double.parseDouble(humidityvariance));// apiWeatherObj.equals(uiWeatherObj);
		System.out.println(status);
	}

	@Then("^user is able to see the correct results$")
	public void user_is_able_to_see_the_correct_results() throws Throwable {
		if (status) {
			Assert.assertTrue(status, "Both Weather Objects are Equal");
		} else {
			Assert.assertTrue(status, "Both Weather Objects are not Equal");
		}
	}

	private boolean isEqual(WeatherPojo pojo1, WeatherPojo pojo2, double tempvariance, double humidityvariance) {
		if (pojo1 == null && pojo2 == null) {
			return true;
		}

		if (pojo1 == null || pojo2 == null) {
			return false;
		}

		double tempDiff = Math.abs(pojo1.getTemperature() - pojo2.getTemperature());
		double humiditydiff = Math.abs(pojo1.getHumidityPercentage() - pojo2.getHumidityPercentage());

		return tempDiff < tempvariance && humiditydiff < humidityvariance;
	}

}
