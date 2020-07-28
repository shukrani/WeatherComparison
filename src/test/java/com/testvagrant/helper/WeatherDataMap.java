package com.testvagrant.helper;

import java.util.HashMap;

import com.testvagrant.pojo.WeatherPojo;

public class WeatherDataMap {
	static HashMap<String, WeatherPojo> weathermap = new HashMap<>();
	private static WeatherDataMap instance = new WeatherDataMap();

	public static WeatherDataMap getInstance() {
		return instance;
	}

	public static WeatherPojo getValue(String key) {
		return weathermap.get(key.toLowerCase());
	}

	public static void add(String cityName, WeatherPojo weatherPojo) {
		weathermap.put(cityName.toLowerCase(), weatherPojo);
	}
}
