package com.tuempresa.steps;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    String apiKey = "reqres-free-v1"; // Definimos la API key como una variable

    @Before
    public void setup() {
        // Configuración centralizada
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSingleUserTest() {

        int userId = 2;
        String expectedUserName = "Janet";

        RestAssured
                // GIVEN (Dado que...) - Preparo mi petición
                .given()
                    .header("x-api-key", apiKey)

                // WHEN (Cuando...) - Envío la petición
                .when()
                    .get("/users/" + userId)

                // THEN (Entonces...) - Valido la respuesta
                .then()
                    .log().all() // Muestra toda la respuesta en la consola
                    .statusCode(200) // Verifica que el código de estado sea 200 (OK)
                    .body("data.id", equalTo(userId)) // Verifica que el id del usuario sea 2
                    .body("data.first_name", equalTo(expectedUserName)); // Verifica que el nombre sea "Janet"
    }

    @Test
    public void validateFieldIsNotNull() {

        RestAssured
                .when()
                    .get("/users/2")
                .then()
                    .statusCode(200)
                    //Verifica que el campo 'email' existe y no es nulo
                    .body("data.email", notNullValue());
    }

    @Test
    public void validateListSize() {

        int expectedUsersInPage = 6;

        RestAssured
                .when()
                    // Este endpoint devuelve una lista de usuarios
                    .get("/users?page=2")
                .then()
                    .statusCode(200)
                    // Verifica que la lista 'data' contenga 6 elementos
                    .body("data.size()", equalTo(expectedUsersInPage));
    }

    @Test
    public void getSingleUserAndValidateFields() {

        int userId = 2;
        String expectedUserName = "Janet";

        RestAssured
                .given() // Inicia la preparación de la petición
                    .header("X-Api-Key", apiKey)
                .when()
                    .get("/users/" + userId)
                .then()
                    .log().body()
                    .statusCode(200)
                    .body("data.id", equalTo(userId))
                    .body("data.first_name", equalTo(expectedUserName))
                    .body("data.email", notNullValue());
    }

    @Test
    public void getUserListAndValidateContent() {

        int expectedUsersInPage = 6;

        RestAssured
                .given() // Inicia la preparación de la petición
                    .header("X-Api-Key", apiKey)
                .when()
                    .get("/users?page=2")
                .then()
                    .log().body()
                    .statusCode(200)
                    .body("data.size()", equalTo(expectedUsersInPage))
                    .body("data.id", everyItem(notNullValue()));
    }

}
