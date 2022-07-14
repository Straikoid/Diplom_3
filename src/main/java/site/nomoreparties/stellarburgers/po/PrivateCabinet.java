package site.nomoreparties.stellarburgers.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PrivateCabinet {

    private final ApplicationManager app;

    public PrivateCabinet(ApplicationManager app) {
        this.app = app;
    }

    private final By locatorProfileLink = By.xpath(".//main/div/nav/ul/li/a[text()='Профиль']");
    private final By locatorHistoryLink = By.xpath(".//main/div/nav/ul/li/a[text()='История заказов']");
    private final By locatorExitLink = By.xpath(".//main/div/nav/ul/li/button[text()='Выход']");
    private final By locatorSaveButton = By.xpath(".//button[text()='Сохранить']");

    public Boolean isPageLoaded() {
        return $(locatorProfileLink).isDisplayed() &&
                $(locatorHistoryLink).isDisplayed() &&
                $(locatorExitLink).isDisplayed();
    }

    @Step("Click Exit link in the my Cabinet")
    public void clickExitLink() {
        $(locatorExitLink).click();
    }

    @Step("Wait for load MyCabinet page")
    public void waitForLoad() {
        $(locatorSaveButton).shouldBe(visible, Duration.ofSeconds(app.config.timeout()));
    }

    @Step("Wait for close MyCabinet page")
    public void waitForClose() {
        $(locatorSaveButton).shouldBe(not(exist));
    }
}
