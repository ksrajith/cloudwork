package com.cloud.pageObjects;

import org.openqa.selenium.By;

import java.util.concurrent.ExecutorService;

public class LandingPage extends BasePage {

    private final String logo = "//img[@alt='Cloudmore']";
    private String menuItem = "//ul[@id='mega-menu-primary_navigation']//a[@href='https://web.cloudmore.com/menuItem/']";
    private final String menuBtn = "navbar-toggler-icon";
    private final String searchIcon = "div#navbarSupportedContent>div:nth-of-type(2)>button>img";
    private final String cookieDecline = "hs-eu-decline-button";
    private final String searchField = "input-search";
    private final String serchBtn = "div#app>header>div>div>div>form>button>img";
    private final String searchResults = "//div[@class='text-area']/article";

    /**
     *  only chceck is element clickable
     * @param item
     * @param value
     * @return
     * @throws Exception
     */
    public boolean elementClickable(String item, String value) throws Exception {
        switch (item) {
            case "MENUITEM":
                By byElement = getLocator(menuItem.replace("menuItem", value), BY_TYPE.BY_XPATH);
                return waitUntilElementToBeClickable(byElement) != null;
            case "MENUITEMMOB":
                clickOnItem("MENUBTN");
                byElement = getLocator(menuItem.replace("menuItem", value), BY_TYPE.BY_XPATH);
                return waitUntilElementToBeClickable(byElement) != null;
        }
        return false;
    }

    /**
     * only chceck is element presence
     * @param item
     * @return
     */
    public boolean elementIsPresence(String item) {
        switch (item) {
            case "LOGO":
                By byElement = getLocator(logo, BY_TYPE.BY_XPATH);
                return waitUntilElementToBePresence(byElement) != null;
        }
        return false;
    }

    /**
     * click on element
     * @param item
     * @return
     */
    public boolean clickOnItem(String item) {
        try{
            By byElement;
            switch (item) {
                case "SEARCH":
                    byElement = getLocator(searchIcon, BY_TYPE.BY_CSSSELECTOR);
                    if (isElementExists(getLocator(cookieDecline, BY_TYPE.BY_ID))) {
                        click(getLocator(cookieDecline, BY_TYPE.BY_ID), 0);
                    }
                    click(byElement, 0);
                    break;
                case "SEARCHBTN":
                    byElement = getLocator(serchBtn, BY_TYPE.BY_CSSSELECTOR);
                    click(byElement, 0);
                    break;
                case "MENUBTN":
                    byElement = getLocator(menuBtn, BY_TYPE.BY_CLASSNAME);
                    click(byElement, 0);
                    break;
            }
            return true;
        }catch (Exception ex){
            ex.getStackTrace();
           return false;
        }

    }

    /**
     * clear the text and type
     * @param item
     * @param value
     */
    public void typeSearch(String item, String value) {
        switch (item) {
            case "SEARCH":
                By byElement = getLocator(searchField, BY_TYPE.BY_CLASSNAME);
                type(byElement, value);
                break;
        }
    }

    /**
     * get element count
     * @param item
     * @return
     */
    public int getElementsCount(String item) {
        switch (item) {
            case "SEARCH":
                By byElement = getLocator(searchResults, BY_TYPE.BY_XPATH);
                return getElementCount(byElement);
        }
        return 0;
    }
}
