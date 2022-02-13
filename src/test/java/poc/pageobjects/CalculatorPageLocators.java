package poc.pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public abstract class CalculatorPageLocators extends PageObject {

    @FindBy(id = "number")
    public WebElementFacade numberInputField;
    @FindBy(id = "getFactorial")
    public WebElementFacade submitBtn;
    @FindBy(id = "resultDiv")
    public WebElementFacade result;

}
