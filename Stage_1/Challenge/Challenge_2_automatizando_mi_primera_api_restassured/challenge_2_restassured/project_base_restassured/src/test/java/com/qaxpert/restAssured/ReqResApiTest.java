package com.qaxpert.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class ReqResApiTest {

    private static final String BASE_URI   = "https://reqres.in";
    private static final String API_KEY    = "reqres-free-v1";
    private static final String TEST_EMAIL = "george.bluth@reqres.in";
    private static final String TEST_PASSWORD = "test";

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void pruebaListarGET() {

        given()
                .when()
                    .get("/api/users")
                .then()
                    .statusCode(200)
                    .body("data[0].id", equalTo(1))
                    .body("data[0].first_name", notNullValue())
                    .body("data[0].last_name", notNullValue())
                    .body("data[0].email", containsString("@"));
    }

    @Test
    public void pruebaRegistrarPOST() {

        String json = """
            {
                "email": "%s",
                "password": "%s"
            }
            """.formatted(TEST_EMAIL, TEST_PASSWORD);

        given()
                .body(json)
                .contentType(ContentType.JSON)
                .header("x-api-key", API_KEY)
                .when()
                    .post("/api/register")
                .then()
                    .statusCode(200)
                    .body("id", notNullValue())
                    .body("token", notNullValue());
    }

    @Test
    public void pruebaLoginPOST() {

        String json = """
            {
                "email": "%s",
                "password": "%s"
            }
            """.formatted(TEST_EMAIL, TEST_PASSWORD);

        given()
                .body(json)
                .contentType(ContentType.JSON)
                .header("x-api-key", API_KEY)
                .when()
                    .post("/api/login")
                .then()
                    .statusCode(200)
                    .body("token", notNullValue());
    }
}
