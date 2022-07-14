package site.nomoreparties.stellarburgers.Config;


import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.Sources;

@Sources({"file:src/test/resources/app-config.resources.properties"})
public interface AppConfig extends Config {

    @Key("global.timeout")
    int timeout();

    @Key("main.page.url")
    String mainPageUrl();

    @Key("login.page.url")
    String loginPageUrl();

    @Key("login.user.api.url")
    String loginAccountApiPath();

    @Key("edit.user.api.url")
    String editAccountApiPath();

    @Key("private.cabinet.url")
    String privateCabinetUrl();

    @Key("testing.browser")
    String testingBrowser();
}
