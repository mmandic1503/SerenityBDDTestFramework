package poc.stepdefinition;

import com.google.common.collect.ImmutableList;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import poc.services.models.ApiConnector;
import poc.services.models.ResponseBody;
import poc.util.NumberUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static poc.util.SessionEnums.API_RESPONSE;
import static poc.util.SessionEnums.REQUEST_SPECIFICATION;

public class FactorialTestSteps {

    @Given("I provide number {string}")
    public void iProvideNumber(String number) {
        ApiConnector apiConnector = new ApiConnector();
        RequestSpecification apiRequestSpecification = apiConnector.requestSpecification(number);
        Serenity.setSessionVariable(REQUEST_SPECIFICATION).to(apiRequestSpecification);
    }

    @When("I try to calculate")
    public void iTryToCalculate() {
        ApiConnector apiConnector = new ApiConnector();
        RequestSpecification apiRequestSpecification = Serenity.sessionVariableCalled(REQUEST_SPECIFICATION);
        Response apiResponse = apiConnector.calculateFactorial(apiRequestSpecification);
        Serenity.setSessionVariable(API_RESPONSE).to(apiResponse);
    }

    @Then("I will get factorial result for that number {int}")
    public void iWillGetFactorialResultForThatNumberValue(int number) {
        Response apiResponse = Serenity.sessionVariableCalled(API_RESPONSE);
        ResponseBody responseBody = apiResponse.as(ResponseBody.class);
        NumberUtil numberUtil = new NumberUtil();
        Double factorialResult = numberUtil.factorialDouble(number);
        Assert.assertEquals("Response Status Code is 200", HttpStatus.SC_OK, apiResponse.getStatusCode());
        Assert.assertEquals("Result is Correct", factorialResult, responseBody.getAnswer());
    }

    @Given("I provide non integer {string}")
    public void iProvideNonInteger(String value) {
        this.iProvideNumber(value);
    }

    @Step
    public void iCalculate(String value) {
        this.iProvideNumber(value);
        this.iTryToCalculate();
    }

    @Then("I will get error message")
    public void iWillGetErrorMessage() {
        Response apiResponse = Serenity.sessionVariableCalled(API_RESPONSE);
        Assert.assertNotEquals("Error code is NOT 500", HttpStatus.SC_INTERNAL_SERVER_ERROR, apiResponse.getStatusCode());
    }


}
