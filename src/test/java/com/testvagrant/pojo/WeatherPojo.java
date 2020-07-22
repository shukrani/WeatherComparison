package com.testvagrant.pojo;

import org.testng.Assert;

public class WeatherPojo {
	Double temperature;
	Double humidityPercentage;

	public WeatherPojo(Double temperature, Double humidityPercentage) {
		super();
		this.temperature = temperature;
		this.humidityPercentage = humidityPercentage;
	}

	public Double getPercentage() {
		return temperature;
	}

	public void setPercentage(Double percentage) {
		this.temperature = percentage;
	}

	public Double getHumidityPercentage() {
		return humidityPercentage;
	}

	public void setHumidityPercentage(Double humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}

	public void compare(WeatherPojo obj1, WeatherPojo obj2) {
		Double tempDiff = obj1.temperature - obj2.temperature;
		Double humiditydiff = obj1.humidityPercentage - obj2.humidityPercentage;

		if (tempDiff < 2 && humiditydiff < 10) {
			Assert.assertTrue(true, "Both Weather objects are equals");
		} else {
			Assert.assertTrue(false, "Weather objects are not equal");
		}
	}

	@Override
	public String toString() {
		return "Temp=" + temperature + "humidity=" + humidityPercentage;

	}

}
