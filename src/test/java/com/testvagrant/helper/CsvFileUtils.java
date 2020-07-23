package com.testvagrant.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.testvagrant.pojo.WeatherPojo;

public class CsvFileUtils {

	private static final Map<String, CSVWriter> WRITER_MAP = new HashMap<>();
	private static final Map<String, CSVReader> READER_MAP = new HashMap<>();

	private CsvFileUtils() {

	}

	public static CSVWriter getCSVWriter(final String file) {
		return WRITER_MAP.computeIfAbsent(file, (key) -> {
			try {
				return new CSVWriter(new FileWriter(file));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	public static CSVReader getCSVReader(final String file) {
		return READER_MAP.computeIfAbsent(file, (key) -> {
			try {
				return new CSVReader(new FileReader(new File(file)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			}
		});
	}

	public static void writeWeatherObject(CSVWriter writer, WeatherPojo pojo) {
		try {
			writer.writeNext(pojo.toArray(), false);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WeatherPojo readWeatherObject(CSVReader reader) {
		WeatherPojo pojo1 = null;
		try {
			pojo1 = WeatherPojo.fromArray(reader.readNext());
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pojo1;
	}

	public static void close() {
		WRITER_MAP.values().iterator().forEachRemaining(writer -> {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		READER_MAP.values().iterator().forEachRemaining(reader -> {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		WRITER_MAP.clear();
		READER_MAP.clear();
	}

}
