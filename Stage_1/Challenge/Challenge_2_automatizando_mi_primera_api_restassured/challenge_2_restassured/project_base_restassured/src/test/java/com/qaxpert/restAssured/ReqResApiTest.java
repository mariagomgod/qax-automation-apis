package com.qaxpert.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqResApiTest {

    @Test
    public void pruebaListarGET() {
        RestAssured.baseURI = "https://reqres.in";

        given()
                .when()
                    .get("/api/users").
                then()
                    .statusCode(200)
                    .body("data[0].id", equalTo(1))
                    .body("data[0].first_name", notNullValue())
                    .body("data[0].last_name", notNullValue())
                    .body("data[0].email", containsString("@"));
    }

    @Test
    public void pruebaRegistrarPOST() {
        RestAssured.baseURI = "https://reqres.in";

        String json = """
            {
                "email": "george.bluth@reqres.in",
                "password": "test"
            }
            """;

        given()
                .body(json)
                .contentType(ContentType.JSON)
                .header("x-api-key","reqres-free-v1")
                .when()
                    .post("/api/register")
                .then()
                    .statusCode(200)
                    .body("id", notNullValue())
                    .body("token", notNullValue());
    }

    @Test
    public void pruebaLoginPOST() {
        RestAssured.baseURI = "https://reqres.in";

        String json = """
            {
                "email": "george.bluth@reqres.in",
                "password": "test"
            }
            """;

        given()
                .body(json)
                .contentType(ContentType.JSON)
                .header("x-api-key","reqres-free-v1")
                .when()
                .post("/api/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }
}
