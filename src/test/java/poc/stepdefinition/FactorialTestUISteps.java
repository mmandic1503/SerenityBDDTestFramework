package poc.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import poc.pageobjects.CalculatorPageObject;
import poc.services.models.ResponseBody;
import poc.util.NumberUtil;

import java.math.BigDecimal;

import static poc.util.SessionEnums.API_RESPONSE;
import static poc.util.SessionEnums.NUMBER_VALUE;

public class FactorialTestUISteps {

    private CalculatorPageObject calculatorPageObject;
    @Steps
    private FactorialTestSteps factorialTestSteps;

    @Given("I navigate to Factorial Calculator web page")
    public void iNavigateToFactorialCalculatorWebPage() {
        calculatorPageObject.openCalculatorPage();
    }

    @Given("I provide valid number")
    public void iProvideValidNumber() {
        Integer randomNumber = new NumberUtil().randomInt(700);
        Serenity.setSessionVariable(NUMBER_VALUE).to(randomNumber);
        calculatorPageObject.provideNumber(randomNumber.toString());
    }

    @When("I press calculate button")
    public void iPressCalculateButton() {
        calculatorPageObject.clickCalculateBtn();
    }

    @Then("I will get factorial result for that number")
    public void iWillGetFactorialResultForThatNumber() {
        Integer numberValue = Serenity.sessionVariableCalled(NUMBER_VALUE);
        factorialTestSteps.iCalculate(numberValue.toString());
        Response apiResponse = Serenity.sessionVariableCalled(API_RESPONSE);
        ResponseBody responseBody = apiResponse.as(ResponseBody.class);
        String expectedValue = responseBody.getAnswer().toString();
        if (!responseBody.getAnswer().isInfinite()) {
            expectedValue = BigDecimal.valueOf(responseBody.getAnswer()).toString();
            System.out.println("EXPECCTESSTDFDSG "+expectedValue);
        }
        calculatorPageObject.verifyResult("The factorial of "+numberValue+" is: "+expectedValue);
    }

    @Given("I provide invalid data {string}")
    public void iProvideInvalidData(String textValue) {
        calculatorPageObject.provideNumber(textValue);
    }

    @Then("I will see error message")
    public void iWillSeeErrorMessage() {
        calculatorPageObject.verifyResult("Please enter an integer");
    }
}
