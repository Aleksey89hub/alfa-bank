package autorisation_feature;

import base_test.BaseTest;
import com.alfa_bank.testframework.framework.driver.WebDriverRun;
import com.alfa_bank.testframework.pages.AuthorisationWindow;
import com.alfa_bank.testframework.framework.utils.StringUtils;
import com.github.javafaker.Faker;

import static com.alfa_bank.testframework.framework.utils.StringUtils.generateRandomString;

public class PrepareDataFotAuthorizationFeature extends BaseTest {
    protected final AuthorisationWindow AUTHORISATION_WINDOW = new AuthorisationWindow(WebDriverRun.getInstanceDriver());
    protected final Faker FAKER = new Faker();
    protected static final String ALLOWED_CHARACTERS_REGEX = "[A-Za-z.,/'_-]";
    protected static final String NOT_ALLOWED_CHARACTERS_REGEX = "[^A-Za-z.,/'_-]";
    protected static final String ZOKAS_ALEX = "Test case owner is Zokas Alex";
    protected static final String AUTHORISATION_FEATURE = "Authorisation feature";
    protected final String GENERATED_STRING_WITH_ALLOWED_CHARACTERS = generateRandomString(FAKER.number().numberBetween(10, 20), ALLOWED_CHARACTERS_REGEX);
    protected final String GENERATED_STRING_WITH_EXCEEDED_CHARACTERS = generateRandomString(FAKER.number().numberBetween(51, 70), ALLOWED_CHARACTERS_REGEX);
    protected final String TRUNCATED_STRING_WITH_FIFTY_SYMBOLS = GENERATED_STRING_WITH_EXCEEDED_CHARACTERS.substring(0, 50);
    protected final String GENERATED_STRING_WITH_NOT_ALLOWED_CHARACTERS = String.join(StringUtils.EMPTY, generateRandomString(FAKER.number().numberBetween(10, 15), NOT_ALLOWED_CHARACTERS_REGEX),
            GENERATED_STRING_WITH_ALLOWED_CHARACTERS);
    protected final String ALFA_BANK_LOGO = "Вход в Alfa-Test";
    protected final String INVALID_VALUE = "InvalidValue.";
    protected final String EXCEPT_VALUE = "ExceptValue";
    protected final String FOLLOWING_TEXT_SHOULD_BE_DISPLAYED = "The following text '%s' should be displayed";
    protected final String BUTTON_SHOULD_BE_ENABLED = "The button is enabled";
    protected final String BUTTON_SHOULD_BE_DISABLED = "The button is disabled";
    protected final String VALUE_SHOULD_BE_TRUNCATED = "The '%s' value should be turned into the '%s' value";
    protected final String ERROR_SHOULD_BE_SHOWN = "The %s error should be shown";
    protected final String INCORRECT_CREDENTIALS = "Введены неверные данные";
    protected final String LOGO_SHOULD_BE_SHOWN = "The logo %s should be shown";
}