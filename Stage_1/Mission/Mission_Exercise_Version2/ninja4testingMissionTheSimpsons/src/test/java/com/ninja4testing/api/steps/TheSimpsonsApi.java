package com.ninja4testing.api.steps;

import com.ninja4testing.api.models.Character;
import com.ninja4testing.api.config.Config;
import com.ninja4testing.api.config.Endpoints;
import com.ninja4testing.api.utils.RunContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheSimpsonsApi {

    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = Config.BASE_URL;
    }

    @When("realizo una petición GET a \\/characters sin enviar el query param page")
    public void obtencionListaPersonajes() {

        Response response =
            given()
                .baseUri(Config.BASE_URL)
                .log().all()
            .when()
                .get(Endpoints.CHARACTERS)
            .then()
                .log().all()
                .extract().response();

        RunContext.setResponse(response);
    }

    @Then("la respuesta tiene status_code {int}")
    public void statusCode(int status) {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");
        assertEquals(status, response.getStatusCode());
    }

    @Then("el cuerpo del listado de personajes corresponde a la primera página por defecto")
    public void cuerpoListadoPersonajesCorrespondeAPrimeraPagina() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        List<Object> personajes = response.jsonPath().getList("results");
        assertNotNull(personajes, "La lista de personajes no debe estar vacía");
        assertEquals(20, personajes.size(), "La primera página debe devolver 20 personajes");
    }

    @When("realizo una petición GET a \\/characters con el query param {word}={int}")
    public void obtencionListaPersonajesConQueryParam(String param, int value) {

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .queryParam(param, value)
                        .log().all()
                .when()
                        .get(Endpoints.CHARACTERS)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("el cuerpo del listado de personajes incluye metadatos de paginación válidos")
    public void cuerpoListadoPersonajesIncluyeMetadatosPaginacionvalidos() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        List<Object> results = response.jsonPath().getList("results");
        assertNotNull(results, "results no debe estar vacío");

        Integer pages = response.jsonPath().getInt("pages");
        assertEquals(60, pages, "El total de páginas debe ser 60");

        int count = response.jsonPath().getInt("count");
        assertTrue(count > 0, "count debe ser mayor que 0");
    }

    @Then("cada personaje del listado cumple la estructura mínima y formatos requeridos")
    public void personajeCumpleConEstructuraYFormatosMinimosRequeridos() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        List<Character> personajes = response.jsonPath().getList("results", Character.class);
        assertNotNull(personajes, "El listado de personajes no debe ser null");
        assertFalse(personajes.isEmpty(), "El listado de personajes no debe estar vacío");

        for (Character personaje : personajes) {

            assertNotNull(personaje.getId(), "id no debe ser null");

            Integer age = personaje.getAge();
            if (age != null) {
                assertTrue(age >= 0, "age no debe ser negativa");
            }

            String birthdate = personaje.getBirthdate();
            if (birthdate != null && !birthdate.isBlank()) {
                assertTrue(birthdate.matches("\\d{4}-\\d{2}-\\d{2}"), "birthdate debe tener formato YYYY-MM-DD");
            }

            assertNotNull(personaje.getGender(), "gender no debe ser null");
            assertNotNull(personaje.getName(), "name no debe ser null");
            assertNotNull(personaje.getOccupation(), "occupation no debe ser null");

            String portraitPath = personaje.getPortrait_path();
            assertNotNull(portraitPath, "portrait_path no debe ser null");
            String expectedPath = "/character/" + personaje.getId() + ".webp";
            assertEquals(expectedPath, portraitPath, "portrait_path debe ser " + expectedPath);

            List<String> phrases = personaje.getPhrases();
            assertNotNull(phrases, "phrases no debe ser null");
        }
    }

    @When("realizo una petición GET a \\/characters\\/{int}")
    public void obtenerInformacionDeUnPersonaje(int id) {

        Response response =
                given()
                        .baseUri(Config.BASE_URL)
                        .log().all()
                .when()
                        .get(Endpoints.CHARACTERS_ID + id)
                .then()
                        .log().all()
                        .extract().response();

        RunContext.setResponse(response);
    }

    @Then("el cuerpo del detalle de personaje corresponde a Homer Simpson")
    public void cuerpoPersonajeCorrespondeAHomerSimpson() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        String name = response.jsonPath().getString("name");
        assertEquals("Homer Simpson", name);
    }

    @Then("el cuerpo de la respuesta indica que el personaje no existe")
    public void personajeInexistente() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        String message = response.jsonPath().getString("message");
        assertEquals("Character not found", message);
    }

    @Then("el listado de personajes es estable y está ordenado por id ascendente")
    public void listadoPersonajesOrdenadoPorIdAscendente() {

        Response response = RunContext.getResponse();
        assertNotNull(response, "La respuesta no debe estar vacía");

        List<Character> personajes = response.jsonPath().getList("results", Character.class);
        assertNotNull(personajes, "El listado de personajes no debe ser null");
        assertFalse(personajes.isEmpty(), "El listado de personajes no debe estar vacío");

        for (int i = 1; i < personajes.size(); i++) {

            Integer idAnterior = personajes.get(i - 1).getId();
            Integer idActual = personajes.get(i).getId();

            assertNotNull(idAnterior, "El id anterior no debe ser null");
            assertNotNull(idActual, "El id actual no debe ser null");

            assertTrue(idActual > idAnterior, "El listado no está ordenado por id ascendente. idAnterior=" + idAnterior + ", idActual=" + idActual);
        }
    }
}
