package site.nomoreparties.stellarburgers.global;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;
import site.nomoreparties.stellarburgers.APIHelpers.APIRequests;
import site.nomoreparties.stellarburgers.Config.AppConfig;
import site.nomoreparties.stellarburgers.po.Pages;

public class ApplicationManager {
    public final AppConfig config;
    public final Pages pages;
    public final Faker fakeData;
    public final APIRequests apiHelpers;

    public ApplicationManager() {
        config = ConfigFactory.create(AppConfig.class);
        pages = new Pages(this);
        fakeData = new Faker();
        apiHelpers = new APIRequests(this);
    }
}
