package site.nomoreparties.stellarburgers.APIHelpers;

import io.qameta.allure.Step;
import site.nomoreparties.stellarburgers.global.ApplicationManager;

import static io.restassured.RestAssured.given;

public class APIRequests extends RestAssuredClient {
    private final ApplicationManager app;

    public APIRequests(ApplicationManager app) {
        this.app = app;
    }

    @Step("Login account and get token")
    public String getAuthTokenForAccount(AccountCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .and()
                .body(credentials)
                .when()
                .post(app.config.loginAccountApiPath()).then().extract().path("accessToken").toString();
    }

    @Step("Delete account and get request status")
    public int deleteAccountAndGetStatusCode(String bearerToken) {
        return given()
                .spec(getBaseSpec())
                .and()
                .header("Authorization", bearerToken)
                .when().delete((app.config.editAccountApiPath())).then().extract().statusCode();
    }
}
