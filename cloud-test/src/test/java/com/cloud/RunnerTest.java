package com.cloud;

import com.cloud.driver.Driver;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@CucumberOptions(publish = true,
        plugin = {"pretty", "json:reports/cucumber.json"
                , "html:reports/cucumber-report.html"
                , "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"src/test/features"},
        glue = {"com.cloud.base", "com.cloud.steps.web", "com.cloud.steps.api", "com.cloud.steps.hooks"},
        tags = "@assignment")

public class RunnerTest extends AbstractTestNGCucumberTests {

    public static String browserView = "";

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional String browser) {
        browserView = browser;
        Driver.initDriver(browser);
        // Enabled reassured logs
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @AfterClass
    public void quite() {
        Driver.quitDriver();
    }

}