package autorisation_feature;

import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.alfa_bank.testframework.enums.AuthorisationFields.PASSWORD;
import static com.alfa_bank.testframework.enums.AuthorisationFields.USERNAME;


@Owner(PrepareDataFotAuthorizationFeature.ZOKAS_ALEX)
@Feature(PrepareDataFotAuthorizationFeature.AUTHORISATION_FEATURE)
public class VerifyPasswordInputFieldTest extends PrepareDataFotAuthorizationFeature {
    private final String TEST_CASE_ID = "3";
    private final String TEXT_WITH_ASTERISKS = GENERATED_STRING_WITH_ALLOWED_CHARACTERS.replaceAll("\\.", "*");

    @DataProvider(name = "testDataForShowPasswordButton")
    public Object[][] testData() {
        return new Object[][]{
                {GENERATED_STRING_WITH_ALLOWED_CHARACTERS, BUTTON_SHOULD_BE_ENABLED, TEXT_WITH_ASTERISKS},
                {GENERATED_STRING_WITH_ALLOWED_CHARACTERS, BUTTON_SHOULD_BE_DISABLED, GENERATED_STRING_WITH_ALLOWED_CHARACTERS},
        };
    }

    @TmsLink(TEST_CASE_ID)
    @Test(dataProvider = "testDataForShowPasswordButton")
    public void verifyPasswordIsReplacedByAsterisksOrChangedBackAfterClickingOnShowPasswordButton(String input, String buttonCondition, String modifiedTest) {
        AUTHORISATION_WINDOW
                .setTextValuesToInputFields(PASSWORD, input)
                .clickOnShowPasswordButton();

        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getTextFromElement(PASSWORD), modifiedTest,
                String.format(FOLLOWING_TEXT_SHOULD_BE_DISPLAYED, modifiedTest));
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getShowPasswordButtonState(), buttonCondition,
                String.format("The button state: %s", buttonCondition));
        SOFT_ASSERT.assertAll();
    }

    @Test(dependsOnMethods = "verifyPasswordIsReplacedByAsterisksOrChangedBackAfterClickingOnShowPasswordButton", alwaysRun = true)
    @Issue("issueId")
    //TODO remove the issue annotation and comments when bug is fixed
    public void verifyPasswordFieldIsTruncatedWhenCharactersAmountIsExceeded() {
        String issue = " the issue id is issueId";
        AUTHORISATION_WINDOW
                .setTextValuesToInputFields(PASSWORD, GENERATED_STRING_WITH_EXCEEDED_CHARACTERS)
                .clickOnInputField(USERNAME);
        //The password is not  truncated to the allowed limit
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getTextFromElement(PASSWORD), TRUNCATED_STRING_WITH_FIFTY_SYMBOLS,
                String.format(VALUE_SHOULD_BE_TRUNCATED + issue, GENERATED_STRING_WITH_EXCEEDED_CHARACTERS, TRUNCATED_STRING_WITH_FIFTY_SYMBOLS));
        //The expected error message is not displayed
        SOFT_ASSERT.assertEquals(AUTHORISATION_WINDOW.getErrorMessage(), EXCEPT_VALUE, String.format(ERROR_SHOULD_BE_SHOWN + issue, EXCEPT_VALUE));
        SOFT_ASSERT.assertAll();
    }

}