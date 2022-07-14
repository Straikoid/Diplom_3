package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.nomoreparties.stellarburgers.APIHelpers.AccountCredentials;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

@Feature("Registration process")
@Story("Registration with correct/incorrect login")
public class RegistrationTests extends TestBase {
    SoftAssertions sortAssert = new SoftAssertions();
    private String name;
    private String email;

    @BeforeEach
    public void beforeTestActions() {
        name = app.fakeData.name().firstName();
        email = name + "@UItest.com";
        open(app.config.loginPageUrl());
        app.pages.loginPage.waitForLoad();
        app.pages.loginPage.clickRegisterLink();
        app.pages.registrationPage.waitForLoad();
    }

    @Test
    @DisplayName("Registration with incorrect login")
    public void warningForIncorrectPasswordIsShown() {
        String password = RandomStringUtils.randomAlphabetic(5);
        app.pages.registrationPage.register(name, email, password);
        Assertions.assertTrue(app.pages.registrationPage.isIncorrectPasswordValidationPresent());
    }

    @Test
    @DisplayName("Registration with correct login")
    public void accountSuccessfullyRegisteredCorrectPassword() {
        String password = RandomStringUtils.randomAlphabetic(6);
        app.pages.registrationPage.register(name, email, password);
        app.pages.registrationPage.waitForClose();
        sortAssert.assertThat(app.config.loginPageUrl()).isEqualTo(app.pages.getCurrentUrl());
        AccountCredentials accountCredentials = new AccountCredentials(email, password);
        String authToken = app.apiHelpers.getAuthTokenForAccount(accountCredentials);
        int requestStatus = app.apiHelpers.deleteAccountAndGetStatusCode(authToken);
        sortAssert.assertThat(requestStatus).isEqualTo(SC_ACCEPTED);
        sortAssert.assertAll();
    }
}
