package com.ninja4testing.api.steps;

import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.models.PostRequest;
import com.ninja4testing.api.models.PostResponse;
import com.ninja4testing.api.utils.DataFactory;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreatePostSteps {

    private PostRequest requestBody;
    private Response response;
    private PostResponse responseBody;

    @Given("que preparo un payload de post válido para el userId {int}")
    public void que_preparo_un_payload(Integer userId) {
        requestBody = DataFactory.randomPost(userId);
    }

    @When("ejecuto envio la petición")
    public void ejecutar_post_posts() {
        response =
                given()
                        .baseUri(Config.BASE_URL)
                        .contentType(JSON)
                        .accept(JSON)
                        .log().all()
                        .body(requestBody)
                .when()
                        .post(Endpoints.POSTS)
                .then()
                        .log().all()
                        .extract().response();

        responseBody = response.as(PostResponse.class);
    }

    @Then("la respuesta debe ser 201 Created")
    public void validar_status() {
        assertEquals(201, response.getStatusCode(), "Status code esperado 201");
    }

    @Then("el cuerpo debe reflejar title, body y userId del request")
    public void validar_echo() {
        assertNotNull(responseBody, "Debe parsearse el response body");
        assertEquals(requestBody.getTitle(), responseBody.getTitle(), "Title debe coincidir");
        assertEquals(requestBody.getBody(),  responseBody.getBody(),  "Body debe coincidir");
        assertEquals(requestBody.getUserId(), responseBody.getUserId(), "userId debe coincidir");
    }

    @Then("la respuesta debe incluir un id asignado")
    public void validar_id_asignado() {
        assertNotNull(responseBody.getId(), "id no debe ser null");
        assertTrue(responseBody.getId() > 0, "id debe ser > 0");
    }
}
