package site.nomoreparties.stellarburgers.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

@Feature("Page links tests")
@Story("Order constructor section testing")
public class MainPageTests extends TestBase {

    @BeforeEach
    public void beforeTestActions() {
        open(app.config.mainPageUrl());
        app.pages.mainPage.waitForLoad();
    }

    @Test
    @DisplayName("Follow to the Bun section in the ingredients")
    public void followToTheBunsSection() throws InterruptedException {
        app.pages.mainPage.clickConstructorSaucesTab();
        app.pages.mainPage.clickConstructorBunsTab();
        Assertions.assertTrue(app.pages.mainPage.isBunSectionPartSelected());
    }

    @Test
    @DisplayName("Follow to the Sauces section in the ingredients")
    public void followToTheSaucesSection() throws InterruptedException {
        app.pages.mainPage.clickConstructorSaucesTab();
        Assertions.assertTrue(app.pages.mainPage.isSaucesSectionPartSelected());
    }

    @Test
    @DisplayName("Follow to the Fillings section in the ingredients")
    public void followToTheFillingsSection() throws InterruptedException {
        app.pages.mainPage.clickConstructorFillingTab();
        Assertions.assertTrue(app.pages.mainPage.isFillingSectionPartSelected());
    }
}
