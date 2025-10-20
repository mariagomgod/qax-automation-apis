package com.qaxpert.restAssured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TheSimpsonsApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://thesimpsonsapi.com";
    }

    @Test
    public void obtenerInformacionEsperadaPersonajesGET() {

        given()
                .when()
                    .get("/api/characters").
                then()
                    .statusCode(200)
                    .contentType(startsWith("application/json"))
                    .body("results[0].id", notNullValue(),
                            "results[0].age", notNullValue(),
                            "results[0].age", instanceOf(Number.class),
                            "results[0].birthdate", matchesPattern("^\\d{4}-\\d{2}-\\d{2}$"),
                            "results[0].gender", notNullValue(),
                            "results[0].gender", anyOf(equalTo("Male"), equalTo("Female")),
                            "results[0].name", notNullValue(),
                            "results[0].name", instanceOf(String.class),
                            "results[0].occupation", notNullValue(),
                            "results[0].occupation", instanceOf(String.class),
                            "results[0].portrait_path", matchesPattern("^/character/\\d+.webp$"),
                            "results[0].phrases", notNullValue(),
                            "results[0].phrases", instanceOf(java.util.List.class),
                            "results[0].status", notNullValue(),
                            "results[0].status", instanceOf(String.class),
                            "results[0].status", anyOf(equalTo("Deceased"), equalTo("Alive"))
                    );
    }

    @Test
    public void peticionInformacionPersonajesCuandoNumeroMayorAUltimaPaginaGET() {

        given()
                .queryParam("page", 61)
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("prev", nullValue());
    }

    @Test
    public void peticionInformacionPersonajesCuandoNumeroIgualAUltimaPaginaGET() {

        given()
                .queryParam("page", 60)
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("next", nullValue());
    }

    @Test
    public void peticionInformacionPersonajesCuandoPaginaEsCeroGET() {

        given()
                .queryParam("page", 0)
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("prev", nullValue());
    }

    @Test
    public void peticionInformacionPersonajesCuandoNumeroPaginaEsNegativoGET() {

        given()
                .queryParam("page", -1)
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("prev", nullValue());
    }

    @Test
    public void peticionInformacionPersonajesCuandoValorPaginaDistintoANumeroGET() {

        given()
                .queryParam("page", "a*!")
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("prev", nullValue());
    }

    @Test
    public void peticionInformacionPersonajesCuandoNumeroPaginaDemasiadoGrandeGET() {

        given()
                .queryParam("page", "9999999999")
                .when()
                .get("/api/characters").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("prev", nullValue(),
                        "next", nullValue()
                );
    }

    @Test
    public void listarUnPersonajePorIdDevuelveInformacionEsperadaGET() {

        given()
                .pathParam("id", 20)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("id", notNullValue(),
                        "age", anyOf(nullValue(), instanceOf(Number.class)),
                        "birthdate", anyOf(nullValue(), matchesPattern("^\\d{4}-\\d{2}-\\d{2}$")),
                        "gender", notNullValue(),
                        "gender", anyOf(equalTo("Male"), equalTo("Female")),
                        "name", notNullValue(),
                        "name", instanceOf(String.class),
                        "occupation", notNullValue(),
                        "occupation", instanceOf(String.class),
                        "portrait_path", matchesPattern("^/character/\\d+.webp$"),
                        "phrases", notNullValue(),
                        "phrases", instanceOf(java.util.List.class),
                        "status", notNullValue(),
                        "status", instanceOf(String.class),
                        "status", anyOf(equalTo("Deceased"), equalTo("Alive"))
                );
    }

    @Test
    public void listarUnPersonajeConIdInexistenteEInvalidoGET() {

        given()
                .pathParam("id", -1)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(404)
                .contentType(startsWith("application/json"))
                .body("$", anyOf(hasKey("error"), hasKey("message")));
    }

    @Test
    public void listarUnPersonajeConIdValidoEInexistenteMayorQueElMaximoAceptadoPorServidorGET() {

        given()
                .pathParam("id", 9999999999L)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(500)
                .contentType(startsWith("application/json"))
                .body("$", anyOf(hasKey("error"), hasKey("message")));
    }

    @Test
    public void listarUnPersonajeConIdExistenteExpresadoComoDecimalGET() {

        given()
                .pathParam("id", 1182.0)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("id", equalTo(1182));
    }

    @Test
    public void listarUnPersonajeConIdInexistenteDecimalGET() {

        given()
                .pathParam("id", 6.5)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(400)
                .contentType(startsWith("application/json"))
                .body("$", anyOf(hasKey("error"), hasKey("message")));
    }

    @Test
    public void listarUnPersonajeConIdDistintoANumeroGET() {

        given()
                .pathParam("id", "ab*")
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(400)
                .contentType(startsWith("application/json"))
                .body("$", anyOf(hasKey("error"), hasKey("message")));
    }

    @Test
    public void listarUnPersonajeFallecidoConIdExistenteGET() {

        given()
                .pathParam("id", 20)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("status", equalTo("Deceased"));
    }

    @Test
    public void listarUnPersonajeVivoConIdExistenteGET() {

        given()
                .pathParam("id", 19)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("status", equalTo("Alive"));
    }

    @Test
    public void listarUnPersonajeMasculinoConIdExistenteGET() {

        given()
                .pathParam("id", 17)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("gender", equalTo("Male"));
    }

    @Test
    public void listarUnPersonajeFemeninoConIdExistenteGET() {

        given()
                .pathParam("id", 10)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("gender", equalTo("Female"));
    }

    @Test
    public void listarUnPersonajeConIdExistenteSinEdadConocidaGET() {

        given()
                .pathParam("id", 10)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("age", nullValue());
    }

    @Test
    public void listarUnPersonajeConIdExistenteSinCumpleaniosConocidoGET() {

        given()
                .pathParam("id", 8)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("birthdate", nullValue());
    }

    @Test
    public void listarUnPersonajeDesempleadoConIdExistenteGET() {

        given()
                .pathParam("id", 2)
                .when()
                .get("/api/characters/{id}").
                then()
                .statusCode(200)
                .contentType(startsWith("application/json"))
                .body("occupation", equalTo("Unemployed"));
    }
}
