# Exercise 01: Proyecto completo para automatizaciones de APIs Rest Assured + Cucumber

En este ejercicio, vamos a construir un proyecto estructurado para APIs partiendo de un archivo de `.feature`.

## Gestión de Publicaciones y Comentarios

### Historia de Usuario
**Como** tester de APIs,  
**Quiero** poder crear publicaciones (posts), listar usuarios y obtener comentarios asociados a un usuario  
**Para** validar que la API funcione correctamente y que los datos sean consistentes.

Base_url: https://jsonplaceholder.typicode.com/

### Criterios de Aceptación
1. **Crear un post**:
    - POST `/posts` debe devolver HTTP 201.
    - La respuesta debe contener `id` asignado y reflejar los datos enviados (`title`, `body`, `userId`).

---
### 🛠️ Paso a paso: Crear un proyecto con Rest Assured + Cucumber

#### 1️⃣ Usar el proyecto detallado en el subdirectorio quick_task del Stage_2

- Agregar las dependencias en el `pom.xml`

```xml
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>${jackson.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-suite</artifactId>
            <version>${junit.platform.version}</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>${faker.version}</version>
            <scope>test</scope>
        </dependency>

```
#### Explicación de dependencias en el `pom.xml`

##### 1. `com.fasterxml.jackson.core:jackson-databind`
- **¿Para qué?** Serializa y deserializa JSON ↔ POJOs. Permite convertir objetos como `PostRequest`/`PostResponse` sin parsear strings manualmente.
- **¿Por qué `test`?** Se usa principalmente en pruebas (Rest Assured la utiliza para mapear el body). Si tu código de producción la requiere, cambia el scope a `compile`.
- **Ejemplo:**
  ```java
  ObjectMapper m = new ObjectMapper();
  PostResponse pr = m.readValue(response.asString(), PostResponse.class);
  ```
- **Notas:** Rest Assured ya incluye soporte para Jackson, pero agregarla explícitamente ayuda a evitar problemas de versiones.

##### 2. `io.cucumber:cucumber-java`
- **¿Para qué?** Proporciona anotaciones y glue de Cucumber en Java (`@Given`, `@When`, `@Then`).
- **¿Por qué `test`?** Solo se usa para ejecutar features/steps.
- **Ejemplo:**
  ```java
  @Given("que preparo un payload válido")
  public void preparar() { /* ... */ }
  ```

##### 3. `io.cucumber:cucumber-junit-platform-engine`
- **¿Para qué?** Permite correr Cucumber sobre JUnit 5 (JUnit Platform). Sin esto, JUnit no detecta tus features.
- **¿Por qué `test`?** Solo para ejecución de pruebas.
- **Síntoma si falta:** `NoTestsDiscoveredException` (JUnit no descubre escenarios).

##### 4. `org.junit.platform:junit-platform-suite`
- **¿Para qué?** Proporciona anotaciones de Suite de JUnit Platform (`@Suite`, `@SelectClasspathResource`, etc.). Útil si usas un runner minimal con `@Suite`.
- **¿Por qué `test`?** Solo afecta la ejecución de los tests.
- **Alternativa:** Puedes omitir esta dependencia y configurar el runner con un archivo `src/test/resources/junit-platform.properties`:
  ```
  cucumber.glue=com.ninja4testing.api.steps
  cucumber.plugin=pretty, summary, html:target/cucumber-report.html, json:target/cucumber.json
  ```

##### 5. `com.github.javafaker:javafaker`
- **¿Para qué?** Genera datos falsos realistas (títulos, párrafos, nombres) para tus payloads.
- **¿Por qué `test`?** Solo para tests, no es parte del código de negocio.
- **Ejemplo:**
  ```java
  Faker faker = new Faker();
  String title = faker.book().title();
  ```
### Crear la siguiente estructura


```bash 
src
├── main
└── test
    ├── java
    │   └── com/ninja4testing/api
    │       ├── config
    │       │   ├── Config.java
    │       │   └── Endpoints.java
    │       ├── models
    │       │   ├── PostRequest.java
    │       │   └── PostResponse.java
    │       ├── runners
    │       │   └── CucumberTestRunner.java
    │       ├── steps
    │       │   └── CreatePostSteps.java
    │       └── utils
    │           └── DataFactory.java
    └── resources
        └── features
            └── create_post.feature

```

#### Estructura y roles de carpetas y clases

##### `src/main`
Reservado para el código de aplicación. En este proyecto de pruebas, permanece vacío porque toda la lógica está en `src/test`.

##### `src/test`
Contiene todo lo relacionado con testing: clases Java de soporte (configuración, modelos, steps, utilidades), el runner y los features en Gherkin.

---

### Vamos a escribir codigo


1. Lo primero es empezar por el libreto, por el caso de prueba el `src/test/resources/features/create_post.feature`

- **create_post.feature**  
  Especificación funcional en Gherkin. Describe el escenario “Crear un post” con pasos Given/When/Then. Es la fuente de verdad legible para negocio y QA; enlaza directamente con los métodos de `CreatePostSteps`.


```gherkin

Feature: Crear Post en JSONPlaceholder
  Como tester de APIs
  Quiero crear un post con Rest Assured
  Para validar que el servicio devuelve 201 y refleja los datos enviados

  @create_post
  Scenario: Crear un post exitosamente
    Given que preparo un payload de post válido para el userId 10
    When ejecuto envio la petición
    Then la respuesta debe ser 201 Created
    And el cuerpo debe reflejar title, body y userId del request
    And la respuesta debe incluir un id asignado
```

