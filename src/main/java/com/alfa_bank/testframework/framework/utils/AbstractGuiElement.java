package com.alfa_bank.testframework.framework.utils;

import com.alfa_bank.testframework.framework.driver.WebDriverRun;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.function.Supplier;

public abstract class AbstractGuiElement {
    private static final int DEFAULT_TIME_TO_WAIT = 5;
    private FluentWait<WebDriver> waiter;

    private FluentWait<WebDriver> getWebDriverWait() {
        if (waiter == null) {
            waiter = new FluentWait<>(WebDriverRun.getInstanceDriver())
                    .withTimeout(Duration.ofSeconds(DEFAULT_TIME_TO_WAIT))
                    .ignoring(NoSuchElementException.class);
        }
        return waiter;
    }

    protected void waitUntil(Supplier<Boolean> condition, int seconds) {
        getWebDriverWait().withTimeout(Duration.ofSeconds(seconds))
                .until(driver -> condition.get());
    }

    protected void waitUntil(Supplier<Boolean> condition) {
        getWebDriverWait().withTimeout(Duration.ofSeconds(DEFAULT_TIME_TO_WAIT))
                .until(driver -> condition.get());
    }

    protected void waitForElementToBeEnabled(WebElement webElement) {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }
}