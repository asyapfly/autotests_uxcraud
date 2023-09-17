package org.uxcrowd.education.api_tests;

import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.uxcrowd.education.api_tests.filters.JwtAuthFilter;
import org.uxcrowd.education.config.ApplicationConfig;
import org.uxcrowd.education.pojo.DoneTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TesterAPITests {
    protected static ApplicationConfig config;

    protected static HashMap<String, String> cookiesMap;

    @BeforeAll
    public static void configInit() {
        config = new ApplicationConfig();
        RestAssured.baseURI = config.apiUrl;
        RestAssured.filters(
                new JwtAuthFilter(config.testerUsername, config.testerPassword),
                new AllureRestAssured(),
                new RequestLoggingFilter(),
                new ResponseLoggingFilter());
    }
    @Test
    @Description("Отправка POST запроса на логин с корректными данными")
    public void login_UnderTester_UXCrowdTest () {

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .header("X-Xsrf-Token", 1)
                .cookie("XSRF-TOKEN", 1)
                .formParam("username", "tester")
                .formParam("password", "654321Test")
                .formParam("remember-me", "undefined")
                .formParam("submit", "Login")
                .when()
                .post("/api/authentication");

        response.then()
                .statusCode(200);
    }

    @Test
    @Description("Отправка GET запроса на получение данных из профиля тестировщика")
    public void getTesterProfileInfo_UXCrowdTest () {
        RestAssured.given()
                //.cookies(cookies)
                .contentType(ContentType.JSON)
                .when()
                .get("/api/v2/tester/profile")
                .then()
                .statusCode(200);
    }

    @Test
    @Description("Отправка POST запроса на обновление данных профиля тестировщика")
    public void updateTesterProfileInfo_UXCrowdTest () throws IOException {
        try (InputStream testData = TesterAPITests.class.getClassLoader().getResourceAsStream("newTesterProfileData.json")) {

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(testData)
                    .when()
                    .post("https://dev-t.uxcrowd.ru/api/v2/tester/profile")
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    @Description("Отправка POST запроса на обновление платежных данных тестировщика")
    public void updateTesterPayInfoData_UXCrowdTest () throws IOException {
        try (InputStream updatePayInfoData = TesterAPITests.class.getClassLoader().getResourceAsStream("payInfoTesterData.json")) {

            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(updatePayInfoData)
                    .when()
                    .post("/api/tester/pay-info")
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    @Description("Отправка GET запроса на получение выполненных заданий тестировщика")
    public void getDoneTasks () throws IOException {
        ArrayList<DoneTask> doneTasks =  RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api/tester/done-tasks")
                .then()
                .statusCode(200)
                .extract()
                .body().as(new TypeRef<ArrayList<DoneTask>>() {});
    }
}
