package org.uxcrowd.education.api_tests.filters;

import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.spi.AuthFilter;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class JwtAuthFilter implements AuthFilter {
    private final String username;
    private final String password;

    public JwtAuthFilter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Response filter(
            FilterableRequestSpecification requestSpec,
            FilterableResponseSpecification responseSpec,
            FilterContext ctx
    ) {
        Response response = given().auth().none().and().disableCsrf().and()//че за магическая строка,все починила )))
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .header("X-Xsrf-Token", 1)
                .cookie("XSRF-TOKEN", 1)
                .formParam("username", username)
                .formParam("password", password)
                .formParam("remember-me", "undefined")
                .formParam("submit", "Login")
                .when()
                .post("/api/authentication");

        Map<String, String> cookies = response.getCookies();
        String XSRFToken = cookies.get("XSRF-TOKEN");

        requestSpec.header("X-Xsrf-Token", XSRFToken);
        requestSpec.cookies(cookies);
        //requestSpec.contentType(ContentType.JSON);

        return ctx.next(requestSpec, responseSpec);
    }
}
