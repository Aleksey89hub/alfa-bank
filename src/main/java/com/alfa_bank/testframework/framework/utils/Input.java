package com.alfa_bank.testframework.framework.utils;

import org.openqa.selenium.WebElement;
public class Input extends AbstractGuiElement {

    public void clearAndType(WebElement webElement, String text) {
        waitForElementToBeEnabled(webElement);
        clearInputField(webElement);
        sendKeysToInputField(webElement, text);
    }

    private void clearInputField(WebElement webElement) {
        webElement.clear();
    }

    private void sendKeysToInputField(WebElement webElement, String text) {
        webElement.sendKeys(text);
    }
}
