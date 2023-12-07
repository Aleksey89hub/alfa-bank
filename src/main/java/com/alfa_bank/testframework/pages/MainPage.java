package com.alfa_bank.testframework.pages;

import com.alfa_bank.testframework.framework.utils.Input;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Input {
    @FindBy(xpath = "//android.widget.TextView[@text='Вход в Alfa-Test выполнен']")
    WebElement mainPageTitle;

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @Step("Get the main page title")
    public String getMainPageTitle() {
        waitUntil(() -> mainPageTitle.isDisplayed(), 10);
        return mainPageTitle.getText();
    }
}
