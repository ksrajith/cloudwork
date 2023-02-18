package com.cloud.driver;

import com.cloud.config.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public final class Driver { //IS A relationship

    private Driver(){}

    public static void initDriver(String browser) {

        if(DriverManager.getDriver() == null) {
            WebDriver driver = DriverFactory.getDriver(browser);
            DriverManager.setDriver(driver);
            DriverManager.getDriver().manage().timeouts().implicitlyWait(ConfigFactory.getConfig().timeout(), TimeUnit.SECONDS);//presence of element in the dom
            DriverManager.getDriver().get(ConfigFactory.getConfig().browserUrl());
        }
    }

    public static void quitDriver(){
        if(DriverManager.getDriver() != null) {
            //DriverManager.getDriver().close();
            DriverManager.getDriver().quit();
            DriverManager.setDriver(null);
        }
    }
}
