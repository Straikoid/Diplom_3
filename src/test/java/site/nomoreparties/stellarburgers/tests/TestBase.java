package site.nomoreparties.stellarburgers.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static site.nomoreparties.stellarburgers.global.Global.appManager;

public class TestBase {
    protected static ApplicationManager app = appManager;

    @BeforeAll
    public static void prepare() {
        Configuration.browser = app.config.testingBrowser();
        Configuration.startMaximized = true;
    }

    @BeforeEach
    public void setUp() {
        Configuration.startMaximized = true;
    }


    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }

}
