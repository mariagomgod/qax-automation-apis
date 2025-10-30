package com.ninja4testing.api.steps;
import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.UserRequest;
import com.ninja4testing.api.models.UserResponse;
import com.ninja4testing.api.utils.RunContext;
import com.ninja4testing.api.utils.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserSteps {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    @Given("un request body inválido para crear usuario con userName vacío")
    public void requestBodyInvalidoUsernameVacio() {
        String passwordValida = Utils.generarPassword();
        UserRequest requestBodyUsernameVacio = new UserRequest("", passwordValida);
        RunContext.setRequestBody(requestBodyUsernameVacio);
    }

    @When("realizo una petición POST a \\/Account\\/v1\\/User")
    public void peticionPostCrearUsuario() {
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestBody())
                .when()
                        .post(Endpoints.USER)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 201) {
            UserResponse responseBody = response.as(UserResponse.class);
            RunContext.setUserId(responseBody.getUserID());
        }
    }

    @Then("la respuesta tiene status_code {int}")
    public void statusCode(int status) {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(status, response.getStatusCode());
    }

    @Then("el cuerpo de la respuesta contiene información de error")
    public void respuestaError() {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertNotNull(response.jsonPath().getString("message"));
    }

    @Given("un request body inválido para crear usuario con password inválida")
    public void requestBodyInvalidoPasswordInvalida() {
        String usernameValido = Utils.generarUsername();
        UserRequest requestBodyPasswordInvalida = new UserRequest(usernameValido, "1@@");
        RunContext.setRequestBody(requestBodyPasswordInvalida);
    }

    @Given("credenciales inválidas para generar un token")
    public void credencialesInvalidasParaGenerarToken() {
        String usernameValido = Utils.generarUsername();
        UserRequest requestBodyPasswordInvalida = new UserRequest(usernameValido, "1@@");
        RunContext.setRequestBody(requestBodyPasswordInvalida);
    }

    @When("realizo una petición POST a \\/Account\\/v1\\/GenerateToken")
    public void peticionPostToken() {
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestBody())
                .when()
                        .post(Endpoints.GENERAR_TOKEN)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 200) {
            String token = response.jsonPath().getString("token");
            RunContext.setToken(token);
        }
    }

    @Then("el cuerpo de la respuesta contiene el mensaje User authorization failed.")
    public void cuerpoRespuestaUserAuthFailed() {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals("User authorization failed.", response.jsonPath().getString("result"));

        /* Aserción equivalente
        response.then()
                .body("result", equalTo("User authorization failed."));*/
    }

    @When("realizo una petición GET a \\/Account\\/v1\\/User\\/\\{userId} sin el header Authorization")
    public void peticionGetSinHeaderAuth() {
        String username = Utils.generarUsername();
        String password = Utils.generarPassword();
        UserRequest requestBody = new UserRequest(username, password);
        RunContext.setRequestBody(requestBody);

        peticionPostCrearUsuario();

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .log().all()
                .when()
                        .get(Endpoints.USER_ID + RunContext.getUserId())
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("el cuerpo de la respuesta contiene el mensaje User not authorized!")
    public void cuerpoRespuestaUserNotAuthorized() {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals("User not authorized!", response.jsonPath().getString("message"));

        /* Aserción equivalente
        response.then()
                .body("message", equalTo("User not authorized!"));*/
    }

    @Then("el cuerpo de la respuesta contiene el code {string}")
    public void cuerpoRespuestaCode1200(String code) {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(code, response.jsonPath().getString("code"));

        /* Aserción equivalente
        response.then()
                .body("code", equalTo(code));*/
    }

    @When("realizo una petición GET a \\/Account\\/v1\\/User\\/\\{userId} con Bearer token inválido")
    public void peticionGetConBearerTokenInvalido() {
        String username = Utils.generarUsername();
        String password = Utils.generarPassword();
        UserRequest requestBody = new UserRequest(username, password);
        RunContext.setRequestBody(requestBody);

        peticionPostCrearUsuario();

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer asd")
                        .log().all()
                .when()
                        .get(Endpoints.USER_ID + RunContext.getUserId())
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @When("realizo una petición GET a \\/BookStore\\/v1\\/Books con credenciales válidas y un token activo")
    public void peticionGetCredencialesValidasYTokenActivo() {
        String username = Utils.generarUsername();
        String password = Utils.generarPassword();
        UserRequest requestBody = new UserRequest(username, password);
        RunContext.setRequestBody(requestBody);

        peticionPostCrearUsuario();
        peticionPostToken();

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .log().all()
                .when()
                        .get(Endpoints.BOOKS)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @When("guardo el ISBN del primer libro del resultado")
    public void guardarIsbnDelPrimerLibro() {
        String isbn = RunContext.getResponse().jsonPath().getString("books[0].isbn");
        RunContext.setIsbn(isbn);
    }

    @When("envío una petición POST a \\/BookStore\\/v1\\/Books para agregar ese ISBN al usuario")
    public void peticionPostAgregarIsbnAlUsuario() {
        String userId = RunContext.getUserId();
        String token = RunContext.getToken();
        String isbn = RunContext.getIsbn();
        String body = """
            {
                "userId": "%s",
                "collectionOfIsbns": [
                { "isbn": "%s" }
               ]
            }
            \s""".formatted(userId, isbn);

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", STR."Bearer " + token)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(body)
                .when()
                        .post(Endpoints.BOOKS)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("el cuerpo de la respuesta confirma que el libro fue agregado correctamente")
    public void confirmarLibroAgregadoCorrectamente() {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        response.then()
                .body("books[0].isbn", equalTo(RunContext.getIsbn()));
    }
}
