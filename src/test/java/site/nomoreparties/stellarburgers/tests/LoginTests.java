package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import site.nomoreparties.stellarburgers.APIHelpers.AccountCredentials;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

@Feature("Login process")
@Story("Login from different pages testing")
public class LoginTests extends TestBase {
    private static AccountCredentials accountCredentials;

    @BeforeAll
    public static void beforeTestActions() {
        String name = app.fakeData.name().firstName();
        String email = name + "@UItest.com";
        String password = RandomStringUtils.randomAlphabetic(6);
        open(app.config.loginPageUrl());
        app.pages.loginPage.waitForLoad();
        app.pages.loginPage.clickRegisterLink();
        app.pages.registrationPage.waitForLoad();
        app.pages.registrationPage.register(name, email, password);
        accountCredentials = new AccountCredentials(email, password);
    }

    @Test
    @DisplayName("Login from the main page")
    public void loginFromTheMainPage() {
        open(app.config.mainPageUrl());
        app.pages.mainPage.clickLoginButton();
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        Assertions.assertEquals(app.config.mainPageUrl(), (app.pages.getCurrentUrl()));

    }

    @Test
    @DisplayName("Login from the MyCabinet page")
    public void loginFromThePrivateCabinetPage() {
        open(app.config.mainPageUrl());
        app.pages.mainPage.clickMyCabinetLinkAndFollowToMyCabinet();
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        Assertions.assertEquals(app.config.mainPageUrl(), (app.pages.getCurrentUrl()));

    }

    @Test
    @DisplayName("Login from the Registering page")
    public void loginFromTheRegisterPage() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.waitForLoad();
        app.pages.loginPage.clickRegisterLink();
        app.pages.registrationPage.waitForLoad();
        app.pages.registrationPage.clickLoginButton();
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        Assertions.assertEquals(app.config.mainPageUrl(), (app.pages.getCurrentUrl()));
    }

    @Test
    @DisplayName("Login from the RestorePassword page")
    public void loginFromTheRestorePasswordPage() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.waitForLoad();
        app.pages.loginPage.clickRestorePasswordLink();
        app.pages.restorePassword.waitForLoad();
        app.pages.restorePassword.clickLoginLink();
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        Assertions.assertEquals(app.config.mainPageUrl(), (app.pages.getCurrentUrl()));
    }

    @AfterAll
    public static void removeTestData() {
        String authToken = app.apiHelpers.getAuthTokenForAccount(accountCredentials);
        int requestStatus = app.apiHelpers.deleteAccountAndGetStatusCode(authToken);
        Assertions.assertEquals(requestStatus, SC_ACCEPTED);
    }
}
