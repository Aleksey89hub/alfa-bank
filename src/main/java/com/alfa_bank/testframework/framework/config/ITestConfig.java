package com.alfa_bank.testframework.framework.config;

import com.alfa_bank.testframework.enums.Platform;
import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;
import static io.appium.java_client.remote.MobilePlatform.ANDROID;

@Config.Sources("classpath:config.properties")
public interface ITestConfig extends Config {
    ITestConfig CONFIG = ConfigFactory.create(ITestConfig.class);

    @Config.DefaultValue(ANDROID)
    Platform platform();
    String adminName();
    String adminPassword();
    String userName();
    String userPassword();
}