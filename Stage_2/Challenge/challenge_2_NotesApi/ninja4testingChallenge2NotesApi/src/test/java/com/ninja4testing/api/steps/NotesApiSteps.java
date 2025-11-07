package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.BaseUserData;
import com.ninja4testing.api.models.LoginData;
import com.ninja4testing.api.models.UserLoginRequest;
import com.ninja4testing.api.models.UserLoginResponse;
import com.ninja4testing.api.models.UserRegisterRequest;
import com.ninja4testing.api.models.UserRegisterResponse;
import com.ninja4testing.api.utils.RunContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NotesApiSteps {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    private String generarEmail() {
        return "ninja4testing_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "@test.com";
    }

    @Given("un request body válido para registrar usuario con name, email y password")
    public void prepararPeticionCreacionUsuarioParaRegistrarse() {
        UserRegisterRequest userRegisterRequestBody = new UserRegisterRequest();
        userRegisterRequestBody.setName("Macarena");

        String email = generarEmail();
        userRegisterRequestBody.setEmail(email);
        userRegisterRequestBody.setPassword("Testing@@123");

        RunContext.setEmail(email);
        RunContext.setRequestBody(userRegisterRequestBody);
    }

    @When("realizo una petición POST a \\/users\\/register")
    public void creacionUsuario(){
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestBody())
                .when()
                        .post(Endpoints.USER_REGISTER)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("la respuesta tiene status_code {int}")
    public void statusCode(Integer status) {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(status, response.getStatusCode());
    }

    @Then("el cuerpo de la respuesta contiene los campos id y name")
    public void cuerpoRespuestaContieneCamposIdYName() {
        Response response = RunContext.getResponse();
        UserRegisterResponse registerResponse = response.as(UserRegisterResponse.class);

        BaseUserData data = registerResponse.getData();
        assertNotNull(data.getId(), "El campo id no debe estar vacío");
        assertNotNull(data.getName(), "El campo name no debe estar vacío");
    }

    @Given("un request body válido para login con email y password")
    public void prepararPeticionUsuarioParaLogin() {
        UserLoginRequest userLoginRequestBody = new UserLoginRequest();
        userLoginRequestBody.setEmail(RunContext.getEmail());
        userLoginRequestBody.setPassword("Testing@@123");
        RunContext.setRequestLoginBody(userLoginRequestBody);
    }

    @When("realizo una petición POST a \\/users\\/login")
    public void creacionUsuarioLogin() {
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestLoginBody())
                .when()
                        .post(Endpoints.USER_LOGIN)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 200) {
            UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);
            RunContext.setToken(userLoginResponse.getData().getToken());
        }
    }

    @Then("el cuerpo de la respuesta contiene un campo token")
    public void cuerpoRespuestaContieneCampoToken() {
        Response response = RunContext.getResponse();
        UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);

        LoginData data = userLoginResponse.getData();
        assertNotNull(data.getToken(), "El campo token no debe estar vacío");
    }

    @Given("que tengo un token de acceso válido obtenido del login")
    public void usuarioConAccesoTokenValido() {
        prepararPeticionUsuarioParaLogin();
        creacionUsuarioLogin();
    }

    @When("realizo una petición GET a \\/users\\/profile")
    public void obtenerProfileUsuario() {

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("x-auth-token", RunContext.getToken())
                        .log().all()
                .when()
                        .get(Endpoints.USER_PROFILE)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("el cuerpo de la respuesta contiene los campos id, name y email")
    public void cuerpoRespuestaContieneCamposIdNameEmail() {
        Response response = RunContext.getResponse();
        UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);

        BaseUserData data = userLoginResponse.getData();
        assertNotNull(data.getId(), "El campo id no debe estar vacío");
        assertNotNull(data.getName(), "El campo name no debe estar vacío");
        assertNotNull(data.getEmail(), "El campo email no debe estar vacío");
    }
}
