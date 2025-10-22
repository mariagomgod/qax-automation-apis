package com.tuempresa.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class UserSteps {

    Response response;

    @Given("la API está disponible")
    public void la_api_esta_disponible() {
        baseURI = "https://reqres.in/api";
    }

    @When("consulto el usuario con id {int}")
    public void consulto_el_usuario_con_id(Integer id) {
        response = when()
                    .get("/users/" + id);
    }

    @Then("el nombre del usuario debe ser {string}")
    public void el_nombre_del_usuario_debe_ser(String nombreEsperado) {
        response.then()
                .statusCode(200)
                .body("data.first_name", equalTo(nombreEsperado));
    }
}
