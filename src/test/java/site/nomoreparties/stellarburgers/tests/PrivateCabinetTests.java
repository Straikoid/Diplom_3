package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import site.nomoreparties.stellarburgers.APIHelpers.AccountCredentials;

import static com.codeborne.selenide.Selenide.open;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

@Feature("Page links tests")
@Story("Private cabinet links testing")
public class PrivateCabinetTests extends TestBase {
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
        app.pages.registrationPage.waitForClose();
        accountCredentials = new AccountCredentials(email, password);
    }

    @Test
    @DisplayName("Follow to the Private Cabinet page")
    public void followToThePrivateCabinet() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        app.pages.header.clickPrivateCabinetLink();
        app.pages.privateCabinet.waitForLoad();
        Assertions.assertEquals(app.config.privateCabinetUrl(), app.pages.getCurrentUrl());
        Assertions.assertTrue(app.pages.privateCabinet.isPageLoaded());
    }

    @Test
    @DisplayName("Follow to the Constructor from the Private Cabinet page")
    public void followToTheConstructorFromPrivateCabinetByConstructorLink() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        app.pages.header.clickPrivateCabinetLink();
        app.pages.privateCabinet.waitForLoad();
        app.pages.header.clickConstructorLink();
        app.pages.privateCabinet.waitForClose();
        Assertions.assertEquals(app.config.mainPageUrl(), app.pages.getCurrentUrl());
        Assertions.assertTrue(app.pages.mainPage.isConstructorLoaded());
    }

    @Test
    @DisplayName("Follow to the Constructor from the Private Cabinet page via Burger logo")
    public void followToTheConstructorFromPrivateCabinetByLogoLink() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        app.pages.header.clickPrivateCabinetLink();
        app.pages.privateCabinet.waitForLoad();
        app.pages.header.clickBurgerLogo();
        app.pages.privateCabinet.waitForClose();
        Assertions.assertEquals(app.config.mainPageUrl(), app.pages.getCurrentUrl());
        Assertions.assertTrue(app.pages.mainPage.isConstructorLoaded());
    }

    @Test
    @DisplayName("Logout from the MyCabinet")
    public void logoutFromPrivateCabinet() {
        open(app.config.loginPageUrl());
        app.pages.loginPage.login(accountCredentials.getEmail(), accountCredentials.getPassword());
        app.pages.header.clickPrivateCabinetLink();
        app.pages.privateCabinet.waitForLoad();
        app.pages.privateCabinet.clickExitLink();
        app.pages.privateCabinet.waitForClose();
        Assertions.assertEquals(app.config.loginPageUrl(), app.pages.getCurrentUrl());
    }

    @AfterAll
    public static void removeTestData() {
        String authToken = app.apiHelpers.getAuthTokenForAccount(accountCredentials);
        int requestStatus = app.apiHelpers.deleteAccountAndGetStatusCode(authToken);
        Assertions.assertEquals(requestStatus, SC_ACCEPTED);
    }
}
