package site.nomoreparties.stellarburgers.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationPage  {
    private final ApplicationManager app;

    private final By locatorNameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By locatorEmailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By locatorPasswordFrame = By.cssSelector("div.input_type_password");
    private final By locatorPasswordInput = By.cssSelector("div.input_type_password input");
    private final By locatorRegisterButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By locatorIncorrectPasswordValidationMessage = By.cssSelector("p.input__error");
    private final By locatorLoginButton = By.cssSelector("a.Auth_link__1fOlj");

    public RegistrationPage(ApplicationManager app) {
        this.app = app;
    }

    @Step("Enter value in the Name field on the Registration page")
    public void enterValueInTheNameField(String value) {
        $(locatorNameInput).setValue(value);
    }

    @Step("Enter value in the Email field on the Registration page")
    public void enterValueInTheEmailField(String value) {
        $(locatorEmailInput).setValue(value);
    }

    @Step("Enter value in the Password field on the Registration page")
    public void enterValueInThePasswordField(String value) {
        $(locatorPasswordInput).setValue(value);
    }

    @Step("Click Register button on the Login page")
    public void clickRegisterButton() {
        $(locatorRegisterButton).click();
    }

    @Step("Click Login button on the Login page")
    public void clickLoginButton() {
        $(locatorLoginButton).click();
    }

    public boolean isIncorrectPasswordValidationPresent() {
        return ($(locatorPasswordFrame).is(have(cssClass("input_status_error"))) &&
                $(locatorIncorrectPasswordValidationMessage).isDisplayed());
    }

    public void register(String name, String email, String password) {
        enterValueInTheNameField(name);
        enterValueInTheEmailField(email);
        enterValueInThePasswordField(password);
        clickRegisterButton();
    }

    @Step("Wait for load Registration page")
    public void waitForLoad() {
        $(locatorRegisterButton).shouldBe(visible, Duration.ofSeconds(app.config.timeout()));
    }

    @Step("Wait for close Registration page")
    public void waitForClose() {
        $(locatorRegisterButton).shouldBe(not(exist));
    }
}
