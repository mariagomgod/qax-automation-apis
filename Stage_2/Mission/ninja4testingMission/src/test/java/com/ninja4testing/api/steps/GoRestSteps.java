package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.CommentRequest;
import com.ninja4testing.api.models.CommentResponse;
import com.ninja4testing.api.models.PostRequest;
import com.ninja4testing.api.models.PostResponse;
import com.ninja4testing.api.models.UserRequest;
import com.ninja4testing.api.models.UserResponse;
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

public class GoRestSteps {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    private String generarEmail() {
        return "ninja4testing_" + UUID.randomUUID().toString().replace("-", "").substring(0, 10) + "@test.com";
    }

    @Given("un request body válido para crear usuario")
    public void prepararPeticionCreacionUsuario() {
        UserRequest requestBody = new UserRequest();
        requestBody.setName("Manuel");
        requestBody.setEmail(generarEmail());
        requestBody.setGender("male");
        requestBody.setStatus("active");
        RunContext.setUserRequestBody(requestBody);
    }

    @When("realizo una petición POST a \\/public\\/v2\\/users")
    public void crearUsuario() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getUserRequestBody())
                .when()
                        .post(Endpoints.USER)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 201) {
            UserResponse responseBody = response.as(UserResponse.class);
            RunContext.setUserId(responseBody.getId());
        }
    }

    @Then("la respuesta tiene status_code {int}")
    public void statusCode(int status) {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(status, response.getStatusCode());
    }

    @Then("validar que el usuario se ha creado correctamente")
    public void validarUsuarioCreadoCorrectamente() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .log().all()
                .when()
                        .get(Endpoints.USER_ID + RunContext.getUserId())
                .then()
                        .log().all()
                        .extract().response();

        UserResponse userResponse = response.as(UserResponse.class);
        UserRequest requestBody = RunContext.getUserRequestBody();

        assertEquals(requestBody.getName(), userResponse.getName(), "Los datos del campo name tienen que coincidir");
        assertEquals(requestBody.getEmail(), userResponse.getEmail(), "Los datos del campo email tienen que coincidir");
        assertEquals(requestBody.getGender(), userResponse.getGender(), "Los datos del campo gender tienen que coincidir");
        assertEquals(requestBody.getStatus(), userResponse.getStatus(), "Los datos del campo body tienen que coincidir");
    }

    @Given("un usuario")
    public void usuario() {
        prepararPeticionCreacionUsuario();
        crearUsuario();
    }

    @When("realizo una petición POST a \\/public\\/v2\\/posts")
    public void crearPublicacion() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        prepararUnRequestValidoDePublicacion();

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getPostRequestBody())
                .when()
                        .post(Endpoints.POST)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 201) {
            PostResponse postResponse = response.as(PostResponse.class);
            RunContext.setPostId(postResponse.getId());
        }
    }

    @Then("validar que el usuario puede crear publicaciones asociadas a su cuenta correctamente")
    public void validarCreacionPublicacionesAsociadasAlUsuario() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .log().all()
                .when()
                        .get(Endpoints.POST_ID + RunContext.getPostId())
                .then()
                        .log().all()
                        .extract().response();

        PostResponse postResponse = response.as(PostResponse.class);
        PostRequest postRequestBody = RunContext.getPostRequestBody();

        assertEquals(postRequestBody.getUser_id(), postResponse.getUser_id(), "Los datos del campo user_id tienen que coincidir");
        assertEquals(postRequestBody.getTitle(), postResponse.getTitle(), "Los datos del campo title tienen que coincidir");
        assertEquals(postRequestBody.getBody(), postResponse.getBody(), "Los datos del campo body tienen que coincidir");
    }

    @When("realizo una petición POST a \\/public\\/v2\\/comments")
    public void crearComentario() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        prepararUnRequestValidoDeComentario();

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getCommentRequestBody())
                .when()
                        .post(Endpoints.COMMENTS)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);

        if (response.statusCode() == 201) {
            CommentResponse commentResponse = response.as(CommentResponse.class);
            RunContext.setCommentId(commentResponse.getId());
        }
    }

    @Then("validar que un comentario se puede agregar a una publicación existente correctamente")
    public void validarQueComentarioSePuedeAgregarAUnaPublicacionExistente() {

        String token = "2be95871ad5690835d2958f97bded42c3d46c7b613f4d21593650eb4a6fd36dd";

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .header("Authorization", "Bearer " + token)
                        .log().all()
                .when()
                        .get(Endpoints.COMMENTS_ID + RunContext.getCommentId())
                .then()
                        .log().all()
                        .extract().response();

        CommentResponse commentResponse = response.as(CommentResponse.class);
        CommentRequest commentRequestBody = RunContext.getCommentRequestBody();

        assertEquals(commentRequestBody.getPost_id(), commentResponse.getPost_id(), "La respuesta del campo id deben coincidir");
        assertEquals(commentRequestBody.getName(), commentResponse.getName(), "La respuesta del campo name deben coincidir");
        assertEquals(commentRequestBody.getEmail(), commentResponse.getEmail(), "La respuesta del campo email deben coincidir");
        assertEquals(commentRequestBody.getBody(), commentResponse.getBody(), "La respuesta del campo body deben coincidir");

    }

    @When("preparo un request body válido de publicación para el usuario creado")
    public void prepararUnRequestValidoDePublicacion() {
        PostRequest postRequestBody = new PostRequest();
        postRequestBody.setUser_id(RunContext.getUserId());
        postRequestBody.setTitle("Mi primer post automatizado");
        postRequestBody.setBody("Contenido de prueba generado por la automatización.");
        RunContext.setPostRequestBody(postRequestBody);
    }

    @When("preparo un request body válido de comentario para la publicación creada")
    public void prepararUnRequestValidoDeComentario() {
        CommentRequest commentRequestBody = new CommentRequest();
        commentRequestBody.setPost_id(RunContext.getPostId());
        commentRequestBody.setName("Manuel");
        commentRequestBody.setEmail(generarEmail());
        commentRequestBody.setBody("Este es un comentario de prueba.");
        RunContext.setCommentRequestBody(commentRequestBody);
    }
}
