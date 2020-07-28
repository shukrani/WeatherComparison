package com.testvagrant.helper;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseWeb {
	static WebDriver driver;
	static String browserName, websiteURL;
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static WebDriver getDriver() {
		if (driver == null) {
			ConfigFileReader configFileReader = new ConfigFileReader();
			browserName = configFileReader.getPropertyValues("browser");
			websiteURL = configFileReader.getPropertyValues("url");
			if (browserName.contains("firefox") || browserName.contains("ff")) {
				driver = new FirefoxDriver();
			}
			if (browserName.contains("chrome") || browserName.contains("cr")) {
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(websiteURL);
		return driver;

	}

	public static void closeBrowser() {
		if (driver != null)
			driver.quit();
	}

	public static void setupEnvironment() {
		String osName = System.getProperty("os.name").toLowerCase();
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
		if (osName.contains("mac")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver");
			return;
		}
		if (osName.contains("windows")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			System.setProperty("ebdriver.gecko.driver", "src/test/resources/geckodriver.exe");
			return;
		}
		if (osName.contains("nux")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
			System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriverlinux");
		}

	}

	public static void takeScreenshot(String name) {
		File screenshotdirectory = new File("test-output/Screenshots");

		if (!screenshotdirectory.exists()) {
			screenshotdirectory.mkdir();
		}
		try {
			String fileName = screenshotdirectory + "/" + name + System.currentTimeMillis() + ".png";
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(source, new File(fileName));
			logger.log(Level.INFO, "Screenshot taken" + fileName);

		} catch (Exception e) {

			logger.log(Level.WARNING, "Exception while taking screenshot " + e.getMessage());
		}
	}

}
