import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class MiPrimerTest {
    @Test
    public void pruebaGET() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        given().
        when().
            get("/posts/1").
        then().
            statusCode(200).
            body("id", equalTo(1));
    }
}
