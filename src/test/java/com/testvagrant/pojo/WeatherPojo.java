package com.testvagrant.pojo;

import java.io.Serializable;

public class WeatherPojo implements Serializable {

	private static final double TEMP_VARIANCE = 2d;
	private static final double HUMIDITY_VARIANCE = 10d;

	private static final long serialVersionUID = -299482035708790407L;
	double temperature;
	double humidityPercentage;
	String cityName;

	public WeatherPojo(double temperature, double humidityPercentage, String cityName) {
		super();
		this.temperature = temperature;
		this.humidityPercentage = humidityPercentage;
		this.cityName = cityName;
	}

	public double getPercentage() {
		return temperature;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setPercentage(Double percentage) {
		this.temperature = percentage;
	}

	public double getHumidityPercentage() {
		return humidityPercentage;
	}

	public void setHumidityPercentage(Double humidityPercentage) {
		this.humidityPercentage = humidityPercentage;
	}

	public boolean equals(Object obj) {
		if (!(obj instanceof WeatherPojo)) {
			return false;
		}
		WeatherPojo o = (WeatherPojo) obj;
		double tempDiff = Math.abs(this.temperature - o.temperature);
		double humiditydiff = Math.abs(this.humidityPercentage - o.humidityPercentage);

		return tempDiff < TEMP_VARIANCE && humiditydiff < HUMIDITY_VARIANCE && this.cityName.equals(o);
	}

	public String[] toArray() {
		return new String[] { cityName, String.valueOf(temperature), String.valueOf(humidityPercentage) };
	}

	public static WeatherPojo fromArray(String[] arr) {
		return new WeatherPojo(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), arr[0]);
	}

	@Override
	public String toString() {
		return "City" + cityName + "Temp=" + temperature + "humidity=" + humidityPercentage;

	}

}
