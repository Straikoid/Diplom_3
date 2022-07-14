package site.nomoreparties.stellarburgers.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;
;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class RestorePassword  {
    private final ApplicationManager app;
    private final By locatorLoginLink = By.cssSelector("a.Auth_link__1fOlj");

    public RestorePassword(ApplicationManager app) {
        this.app = app;
    }

    @Step("Wait for load ResetPassword page")
    public void waitForLoad() {
        $(locatorLoginLink).shouldBe(visible, Duration.ofSeconds(app.config.timeout()));
    }

    @Step("Click login link on the Registration page")
    public void clickLoginLink() {
        $(locatorLoginLink).click();
    }
}
