package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.UserRequest;
import com.ninja4testing.api.utils.Utils;
import com.ninja4testing.api.utils.RunContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.ninja4testing.api.utils.Utils.generarPassword;
import static com.ninja4testing.api.utils.Utils.generarUsername;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSteps {

    private String userName;
    private String password;
    private String userId;
    private String token;
    private Response response;

    @Given("el usuario prepara un request body válido para crear usuario")
    public void prepararRequestBody() {
        userName = generarUsername();
        password = generarPassword();
        RunContext.setCreds(userName, password);
    }

    @When("realiza una petición POST a \\/Account\\/v1\\/User")
    public void crearUsuario() {
        userName = (userName != null) ? userName : RunContext.getUserName();
        password = (password != null) ? password : RunContext.getPassword();
        UserRequest userRequest = new UserRequest(userName, password);
        response = given().log().all()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post(Endpoints.USER);
        if (response.statusCode() == 201) {
            userId = response.jsonPath().getString("userID");
            RunContext.setUserId(userId);
        }
    }

    @Then("la respuesta tiene status 201 y contiene un userId")
    public void validarUsuarioCreado() {
        assertEquals(201, response.statusCode());
        assertNotNull(userId);
    }

    @Given("el usuario tiene credenciales válidas")
    public void usuarioConCredencialesValidas() {
        if (!RunContext.hasCreds()) {
            userName = Utils.generarUsername();
            password = Utils.generarPassword();
            RunContext.setCreds(userName, password);
        } else {
            userName = RunContext.getUserName();
            password = RunContext.getPassword();
        }
    }

    @When("realiza una petición POST a \\/Account\\/v1\\/GenerateToken")
    public void generarToken() {
        UserRequest userRequest = new UserRequest(userName, password);
        response = given().log().all()
                .baseUri(Config.BASE_URL)
                .contentType(ContentType.JSON)
                .body(userRequest)
                .when()
                .post(Endpoints.GENERAR_TOKEN);
        if (response.statusCode() == 200) {
            token = response.jsonPath().getString("token");
            RunContext.setToken(token);
        }
    }

    @Then("la respuesta tiene status 200 y contiene un token")
    public void validarTokenGenerado() {
        assertEquals(200, response.statusCode());
        assertNotNull(token);
    }

    @Given("el usuario tiene un token válido y un userId existente")
    public void usuarioConTokenYUserId() {
        // Garantiza estado ANTES del GET:
        if (!RunContext.hasUserId()) {
            // Si no hay userId, crea usuario con las credenciales persistidas (o genera nuevas si faltan)
            if (!RunContext.hasCreds()) {
                userName = Utils.generarUsername();
                password = Utils.generarPassword();
                RunContext.setCreds(userName, password);
            } else {
                hydrateFromContext();
            }
            crearUsuario();     // setea userId y lo persiste
            assertTrue(RunContext.hasUserId(), "No se pudo obtener userId");
        }
        if (!RunContext.hasToken()) {
            generarToken();     // setea token y lo persiste
            assertTrue(RunContext.hasToken(), "No se pudo obtener token");
        }
        hydrateFromContext();   // refresca locales
    }

    private void hydrateFromContext() {
        if (userName == null && RunContext.getUserName() != null) userName = RunContext.getUserName();
        if (password == null && RunContext.getPassword() != null) password = RunContext.getPassword();
        if (userId   == null && RunContext.getUserId()   != null) userId   = RunContext.getUserId();
        if (token    == null && RunContext.getToken()    != null) token    = RunContext.getToken();
    }

    @When("realiza una petición GET a \\/Account\\/v1\\/User\\/\\{userId} con el token en el header Authorization")
    public void obtenerInfoUsuario() {
        response = given().log().all()
                .baseUri(Config.BASE_URL)
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId);
    }

    @Then("la respuesta tiene status 200 y el userId corresponde al usuario creado")
    public void validarInfoUsuario() {
        assertEquals(200, response.statusCode());
        assertEquals(userId, response.jsonPath().getString("userId"));
    }
}
