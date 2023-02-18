package com.cloud.steps.web;

import com.cloud.driver.DriverManager;
import com.cloud.pageObjects.BasePage;
import com.cloud.pageObjects.LandingPage;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.util.List;

public class LandingPageTestSteps {

    @Given("user direct to the web page as {string} user")
    public void user_direct_to_the_web_page_as_user(String type) {
        BasePage pge = new BasePage();
        pge.browserSize(type);
    }

    @When ("able to see the log in the page")
    public void able_to_see_the_log_in_the_page() {
        LandingPage lp = new LandingPage();
        Assert.assertTrue(lp.elementIsPresence("LOGO"), "Error on logo verification");
    }

    @Then("able to verify menu item <menuItem>")
    public void ableToVerifyMenuItemMenuItem(List<String> menuItems) throws Exception {
        for (String item: menuItems) {
            if (item.equals("integrations")){
                item = "home/"+item;
            }
            LandingPage lp = new LandingPage();
            Assert.assertTrue(lp.elementClickable("MENUITEM", item), "Error in menu item clickable");
        }
    }

    @When("user clicks on search button")
    public void user_clicks_on_search_button() throws Exception {
        LandingPage lp = new LandingPage();
        lp.clickOnItem("SEARCH");
    }

    @Then("user able to search text as {string}")
    public void user_able_to_search_text_as(String string) {
        LandingPage lp = new LandingPage();
        lp.typeSearch("SEARCH", string);
    }

    @Then("user clicks on search")
    public void user_clicks_on_search() {
        LandingPage lp = new LandingPage();
        Assert.assertTrue(lp.clickOnItem("SEARCHBTN"), "Error search click");
    }

    @Then("take screenshot of page")
    public void take_screenshot_of_page() throws Exception {
        LandingPage lp = new LandingPage();
        Assert.assertTrue(lp.takeSnapShot("azureScreen"), "Error in taking screenshot");
    }

    @Then("user validate the results count")
    public void user_validate_the_results_count() {
        LandingPage lp = new LandingPage();
        int count = lp.getElementsCount("SEARCH");
        Assert.assertTrue(count>3, "Error in Search Results are more than three");
    }

    @When("able to click on menu button")
    public void able_to_click_on_menu_button() {
        LandingPage lp = new LandingPage();
        Assert.assertTrue(lp.clickOnItem("MENUBTN"), "Error in click on menu button");
    }

}
