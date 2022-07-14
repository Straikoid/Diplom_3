package site.nomoreparties.stellarburgers.po;

import com.codeborne.selenide.WebDriverRunner;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

public class Pages {
    public MainPage mainPage;
    public LoginPage loginPage;
    public RegistrationPage registrationPage;
    public RestorePassword restorePassword;
    public Header header;
    public PrivateCabinet privateCabinet;

    public Pages(ApplicationManager app) {
        mainPage = new MainPage(app);
        loginPage = new LoginPage(app);
        registrationPage = new RegistrationPage(app);
        restorePassword = new RestorePassword(app);
        header = new Header(app);
        privateCabinet = new PrivateCabinet(app);

    }

    public String getCurrentUrl() {
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}
