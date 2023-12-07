package base_test;

import com.alfa_bank.testframework.framework.driver.WebDriverRun;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import static org.testng.ITestResult.*;

@Log4j
public class BaseTest {
    public final SoftAssert SOFT_ASSERT = new SoftAssert();

    @BeforeSuite
    public void beforeSuite() {
        try {
            log.info("WebDriver is initialized successfully.");
            WebDriver driver = WebDriverRun.getInstanceDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Failed to initialize WebDriver", e);
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        log.info(String.format("Test '%s' is started", method.getName()));
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) {
        switch (result.getStatus()) {
            case SUCCESS:
                log.info(String.format("Test %s is passed", method.getName()));
                break;
            case FAILURE:
                log.error(String.format("Test %s is failed", method.getName()));
                break;
            case SKIP:
                log.error(String.format("Test %s is skipped", method.getName()));
                break;
        }
    }

    @AfterSuite
    public void afterSuite() {
        try {
            log.info("Tests automation is ended");
            WebDriverRun.closeWebDriver();
        } catch (Exception e) {
            log.error("Error during WebDriver cleanup", e);
        }
    }
}