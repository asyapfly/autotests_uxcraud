package org.uxcrowd.education.api_tests;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class APITests {
    @Test
    @Description("Отправка POST запроса на логин с корректными данными")
    public void login_UnderTester_UXCrowdTest (){

        Response response = given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .header("X-Xsrf-Token", 1)
                .cookie("XSRF-TOKEN", 1)
                .formParam("username", "tester")
                .formParam("password", "654321Test")
                .formParam("remember-me", "undefined")
                .formParam("submit", "Login")
                .when()
                .post("https://dev-t.uxcrowd.ru/api/authentication");

        response.then()
                .statusCode(200);

//        Headers headers = response.getHeaders();
//        String str = headers.getValues("Set-Cookie").get(0);
//        String XSRFToken = str.substring(11, 47);

        Map<String, String> cookies = response.getCookies();

        RestAssured.given()
                .cookies(cookies)
                .contentType(ContentType.JSON)
                .when()
                .get("https://dev-t.uxcrowd.ru/api/v2/tester/profile")
                .then()
                .statusCode(200);

//        Headers headersSec = response.getHeaders();
//        String strSec = headersSec.getValues("Set-Cookie").get(1);
        String XSRFToken = cookies.get("XSRF-TOKEN");

        File testData = new File("C:\\Projects\\Pets\\at_practics2023\\src\\test\\resources\\newTester.json");

        RestAssured.given()
                .cookies(cookies)
                .header("X-Xsrf-Token", XSRFToken)
                .contentType(ContentType.JSON)
                .body(testData)
                .when()
                .post("https://dev-t.uxcrowd.ru/api/v2/tester/profile")
                .then()
                .statusCode(200);

        File updatePayInfoData = new File("C:\\Projects\\Pets\\at_practics2023\\src\\test\\resources\\payInfoTesterData.json");

        RestAssured.given()
                .cookies(cookies)
                .header("X-Xsrf-Token", XSRFToken)
                .contentType(ContentType.JSON)
                .body(testData)
                .when()
                .post("https://dev-t.uxcrowd.ru/api/tester/pay-info")
                .then()
                .statusCode(200);

        //Auth.AuthResponse authResponse = response.getBody().as(Auth.AuthResponse.class);

        //sendMessageToReport(authResponse.accessToken);
    }
}
