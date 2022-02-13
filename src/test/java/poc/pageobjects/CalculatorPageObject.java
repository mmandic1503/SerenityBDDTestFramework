package poc.pageobjects;

import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;

@DefaultUrl("page:factorial.url")
public class CalculatorPageObject extends CalculatorPageLocators {

    public static final String PLACEHOLDER_VALUE = "Enter an integer";
    public static final String PLACEHOLDER_ATTRIBUTE = "placeholder";
    public static final String BTN_TEXT = "Calculate!";

    public void openCalculatorPage() {
        this.open();
    }

    private void verifyInputField() {
        numberInputField.isPresent();
        numberInputField.isVisible();
        Assert.assertEquals(PLACEHOLDER_VALUE, numberInputField.getAttribute(PLACEHOLDER_ATTRIBUTE));
    }

    private void verifyCalculateBtn() {
        submitBtn.isVisible();
        submitBtn.isEnabled();
        Assert.assertEquals(BTN_TEXT, submitBtn.getText());
    }

    public void provideNumber(String numberValue) {
        this.verifyInputField();
        numberInputField.type(numberValue);
    }

    public void clickCalculateBtn() {
        this.verifyCalculateBtn();
        submitBtn.click();
    }

    public void verifyResult(String resultValue) {
        result.isVisible();
        System.out.println("EXPECTING: "+resultValue);
        System.out.println("ACTUAL: "+result.getText());
        Assert.assertTrue(resultValue.equalsIgnoreCase(result.getText()));
    }

}
