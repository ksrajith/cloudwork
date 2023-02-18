package com.cloud.steps.api;

import com.cloud.data.DataManager;
import com.cloud.pojo.ServiceDataObj;
import com.cloud.services.ApiService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.Assert;

public class ApiTestSteps {

    DataManager dataManager = new DataManager();
    ApiService service = new ApiService();
    ServiceDataObj dataObj;
    Response response;

    String testCaseName;

    @Given("user invoke {string} with test case {string}")
    public void userInvokeWithTestCase(String api, String testCase) {
        testCaseName = testCase;
        dataObj =  dataManager.getServiceData(testCase);
        switch (api){
            case "create":
                response = service.setCreateUserRequestObject(dataObj.getRequest(), dataObj.getUrlSuffix());
                break;
            case "read":
                response = service.getUserRequestObject(dataObj.getUrlSuffix());
                break;
            case "update":
                response = service.setUpdateRequestObject(dataObj.getRequest(), dataObj.getUrlSuffix());
                break;
            case "delete":
                response = service.deleteUserRequestObject(dataObj.getUrlSuffix());
                break;
        }

    }


    @When("user should receive the response")
    public void user_should_receive_the_response() {
        Assert.assertNotNull(response, "Error in retrieve user create response");
    }
    @Then("verify response code")
    public void verify_response_code() {
        Assert.assertEquals(response.getStatusCode(), dataObj.getResponseCode(), "Error in response code validation");

    }
    @Then("Verify response body")
    public void verify_response_body() throws JSONException {
        if(!testCaseName.equals("deleteInvalidUser")){
            JSONAssert.assertEquals("Error in json response validation", dataObj.getResponse(), response.getBody().prettyPrint(),
                    new CustomComparator(JSONCompareMode.LENIENT,
                            new Customization("id", (o1, o2) -> true)
                    ));
        }
    }

}
