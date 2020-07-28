package com.testvagrant.helper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.log4testng.Logger;

public class ConfigFileReader {
	private static final Logger logger = Logger.getLogger(ConfigFileReader.class);
	static Properties properties;
	static FileReader reader;

	public ConfigFileReader() {
		properties = new Properties();
		try {
			reader = new FileReader("src/test/resources/configuration.properties");
			properties.load(reader);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	public String getPropertyValues(String keyName) {
		return properties.getProperty(keyName).toLowerCase();
	}

}
