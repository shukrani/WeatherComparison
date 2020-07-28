package com.testvagrant.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherPojo implements Serializable {

	private static final double TEMP_VARIANCE = 2d;
	private static final double HUMIDITY_VARIANCE = 10d;

	private static final long serialVersionUID = -299482035708790407L;

//	@JsonProperty("main.temp")
//	double temperature;
//
//	@JsonProperty("main.humidity")
//	double humidityPercentage;

	@JsonProperty("main")
	private WeatherData data;

	@JsonProperty("name")
	String cityName;

	public WeatherPojo() {

	}

	public WeatherPojo(double temperature, double humidityPercentage, String cityName) {
		super();
		data = new WeatherData();

		data.setTemperature(temperature);
		data.setHumidityPercentage(humidityPercentage);
		this.cityName = cityName;
	}

	public double getTemperature() {
		return this.data.getTemperature();
	}

	public void setTemperature(Double temperature) {
		data.setTemperature(temperature);
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getHumidityPercentage() {
		return this.data.getHumidityPercentage();
	}

	public void setHumidityPercentage(Double humidityPercentage) {
		this.data.setHumidityPercentage(humidityPercentage);
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof WeatherPojo)) {
			return false;
		}
		WeatherPojo o = (WeatherPojo) obj;
		double tempDiff = Math.abs(this.getTemperature() - o.getTemperature());
		double humiditydiff = Math.abs(this.getHumidityPercentage() - o.getHumidityPercentage());

		return tempDiff < TEMP_VARIANCE && humiditydiff < HUMIDITY_VARIANCE && this.cityName.equals(o);
	}

	public String[] toArray() {
		return new String[] { cityName, String.valueOf(getTemperature()), String.valueOf(getHumidityPercentage()) };
	}

	public static WeatherPojo fromArray(String[] arr) {
		return new WeatherPojo(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), arr[0]);
	}

	@Override
	public String toString() {
		return "City=" + cityName + "Temp=" + getTemperature() + "humidity=" + getHumidityPercentage();

	}

	public boolean isEquals(WeatherPojo pojo, double tempvariance, double humidityvariance) {

		if (pojo == null) {
			return false;
		}

		double tempDiff = Math.abs(this.getTemperature() - pojo.getTemperature());
		double humiditydiff = Math.abs(this.getHumidityPercentage() - pojo.getHumidityPercentage());

		return tempDiff < tempvariance && humiditydiff < humidityvariance;
	}

}

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherData {
	@JsonProperty("temp")
	private double temperature;

	@JsonProperty("humidity")
	private double humidityPercentage;

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getHumidityPercentage() {
		return humidityPercentage;
	}

	public void setHumidityPercentage(double humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}

}
