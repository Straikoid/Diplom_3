package site.nomoreparties.stellarburgers.po;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import static com.codeborne.selenide.Selenide.$;

public class Header {
    private final ApplicationManager app;

    public Header(ApplicationManager app) {
        this.app = app;
    }

    private final By locatorPrivateCabinetLink = By.xpath(".//header/nav/a/p[text()='Личный Кабинет']");
    private final By locatorConstructorLink = By.xpath(".//header/nav/ul/li/a/p[text()='Конструктор']");
    private final By locatorBurgerLogo = By.xpath(".//header/nav/div/a");

    @Step("Click on the MyCabinet Link in the Header")
    public void clickPrivateCabinetLink() {
        $(locatorPrivateCabinetLink).click();
    }

    @Step("Click on the Constructor Link in the Header")
    public void clickConstructorLink() {
        $(locatorConstructorLink).click();
    }

    @Step("Click on the Burger logo in the Header")
    public void clickBurgerLogo() {
        $(locatorBurgerLogo).click();
    }
}
