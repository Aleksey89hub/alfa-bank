package autorisation_feature;

import com.alfa_bank.testframework.framework.testdata.Users;
import com.alfa_bank.testframework.pages.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;

@Owner(PrepareDataFotAuthorizationFeature.ZOKAS_ALEX)
@Feature(PrepareDataFotAuthorizationFeature.AUTHORISATION_FEATURE)
public class VerifyLoginTest extends PrepareDataFotAuthorizationFeature {
    private final String TEST_CASE_ID_1 = "5";

    @TmsLink(TEST_CASE_ID_1)
    @Test
    public void verifyUserCannotLoginWithIncorrectCredentials() {
        AUTHORISATION_WINDOW
                .loginToAlfaBankApp(Users.createUser());

        SOFT_ASSERT.assertTrue(AUTHORISATION_WINDOW.isLogoShown(),
                String.format(LOGO_SHOULD_BE_SHOWN, ALFA_BANK_LOGO));
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getErrorMessage(), INCORRECT_CREDENTIALS,
                String.format(ERROR_SHOULD_BE_SHOWN, INCORRECT_CREDENTIALS));
        SOFT_ASSERT.assertAll();
    }

    @TmsLink(TEST_CASE_ID_1)
    @Test(dependsOnMethods = "verifyUserCannotLoginWithIncorrectCredentials", alwaysRun = true)
    public void verifyUserCanLoginWithCorrectCredentials() {
        MainPage mainPage = AUTHORISATION_WINDOW
                .loginToAlfaBankApp(Users.createAdminUser());

        SOFT_ASSERT.assertTrue(AUTHORISATION_WINDOW.isLogoShown(),
                String.format(LOGO_SHOULD_BE_SHOWN, ALFA_BANK_LOGO));
        SOFT_ASSERT.assertEquals(mainPage.getMainPageTitle(), "Вход в Alfa-Test выполнен",
                "The main page title should be shown");
        SOFT_ASSERT.assertAll();
    }
}
