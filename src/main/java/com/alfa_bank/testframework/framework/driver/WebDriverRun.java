package com.alfa_bank.testframework.framework.driver;

import com.alfa_bank.testframework.framework.config.ITestConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class WebDriverRun {
    private static final String APPIUM_SERVER_URL = "http://192.168.1.103:4723/";

    private static final String ANDROID_PLATFORM = "Android";
    private static final String PLATFORM_VERSION = "34";

    private static final String ANDROID_UDID = "emulator-5554";
    private static final String ANDROID_AUTOMATION_NAME = "UiAutomator2";
    private static final String ANDROID_APP_PATH = System.getProperty("user.dir") + "/app/app-release.apk";
    private static WebDriver driver;
    private static final DesiredCapabilities CAPABILITIES = new DesiredCapabilities();

    private WebDriverRun() {
    }

    public static WebDriver getInstanceDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        driver = switch (ITestConfig.CONFIG.platform()) {
            case ANDROID -> getAndroidDriver();
            case IOS -> getIOSDriver();
        };
    }

    public static WebDriver getAndroidDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ANDROID_PLATFORM);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.UDID, ANDROID_UDID);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, ANDROID_AUTOMATION_NAME);
        capabilities.setCapability(MobileCapabilityType.APP, ANDROID_APP_PATH);

        try {
            return new AppiumDriver<>(new URL(APPIUM_SERVER_URL), capabilities);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException(String.format("Failed to initialize AndroidDriver: %s", e.getMessage()));
        }
    }

    private static WebDriver getIOSDriver() {
        try {
            return new IOSDriver<>(new URL(APPIUM_SERVER_URL), CAPABILITIES);
        } catch (java.net.MalformedURLException e) {
            throw new RuntimeException(String.format("Failed to initialize IOSDriver: %s", e.getMessage()));
        }
    }

    public static void closeWebDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}