package com.alfa_bank.testframework.pages;

import com.alfa_bank.testframework.framework.driver.WebDriverRun;
import com.alfa_bank.testframework.enums.AuthorisationFields;
import com.alfa_bank.testframework.model.User;
import com.alfa_bank.testframework.framework.utils.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.alfa_bank.testframework.enums.AuthorisationFields.PASSWORD;
import static com.alfa_bank.testframework.enums.AuthorisationFields.USERNAME;

public class AuthorisationWindow extends Input {

    @FindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'tvTitle')]")
    private WebElement logoLabel;

    @FindBy(xpath = " //android.widget.EditText[contains(@resource-id, 'etUsername')]")
    private WebElement loginInputField;

    @FindBy(xpath = "//android.widget.EditText[contains(@resource-id, 'etPassword')]")
    private WebElement passwordInputField;

    @FindBy(xpath = "//android.widget.Button[contains(@resource-id, 'btnConfirm')]")
    private WebElement submitButton;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Show password']")
    private WebElement showPasswordButton;

    @FindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'tvError')]")
    private WebElement errorMessage;

    private WebElement generateInputField(String fieldName) {
        return WebDriverRun.getInstanceDriver().findElement(By.xpath(String.format("//android.widget.EditText[@resource-id='com.alfabank.qapp:id/%s']", fieldName)));
    }

    public AuthorisationWindow(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static AuthorisationWindow create(WebDriver driver) {
        return new AuthorisationWindow(driver);
    }

    @Step("Is the logo of alfabank shown")
    public boolean isLogoShown() {
        return logoLabel.isDisplayed();
    }


    @Step("Get logo of Alfa bank")
    public String getAlfaBankLogo() {
        waitUntil(() -> loginInputField.isDisplayed());
        return logoLabel.getText();
    }

    @Step("Get text from the '{0}' input element")
    public String getTextFromElement(AuthorisationFields authorisationFields) {
        WebElement element = generateInputField(authorisationFields.getFieldName());
        waitUntil(element::isDisplayed, 5);
        return element.getText();
    }

    @Step("Set text '{1}' to the '{0}' input field")
    public AuthorisationWindow setTextValuesToInputFields(AuthorisationFields authorisationFields, String text) {
        clearAndType(generateInputField(authorisationFields.getFieldName()), text);

        return this;
    }

    @Step("Click on the '{0}' input field")
    public AuthorisationWindow clickOnInputField(AuthorisationFields authorisationFields) {
        WebElement element = generateInputField(authorisationFields.getFieldName());
        waitForElementToBeEnabled(element);
        element.click();

        return this;
    }

    @Step("Click on the show password button")
    public AuthorisationWindow clickOnShowPasswordButton() {
        waitForElementToBeEnabled(showPasswordButton);
        showPasswordButton.click();

        return this;
    }

    @Step("Click on the show password button")
    public MainPage loginToAlfaBankApp(User user) {
        clearAndType(generateInputField(USERNAME.getFieldName()), user.getUserName());
        clearAndType(generateInputField(PASSWORD.getFieldName()), user.getPassword());
        clickOnSubmitButton();

        return new MainPage(WebDriverRun.getInstanceDriver());
    }

    @Step("Get the show password button state")
    public String getShowPasswordButtonState() {
        String isChecked = showPasswordButton.getAttribute("checked");

        if ("true".equals(isChecked)) {
            return "The button is enabled";
        } else if ("false".equals(isChecked)) {
            return "The button is disabled";
        } else {
            return "The button state is unknown";
        }
    }

    @Step("Click on submit button")
    public void clickOnSubmitButton() {
        waitForElementToBeEnabled(submitButton);
        submitButton.click();
    }

    @Step("Get the error message")
    public String getErrorMessage() {
        waitUntil(() -> errorMessage.isDisplayed(), 10);
        return errorMessage.getText();
    }
}