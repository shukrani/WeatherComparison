
package com.testvagrant.testrunner;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.testvagrant.helper.BaseWeb;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features" }, glue = {
		"com/testvagrant/stepdefinitions" }, tags = "@weatherSanity", plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"json:target/cucumber-report.json" })

public class TestRunner extends AbstractTestNGCucumberTests {

	@BeforeSuite
	public void setupEnvironment() {
		BaseWeb.setupEnvironment();

	}

	@BeforeTest
	public void geturl() {
		BaseWeb.getDriver();
	}

	@AfterTest
	public void tearDown() {
		BaseWeb.closeBrowser();
	}

	@AfterMethod
	public void cleanup(ITestResult result) {
		BaseWeb.takeScreenshot(result.getName());

	}

	@AfterSuite
	public void cleanResources() {

	}

}
