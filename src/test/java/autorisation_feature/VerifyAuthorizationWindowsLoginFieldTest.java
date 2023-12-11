package autorisation_feature;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.alfa_bank.testframework.enums.AuthorisationFields.PASSWORD;
import static com.alfa_bank.testframework.enums.AuthorisationFields.USERNAME;

@Owner(PrepareDataFotAuthorizationFeature.ZOKAS_ALEX)
@Feature(PrepareDataFotAuthorizationFeature.AUTHORISATION_FEATURE)
public class VerifyAuthorizationWindowsLoginFieldTest extends PrepareDataFotAuthorizationFeature {
    private final String TEST_CASE_ID_1 = "1";
    private final String TEST_CASE_ID_2 = "2";

    @DataProvider(name = "testDataForLogin")
    public Object[][] testData() {
        return new Object[][]{
                {GENERATED_STRING_WITH_EXCEEDED_CHARACTERS, TRUNCATED_STRING_WITH_FIFTY_SYMBOLS, EXCEPT_VALUE},
                {GENERATED_STRING_WITH_NOT_ALLOWED_CHARACTERS, GENERATED_STRING_WITH_ALLOWED_CHARACTERS, INVALID_VALUE},
        };
    }

    @TmsLink(TEST_CASE_ID_1)
    @Test
    public void verifyAlfaBankLogoIsDisplayed() {
        Assert.assertEquals(AUTHORISATION_WINDOW.getAlfaBankLogo(), ALFA_BANK_LOGO,
                String.format(LOGO_SHOULD_BE_SHOWN, ALFA_BANK_LOGO));
    }

    @TmsLink(TEST_CASE_ID_2)
    @Test
    public void verifyLoggInFieldIsEmptyBeforeInteraction() {
        Assert.assertFalse(AUTHORISATION_WINDOW.getTextFromElement(USERNAME).isEmpty(),
                "The login input field should be empty");
    }

    @TmsLink(TEST_CASE_ID_2)
    @Test
    public void verifyAllowedCharactersSetInUserNameField() {
        AUTHORISATION_WINDOW.setTextValuesToInputFields(USERNAME, GENERATED_STRING_WITH_ALLOWED_CHARACTERS);

        Assert.assertEquals(AUTHORISATION_WINDOW.getTextFromElement(USERNAME), GENERATED_STRING_WITH_ALLOWED_CHARACTERS,
                String.format(FOLLOWING_TEXT_SHOULD_BE_DISPLAYED, GENERATED_STRING_WITH_ALLOWED_CHARACTERS));
    }

    @TmsLink(TEST_CASE_ID_2)
    @Test(dataProvider = "testDataForLogin")
    @Issue("issueId")
    //TODO remove the issue annotation, issue id and comments when bug is fixed
    public void verifyNotAllowedCharactersIsNotSetInUserNameField(String inputValue, String modifiedValue, String errorMessageText) {
        AUTHORISATION_WINDOW
                .setTextValuesToInputFields(USERNAME, inputValue)
                .clickOnInputField(PASSWORD);
        //  The login is not  truncated to the allowed limit
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getTextFromElement(USERNAME), modifiedValue,
                String.format(VALUE_SHOULD_BE_TRUNCATED + "The issue issueId", inputValue, modifiedValue));
        // The expected error message is not displayed and illegal characters are not truncated in the password field.
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getErrorMessage(), errorMessageText, String.format(ERROR_SHOULD_BE_SHOWN + "The issue issueId", errorMessageText));
        SOFT_ASSERT.assertAll();
    }
}