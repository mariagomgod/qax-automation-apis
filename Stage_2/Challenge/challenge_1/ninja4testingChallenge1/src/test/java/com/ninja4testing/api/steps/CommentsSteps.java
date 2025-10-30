package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.CommentsRequest;
import com.ninja4testing.api.models.CommentsResponse;
import com.ninja4testing.api.utils.RunContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommentsSteps {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    @Given("el endpoint \\/comments")
    public void endpointComments() {
        String endpoint = Endpoints.COMMENTS;
        RunContext.setEndpoint(endpoint);
    }

    @Given("uso el cuerpo de la solicitud por defecto")
    public void cuerpoSolicitud() {

        // Establecer el cuerpo de la solicitud con datos por defecto
        CommentsRequest requestBody = new CommentsRequest();
        requestBody.setPostId(1);
        requestBody.setName("Juan");
        requestBody.setEmail("juan@email.com");
        requestBody.setBody("Este es un comentario de prueba");
        RunContext.setRequestBody(requestBody);
    }

    @When("envío una petición POST")
    public void peticionPost() {
        //Enviar
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestBody())
                .when()
                        .post(RunContext.getEndpoint())
                .then()
                        .log().all()
                        .extract().response();

        // Establecer respuesta
        RunContext.setResponse(response);

        // Deserializar y guardar respuesta
        CommentsResponse responseBody = response.as(CommentsResponse.class);
        RunContext.setResponseBody(responseBody);
    }

    @Then("la API responde con status_code {int}")
    public void statusCode(int status) {
        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(status, response.getStatusCode());
    }

    @Then("la respuesta incluye un id generado y refleja exactamente los datos enviados")
    public void respuestaEsperadaconIdGenerado() {
        CommentsRequest requestBody = RunContext.getRequestBody();
        CommentsResponse responseBody = RunContext.getResponseBody();
        assertEquals(responseBody.getPostId(), requestBody.getPostId());
        assertEquals(responseBody.getName(), requestBody.getName());
        assertEquals(responseBody.getEmail(), requestBody.getEmail());
        assertEquals(responseBody.getBody(), requestBody.getBody());
        assertNotNull(responseBody.getId(), "El id no debe estar vacío");
    }

    @Given("indico el parámetro postId con valor {int}")
    public void postIdConValorUno(int postId) {
        RunContext.setPostId(postId);
    }

    @When("envío una petición GET")
    public void peticionGet() {
        //Enviar
        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .queryParam("postId", RunContext.getPostId())
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(RunContext.getRequestBody())
                .when()
                        .get(RunContext.getEndpoint())
                .then()
                        .log().all()
                        .extract().response();

        // Establecer la respuesta
        RunContext.setResponse(response);

        // Deserializar y guardar respuesta
        List<CommentsResponse> listResponseBody = response.as(new TypeRef<>(){}); // Es la manera para que Rest Assured
        // sepa deserializar colecciones de POJOs.
        RunContext.setListResponseBody(listResponseBody);
    }

    @Then("la respuesta contiene al menos un comentario")
    public void respuestaConAlMenosUnComentario() {
        List<CommentsResponse> listResponseBody = RunContext.getListResponseBody();
        assertFalse(listResponseBody.isEmpty(), "La respuesta no debe estar vacía");
    }

    @Then("cada comentario incluye id, nombre, email y cuerpo")
    public void comentarioContieneCamposEsperados() {
        List<CommentsResponse> listResponseBody = RunContext.getListResponseBody();

        for (CommentsResponse responseBody : listResponseBody) {
            assertNotNull(responseBody.getId(), "El comentario incluye id");
            assertNotNull(responseBody.getName(), "El comentario incluye nombre");
            assertNotNull(responseBody.getEmail(), "El comentario incluye email");
            assertNotNull(responseBody.getBody(), "El comentario incluye cuerpo");
        }
    }

    @Then("cada email contiene el carácter @")
    public void emailContieneArroba() {
        List<CommentsResponse> listResponseBody = RunContext.getListResponseBody();

        for (CommentsResponse responseBody : listResponseBody) {
            assertTrue(responseBody.getEmail().contains("@"));
        }
    }
}