---

2. Vamos a crear los steps. `src/test/java/com/ninja4testing/api/steps/CreatePostSteps.java`

- **CreatePostSteps.java**  
  Implementa los métodos anotados con `@Given`, `@When` y `@Then` para cada paso del escenario.
    - Usa `DataFactory` para crear el payload.
    - Ejecuta la petición con Rest Assured usando la base URL y endpoint definidos.
    - Valida el status code, los datos reflejados y la presencia del `id` en la respuesta.
```java

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
```


---

3. Vamos a realizar la configuración de la baseUrl y los endpoint.


##### Paquete `com.ninja4testing.api.config`

- **Config.java**  
  Fuente única de configuración global del test. Guarda la URL base del servicio. Cambia aquí para apuntar a otro entorno sin tocar el resto del proyecto.

- **Endpoints.java**  
  Catálogo de rutas (paths) de la API. Define nombres claros para cada recurso (por ejemplo, `POSTS`). Evita “strings mágicos” y errores de tipeo.

- `src/test/java/com/ninja4testing/api/config/Config.java`

```java
public class Config {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com";

}
```

- `src/test/java/com/ninja4testing/api/config/Endpoints.java`

```java
public class Endpoints {
    private Endpoints() {}
    public static final String POSTS = "/posts";
}
```
----

3. Vamos a modelar los `request`y los `response`

##### Paquete `com.ninja4testing.api.models`

- **PostRequest.java**  
  DTO del cuerpo que envías al crear un post. Modela los campos esperados por la API (`title`, `body`, `userId`). Facilita la serialización automática a JSON.

- **PostResponse.java**  
  DTO del cuerpo que recibes al crear un post. Modela los campos que devuelve la API (`id`, `title`, `body`, `userId`). Permite validar con getters en lugar de parsear JSON a mano.

> **Ventaja:** Usar modelos Java permite validaciones más expresivas y evita manipular JSON como texto.

- `src/test/java/com/ninja4testing/api/models/PostRequest.java`

```java
public class PostRequest {

    private String title;
    private String body;
    private Integer userId;

    public PostRequest() {}
    public PostRequest(String title, String body, Integer userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public Integer getUserId() { return userId; }

    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
    public void setUserId(Integer userId) { this.userId = userId; }
}
```

- `src/test/java/com/ninja4testing/api/models/PostResponse.java`

```java
package com.ninja4testing.api.models;

public class PostResponse {
    private Integer id;
    private String title;
    private String body;
    private Integer userId;

    public Integer getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public Integer getUserId() { return userId; }

}
```

---
4. La clase para realizar la generacion de data `src/test/java/com/ninja4testing/api/utils/DataFactory.java`

##### Paquete `com.ninja4testing.api.utils`

Contiene clases y métodos reutilizables que ayudan a simplificar y centralizar tareas comunes dentro del framework de automatización. Su propósito principal es evitar la duplicación de código y mantener los steps y tests enfocados en la lógica de negocio.

**Ejemplos de utilidades:**
- Generación de datos de prueba (por ejemplo, con Faker).
- Métodos auxiliares para formatear fechas, leer archivos, construir objetos, etc.
- Funciones de soporte para validaciones o transformaciones.

- **DataFactory.java**  
  Generador de datos de prueba. Crea instancias válidas de `PostRequest` (dinámicas o estáticas) para que los steps no mezclen lógica de datos con la ejecución de la request.

```java
import com.ninja4testing.api.models.PostRequest;

public class DataFactory {

    private static final Faker FAKER = new Faker();

    public static PostRequest randomPost(Integer userId) {
        String title = FAKER.book().title();
        String body  = FAKER.lorem().sentence(10);
        return new PostRequest(title, body, userId);
    }
}
```
> En esta clase importamos el modelo de PostRequest

---

5. Ahora es el turno de crear el disparador.

- **CucumberTestRunner.java**  
  Puente de ejecución con JUnit Platform. Indica dónde buscar features, qué engine usar (Cucumber) y qué plugins de reporte activar. Debe cumplir el patrón de nombres que Surefire descubre.

```java
import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.ninja4testing.api.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME,
        value = "pretty, summary, html:target/cucumber-report.html, json:target/cucumber.json")
public class CreatePostTestRunner { }

```

- `import org.junit.platform.suite.api.*;`  
  Importa todas las anotaciones necesarias de JUnit para definir una suite de pruebas, que será el contenedor encargado de ejecutar Cucumber.

- `import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;` y `PLUGIN_PROPERTY_NAME;`  
  Importan constantes de Cucumber para configurar la suite usando nombres de propiedades estándar, evitando errores por escribirlos manualmente.

- `@Suite`  
  Marca la clase como una suite de pruebas reconocida por JUnit.

- `@IncludeEngines("cucumber")`  
  Indica a JUnit que utilice el motor de ejecución de Cucumber.

- `@SelectClasspathResource("features")`  
  Especifica la ubicación de los archivos `.feature` dentro de `resources/features`.

- `@ConfigurationParameter` (con `GLUE_PROPERTY_NAME`)  
  Define el paquete `com.ninja4testing.api.steps` como ubicación de las clases que implementan los pasos de los escenarios.

- Otro `@ConfigurationParameter` (con `PLUGIN_PROPERTY_NAME`)  
  Configura los plugins de reporte: salida legible (`pretty`, `summary`) y reportes en HTML y JSON en la carpeta `target/`.

- `public class CreatePostTestRunner { }`  
  La clase se deja vacía intencionalmente; toda la configuración se realiza mediante anotaciones.