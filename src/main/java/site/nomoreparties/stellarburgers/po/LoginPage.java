package site.nomoreparties.stellarburgers.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final ApplicationManager app;

    public LoginPage(ApplicationManager app) {
        this.app = app;
    }

    private final By locatorRegisterLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By locatorRestorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");
    private final By locatorEmailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By locatorPasswordInput = By.cssSelector("div.input_type_password input");
    private final By locatorLoginButton = By.xpath(".//button[text()='Войти']");

    @Step("Click on Register link on the login page")
    public void clickRegisterLink() {
        $(locatorRegisterLink).click();
    }

    @Step("Click on Restore password link on the login page")
    public void clickRestorePasswordLink() {
        $(locatorRestorePasswordLink).click();
    }

    @Step("Enter value in the Email field on the Login page")
    public void enterValueInTheEmailField(String value) {
        $(locatorEmailInput).setValue(value);
    }

    @Step("Enter value in the Password field on the Login page")
    public void enterValueInThePasswordField(String value) {
        $(locatorPasswordInput).setValue(value);
    }

    @Step("Click Login button on the Login page")
    public void clickLoginButton() {
        $(locatorLoginButton).click();
    }

    public void login(String email, String password) {
        waitForLoad();
        enterValueInTheEmailField(email);
        enterValueInThePasswordField(password);
        clickLoginButton();
        $(locatorLoginButton).should(not(exist));
    }

    @Step("Wait for load the Login page")
    public void waitForLoad() {
        $(locatorRegisterLink).shouldBe(visible, Duration.ofSeconds(app.config.timeout()));
    }
}

