package com.cloud.steps.hooks;

import com.cloud.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class GeneralHooks {
    @After
    public void afterTestStep(Scenario scenario) {
        if ((scenario.isFailed()) && !scenario.getSourceTagNames().contains("@api")) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
