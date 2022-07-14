package site.nomoreparties.stellarburgers.po;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private final ApplicationManager app;

    public MainPage(ApplicationManager app) {
        this.app = app;
    }

    private final By locatorMyCabinetLink = By.xpath(".//header/nav/a/p");
    private final By locatorLoginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By locatorIngredientsSection = By.cssSelector("div.BurgerIngredients_ingredients__menuContainer__Xu3Mo");
    private final By locatorConstructorBunsTab = By.xpath(".//span[text()='Булки']//parent::div");
    private final By locatorConstructorSaucesTab = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div/span[text()='Соусы']");
    private final By locatorConstructorFillingTab = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/div/div/span[text()='Начинки']");
    private final By locatorIngredientsSectionBunsListHeader = By.xpath(".//h2[text()='Булки']");
    private final By locatorIngredientsSectionSaucesListHeader = By.xpath(".//h2[text()='Соусы']");
    private final By locatorFillingsSectionSaucesListHeader = By.xpath(".//h2[text()='Начинки']");

    public void clickMyCabinetLinkAndFollowToMyCabinet() {
        $(locatorMyCabinetLink).click();
    }

    @Step("Click on the Login button tab on the Main page")
    public void clickLoginButton() {
        $(locatorLoginButton).click();
    }

    public Boolean isConstructorLoaded() {
        return $(locatorIngredientsSection).is(visible);
    }

    @Step("Click on the Buns tab")
    public void clickConstructorBunsTab() throws InterruptedException {
        $(locatorConstructorBunsTab).click();
        waitForScrolling();
    }

    @Step("Wait for ingredients section scrolling")
    public void waitForScrolling() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Step("Click on the Sauces tab")
    public void clickConstructorSaucesTab() throws InterruptedException {
        $(locatorConstructorSaucesTab).click();
        waitForScrolling();
    }

    @Step("Click on the Filling tab")
    public void clickConstructorFillingTab() throws InterruptedException {
        $(locatorConstructorFillingTab).click();
        waitForScrolling();
    }

    public boolean isBunSectionPartSelected() {
        return $(locatorIngredientsSectionBunsListHeader).getLocation().y == $(locatorIngredientsSection).getLocation().y;
    }

    public boolean isSaucesSectionPartSelected() {
        return $(locatorIngredientsSectionSaucesListHeader).getLocation().y == $(locatorIngredientsSection).getLocation().y;
    }

    public boolean isFillingSectionPartSelected() {
        return $(locatorFillingsSectionSaucesListHeader).getLocation().y == $(locatorIngredientsSection).getLocation().y;
    }

    @Step("Wait for loading the Main page")
    public void waitForLoad() {
        $(locatorIngredientsSection).shouldBe(visible, Duration.ofSeconds(app.config.timeout()));
    }
}
