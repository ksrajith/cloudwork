package com.cloud.pageObjects;

import com.cloud.config.ConfigFactory;
import com.cloud.driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BasePage {

    final static Logger logger = Logger.getLogger(BasePage.class);
  //  public static WebDriver driver;
    public static final int TIME_OUT = 4;
    public static final int SLEEP_TIME_MILL=4000;

//    public static void browser()  {
//        try{
//            PropertyReader pr = new PropertyReader();
//            driver = BrowserSetup.setBrowser(pr.getPropValues());
//        }catch (Exception ex){
//            logger.error("Browser not functioning :", ex);
//        }
//
//    }

    public void browserSize(String type){
        switch (type){
            case "WEB":
                DriverManager.getDriver().manage().window().maximize();
                break;
            case "MOBILE":
                DriverManager.getDriver().manage().window().setSize(new Dimension(400,725));
                break;
        }
    }

    public enum BY_TYPE {
        BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME, BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME
    };

    public static By getLocator(String locator, BY_TYPE type) {
        switch (type) {
            case BY_XPATH:
                return By.xpath(locator);
            case BY_LINKTEXT:
                return By.linkText(locator);
            case BY_ID:
                return By.id(locator);
            case BY_CSSSELECTOR:
                return By.cssSelector(locator);
            case BY_CLASSNAME:
                return By.className(locator);
            case BY_NAME:
                return By.name(locator);
            case BY_PARTIALLINKTEXT:
                return By.partialLinkText(locator);
            case BY_TAGNAME:
                return By.tagName(locator);

        }
        throw new IllegalArgumentException(
                "Invalid By Type, Please provide correct locator type");

    }

    public static void click(By locator, int ...seconds) throws Exception{
        if(logger.isDebugEnabled()){
            logger.debug("Trying to clicks on : " + locator);
        }
        int time = (seconds.length==0) ? TIME_OUT : seconds[0];
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(time));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public static WebElement waitUntilElementToBeClickable(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigFactory.getConfig().timeout()));
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static WebElement waitUntilElementToBePresence(By by) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigFactory.getConfig().timeout()));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void type(By locator, String value){

        try{
            if(logger.isDebugEnabled()){
                logger.debug("Trying to type "+value+" on field: " + locator);
            }

            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigFactory.getConfig().timeout()));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            setTimeOut(ConfigFactory.getConfig().timeout());
            element.click();
            element.clear();
            element.sendKeys(value);
        }catch(NoSuchElementException ex){
            logger.error("type text NoSuchElementException field", ex);
        } catch (Exception e){
            logger.error("type text Exception field", e);
        }

    }

    public static String getText(By locator){
        try {
            if(logger.isDebugEnabled()){
                logger.debug("Trying to get text " + locator);
            }

            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIME_OUT));
            wait.until(ExpectedConditions.attributeToBeNotEmpty(DriverManager.getDriver().findElement(locator),"value"));
            setTimeOut(TIME_OUT);
            return DriverManager.getDriver().findElement(locator).getAttribute("value");
        }catch (Exception ex){
            logger.error("GetText failed ", ex);
            return null;
        }
    }

    public static void setTimeOut(long timeoutInSeconds) {
        DriverManager.getDriver().manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        DriverManager.getDriver().manage().timeouts().setScriptTimeout(timeoutInSeconds, TimeUnit.SECONDS);
    }


    /**
     * This function will take screenshot
     * @throws Exception
     */
    public static boolean takeSnapShot(String fileName) throws Exception{

        try {
            //Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot =((TakesScreenshot)DriverManager.getDriver());
            //Call getScreenshotAs method to create image file
            File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
            //Move image file to new destination
            File DestFile=new File("src/main/resources/screen/"+fileName+ new SimpleDateFormat("yyyyMMddHHmm'.png'").format(new Date()));
            //Copy file at destination
            FileUtils.copyFile(SrcFile, DestFile);
            return true;
        }catch (Exception ex){
            ex.getStackTrace();
            return false;
        }
    }

    public boolean isElementExists(By locator){
        return  DriverManager.getDriver().findElements(locator).size() != 0;
    }

    public int getElementCount(By locator){
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(ConfigFactory.getConfig().timeout()));
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return elements.size();
    }

}
