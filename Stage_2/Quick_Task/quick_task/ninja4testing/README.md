# Quick_Task – Uso de clases en Java, estructura de proyecto

En este quick task vamos a aprender a instalar las herramientas básicas, crear un proyecto Java desde cero, escribir tu primera prueba automatizada de una API usando Rest-Assured y entender los conceptos clave de pruebas de APIs.

### **Parte 1: Introducción a Rest-Assured**

#### **¿Qué es Rest-Assured y para qué sirve?**

Imagina que las aplicaciones web son como un restaurante. Tú, como cliente, interactúas con el mesero (la **interfaz de usuario** o **UI**), pero el mesero se comunica con la cocina (el **backend** o la **API**) para que preparen tu comida. Probar la UI es como observar todo el servicio desde tu mesa. En cambio, Rest-Assured es como tener un intercomunicador 🎙️ directo a la cocina. Te permite dar órdenes y verificar que lo que sale de la cocina es exactamente lo que pediste, sin tener que pasar por el mesero. Es más rápido, directo y preciso.

**Rest-Assured** es una librería de Java de código abierto diseñada específicamente para simplificar las pruebas de servicios web **RESTful**. Permite escribir pruebas de API potentes, legibles y fáciles de mantener. En el campo de las pruebas automatizadas, su relevancia es enorme porque:
* **Velocidad y Estabilidad:** Las pruebas de API son mucho más rápidas y menos frágiles que las pruebas de UI.
* **Pruebas Tempranas:** Permiten a los equipos de QA encontrar defectos en la lógica de negocio mucho antes de que se construya la interfaz de usuario.
* **Base de la Pirámide:** Son un pilar fundamental en la pirámide de automatización de pruebas, proveyendo una cobertura amplia y robusta en la capa de servicios.

#### **Conceptos Clave: La Sintaxis Gherkin (Given-When-Then)**

La estructura de Rest-Assured sigue una narrativa muy natural, como si contaras una historia. Piénsalo como pedir un café ☕:
1.  **GIVEN (Dado que...)**: Preparas todo lo necesario para tu pedido. *Ej: "Dado que tengo mi tarjeta de crédito y estoy en la app de la cafetería".*
2.  **WHEN (Cuando...)**: Realizas la acción principal. *Ej: "Cuando pido un capuchino grande".*
3.  **THEN (Entonces...)**: Verificas el resultado. *Ej: "Entonces deben cobrarme el precio correcto y mi pedido debe aparecer como 'en preparación'".*

Rest-Assured utiliza una sintaxis inspirada en **BDD (Behavior-Driven Development)** que hace las pruebas muy legibles.
* `given()`: Se usa para preparar la petición. Aquí se configuran los **headers** (metadatos de la petición), **query parameters** (filtros en la URL) y el **body** (los datos que envías, por ejemplo, en un POST).
* `when()`: Contiene el método HTTP (`get()`, `post()`, `put()`, etc.) y el *endpoint* (la dirección específica del recurso al que apuntas). Es la acción que se ejecuta.
* `then()`: Se usa para validar la respuesta del servidor. Aquí hacemos **aserciones** (validaciones) sobre el **status code** (ej: 200 para OK, 404 para No Encontrado), el contenido del **body** de la respuesta y los **headers** de la respuesta.

---

### **Parte 2: Preparando el Taller (Instalación de Herramientas)**

#### **Paso 2.1: Instalar el JDK (La Caja de Herramientas 🧰)**

1.  **Descarga:** Ve al sitio oficial de **[Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)** y descarga el instalador.
2. Selecciona la versión **Java 11**
2.  **Instala:** Ejecuta el archivo. En la pantalla "Custom Setup", es **muy importante** que habilites la opción `Set JAVA_HOME variable`.
3.  **Verifica:** Abre el **Símbolo del sistema** (`cmd`) y escribe `java -version`. Deberías ver la versión que instalaste.

Ejemplo:

```
C:\Users\estudiante>java -version
java version "21.0.7" 2025-04-15 LTS
Java(TM) SE Runtime Environment (build 21.0.7+8-LTS-245)
Java HotSpot(TM) 64-Bit Server VM (build 21.0.7+8-LTS-245, mixed mode, sharing)
```

#### **Paso 2.2: Instalar IntelliJ IDEA (El Banco de Trabajo 🛠️)**

1.  **Descarga:** Ve a la página de **[Descargar IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/)** y descarga el instalador `.exe` de la versión **Community**.
2.  **Instala:** Ejecuta el archivo. Puedes seguir el asistente con las opciones por defecto.

---

### **Parte 3: Creando Nuestro Primer Proyecto**

#### **Paso 3.1: Crear el Proyecto Maven (La Caja Organizadora 📦)**

1.  Abre IntelliJ y haz clic en **"New Project"**.
2.  Nómbralo `RestAssuredWarmup2`.
3.  Asegúrate de que el "Build system" sea **Maven** y el JDK sea la versión que descargaste.
4.  En "Advanced Settings", define el **GroupId** como `com.ninja4testing`.
5.  Haz clic en **"Create"**.

El **GroupId** y el **ArtifactId** son las dos piezas principales de información que Maven usa para encontrar una librería.

***

### GroupId: El Creador del Proyecto

Piensa en el **GroupId** como el identificador único del **creador o la organización** que hizo el proyecto. Responde a la pregunta: **¿Quién hizo esta librería?**

* Generalmente se escribe como el dominio web de la organización, pero al revés.
* **Ejemplo:** La organización Apache Maven usa el GroupId `org.apache.maven`.

***

### ArtifactId: El Nombre del Proyecto

El **ArtifactId** es simplemente el **nombre del proyecto o librería** que quieres usar. Responde a la pregunta: **¿Qué librería es esta?**

* Es el nombre específico del archivo `.jar` que se descargará.
* **Ejemplo:** Dentro del grupo `org.apache.maven`, uno de sus proyectos se llama `maven-core`.

***

### ¿Cómo funcionan juntos?

Una misma organización (GroupId) puede tener muchos proyectos (ArtifactId). La combinación de ambos crea un identificador único.

Para Rest Assured, la dependencia es:

* **GroupId:** `io.rest-assured` (El creador es el proyecto "io.rest-assured").
* **ArtifactId:** `rest-assured` (El nombre del proyecto que queremos es "rest-assured").

Así, Maven sabe que debe buscar el proyecto llamado **`rest-assured`** que fue creado por la organización **`io.rest-assured`**.

Tendremos entonces un proyecto con la siguiente estructura:

A continuación se detalla el propósito de cada carpeta y archivo principal en un proyecto estándar de Maven.

### Estructura del Proyecto

* **`RestAssuredWarmup2` (Directorio Raíz)**
  Es la carpeta principal que contiene todos los archivos y directorios del proyecto.

* **`.idea` (Directorio de IntelliJ IDEA)**
  Contiene archivos de configuración específicos del IDE IntelliJ IDEA para este proyecto. No debe incluirse en el control de versiones (Git).

* **`.gitignore` (Archivo de Git)**
  Especifica qué archivos y carpetas deben ser ignorados intencionalmente por el sistema de control de versiones Git.

* **`.mvn` (Directorio de Maven Wrapper)**
  Contiene el "Maven Wrapper", una herramienta que asegura que todos los desarrolladores usen la misma versión de Maven para construir el proyecto, evitando inconsistencias.

* **`src` (Directorio de Código Fuente)**
  Es el directorio estándar que contiene todo el código fuente del proyecto, dividido en `main` y `test`.

* **`src/main/java` (Código Fuente de la Aplicación)**
  Aquí se encuentra el código fuente de la aplicación principal, escrito en Java. En un proyecto de solo pruebas, esta carpeta puede estar vacía.

* **`src/main/resources` (Recursos de la Aplicación)**
  Contiene archivos que no son código pero que la aplicación necesita, como archivos de configuración (`.properties`, `.yml`), imágenes, etc.

* **`src/test/java` (Código Fuente de Pruebas)**
  Aquí se encuentra el código fuente de las pruebas automatizadas. **En esta carpeta crearás tus clases de prueba.**

* **`pom.xml` (Project Object Model)**
  Es el archivo central de configuración de Maven. Define las **dependencias** (librerías externas), los **plugins** y la información necesaria para construir el proyecto.

* **`External Libraries` (Librerías Externas)**
  Es una vista lógica creada por IntelliJ para mostrar todas las librerías (`.jar`) que Maven ha descargado y añadido a tu proyecto, basándose en lo que definiste en el `pom.xml`.

* **`Scratches and Consoles` (Archivos Temporales de IDE)**
  Una característica de IntelliJ que te permite crear archivos temporales ("scratches") para escribir y probar fragmentos de código rápidamente sin tener que guardarlos formalmente en la estructura del proyecto.

#### **Paso 3.2: Añadir Dependencias (La Lista de Compras 📜)**

1.  Abre el archivo `pom.xml`, el cual deberá tener una estructura como la siguiente:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ninja4testing</groupId>
    <artifactId>RestAssuredWarmup2</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

</project>
```

2.  Pega el siguiente bloque de código antes de la etiqueta `</project>`:
    ```xml
    <dependencies>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.4.0</version>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.13.2</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    ```
3.  Haz clic en el ícono de Maven con flechas de recarga que aparece en la esquina superior derecha para que se descarguen las librerías.

### **Parte 4: Nuestra Primera Línea de Código**

#### **Paso 4.1: Crear el Archivo de Prueba 🧪**

1.  En el panel del proyecto, navega a `src` -> `test` -> `java`.
2.  Haz clic derecho sobre la carpeta `java` y selecciona `New` -> `Java Class`.
3.  Nombra la clase `FirstApiTest`.


#### **Paso 4.2: Escribir el Código de Prueba ✍️**

Pega el siguiente código en tu nuevo archivo:

```java
import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    @Test
    public void getSingleUserTest() {
        // GIVEN (Dado que...) - Preparo mi petición
        RestAssured.baseURI = "https://reqres.in/api";

        // WHEN (Cuando...) - Envío la petición
        RestAssured
            .when()
                .get("/users/2")
            
            // THEN (Entonces...) - Valido la respuesta
            .then()
                .log().all() // Muestra toda la respuesta en la consola
                .assertThat().statusCode(200) // Verifica que el código de estado sea 200 (OK)
                .assertThat().body("data.id", equalTo(2)) // Verifica que el id del usuario sea 2
                .assertThat().body("data.first_name", equalTo("Janet")); // Verifica que el nombre sea "Janet"
    }
}
```

#### Paso 4.3: Ejecutar la Prueba ▶️

1.  Al lado de la línea `public void getSingleUserTest() {`, verás un pequeño ícono de "play" verde.
2.  Haz clic en ese ícono y selecciona la opción **"Run 'getSingleUserTest()'"**.

Verás cómo se abre una consola en la parte inferior de IntelliJ. Se ejecutará la prueba y, si todo salió bien, verás una **barra de color verde** que indica que la prueba pasó exitosamente. ¡La consola también te mostrará la respuesta completa de la API!

### ¿Qué Hace el Código en General? 🧪

Este es un script de prueba automatizada que utiliza la librería **Rest-Assured** para verificar el funcionamiento de una API. Específicamente, hace lo siguiente:

1.  **Prepara** una petición a un servidor público (reqres.in).

2.  **Ejecuta** la petición para obtener la información de un usuario específico (el que tiene el ID 2).

3.  **Verifica** que la respuesta del servidor sea la correcta, tanto en el código de estado como en el contenido.


### Desglose del Código

Vamos a revisar el script en partes para que entiendas la función de cada línea.

#### **1\. Las Importaciones (Imports)**

```java
import io.restassured.RestAssured;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
```

Estas líneas son como decirle a nuestro código: "vamos a necesitar estas cajas de herramientas".

*   `io.restassured.RestAssured`: Importa la librería principal de **Rest-Assured**, que nos da los comandos para hacer las peticiones a la API.

*   `org.junit.Test`: Importa la anotación @Test de la librería **JUnit**. Esta "etiqueta" le dice a Java que el método que viene a continuación es una prueba que se puede ejecutar.

*   `static org.hamcrest.Matchers.\*`: Importa los "Matchers" de la librería **Hamcrest**. Son métodos que nos ayudan a hacer las validaciones de una forma muy legible, como equalTo() (es igual a).


#### **2\. La Clase y el Método de Prueba**

```java
public class FirstApiTest {

    @Test
    public void getSingleUserTest() {
        // ... el código de la prueba va aquí ...
    }
}
```

*  `public class FirstApiTest`: Simplemente declara una clase, que es un contenedor para nuestro código de prueba.

*   `@Test`: Esta es la etiqueta mágica de JUnit. Marca el método `getSingleUserTest()` como un caso de prueba ejecutable. Sin esto, el código no se correría como una prueba.

*   `public void getSingleUserTest()`: Es el método que contiene los pasos de nuestra prueba.


#### **3\. La Estructura GIVEN - WHEN - THEN**

Esta es la parte más importante y sigue un patrón que hace que las pruebas sean muy fáciles de leer.

##### **GIVEN (La Preparación 📝)**

```java
// GIVEN (Dado que...) - Preparo mi petición
RestAssured.baseURI = "https://reqres.in/api";   
```

*   Aquí preparamos todo lo necesario antes de hacer la llamada. En este caso, establecemos la **URL base** del servidor al que vamos a apuntar. A partir de ahora, todas las peticiones se harán a esta dirección.


##### **WHEN (La Acción 🚀)**

```java
// WHEN (Cuando...) - Envío la petición
RestAssured
    .when()
        .get("/users/2")  
```

*   Esta es la acción principal. Le estamos diciendo a Rest-Assured: "Cuando yo haga una petición **GET** a la URL `/users/2`...".

*   Rest-Assured combinará la `baseURI` con esta parte, por lo que la petición completa se hará a `https://reqres.in/api/users/2`.


##### **THEN (La Verificación ✅)**

```java
// THEN (Entonces...) - Valido la respuesta
    .then()
        .log().all()
        .assertThat().statusCode(200)
        .assertThat().body("data.id", equalTo(2))
        .assertThat().body("data.first_name", equalTo("Janet")); 
```

*   Aquí es donde comprobamos si la respuesta del servidor fue la que esperábamos.

*   `.log().all()`: Es un ayudante muy útil. Imprime en la consola **toda la respuesta** del servidor (código, cabeceras, cuerpo), lo que es genial para depurar.

*   `.assertThat().statusCode(200)`: Verifica que el **código de estado** de la respuesta sea **200**, que en HTTP significa "OK" o que todo salió bien.

*   `.assertThat().body("data.id", equalTo(2))`: Revisa el **cuerpo (body)** de la respuesta. Busca un objeto `data`, dentro de él un campo `id`, y verifica que su valor sea **igual a 2**.

*   `.assertThat().body("data.first\_name", equalTo("Janet"))`: De la misma forma, busca dentro de `data` el campo `first\_name` y verifica que su valor sea **igual a "Janet"**.


Si todas estas verificaciones son correctas, la prueba pasará y se marcará en verde. Si alguna falla, la prueba se detendrá y se marcará en rojo.

Ahora vamos a revisar unas mejoras en el código anterior:

### Centraliza la Configuración de la URL
**El Problema:** La URL base (RestAssured.baseURI) está definida dentro del mismo método de prueba. Si tuvieras 10 pruebas, tendrías que repetir esa línea 10 veces. Si la URL cambia, tendrías que modificarla en 10 lugares distintos.

**La Solución:** Usa un método con la anotación @Before. Este método se ejecutará automáticamente antes de cada prueba en la clase, asegurando que la configuración esté lista sin tener que repetirla.

Antes:
```java
@Test
public void getSingleUserTest() {
    RestAssured.baseURI = "https://reqres.in/api";
    // ... resto del código
}
```

Ahora:

```java
import org.junit.Before; // Importante añadir esta línea

public class FirstApiTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSingleUserTest() {
        // La baseURI ya está configurada, no es necesario ponerla aquí.
        RestAssured.when().get("/users/2").then()...
    }
}
```

### Mejora la Legibilidad de las Aserciones
**El Problema:** El código repite .assertThat() en cada validación. Aunque funciona, es un poco redundante y se puede escribir de forma más fluida.

**La Solución:** La sintaxis de Rest-Assured te permite encadenar las validaciones directamente después del then().


Antes:

```Java
.then()
    .log().all()
    .assertThat().statusCode(200)
    .assertThat().body("data.id", equalTo(2))
    .assertThat().body("data.first_name", equalTo("Janet"));
```

Ahora:

```Java
.then()
    .log().all()
    .statusCode(200) // Sin .assertThat()
    .body("data.id", equalTo(2)) // Sin .assertThat()
    .body("data.first_name", equalTo("Janet")); // Sin .assertThat()
```

### Elimina los "Datos Mágicos"
**El Problema:** Valores como /users/2, 2 y "Janet" están escritos directamente en la prueba. A estos se les conoce como "datos mágicos" porque no tienen un contexto claro y si necesitas cambiarlos, tienes que buscarlos dentro del código.

**La Solución:** Declara estos valores en variables al inicio de la prueba. Esto hace el código más legible y fácil de mantener.

Antes:

```Java
.when()
    .get("/users/2")
.then()
    .statusCode(200)
    .body("data.id", equalTo(2))
    .body("data.first_name", equalTo("Janet"));
```

Ahora:

```Java
@Test
public void getSingleUserTest() {
    int userId = 2;
    String userName = "Janet";

    RestAssured
        .when()
            .get("/users/" + userId) // Usamos la variable
        .then()
            .statusCode(200)
            .body("data.id", equalTo(userId)) // Usamos la variable
            .body("data.first_name", equalTo(userName)); // Usamos la variable
}
```

### Resultado Final: Código Optimizado
Aplicando todas las mejoras, tu clase de prueba ahora se vería así, mucho más limpia y profesional:

```Java
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    @Before
    public void setup() {
        // 1. Configuración centralizada
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getSingleUserTest() {
        // 3. Variables para datos clave
        int userId = 2;
        String expectedUserName = "Janet";

        // WHEN: Envío la petición
        RestAssured
            .when()
                .get("/users/" + userId)
            // THEN: Valido la respuesta
            .then()
                .log().all()
                // 2. Aserciones más legibles
                .statusCode(200)
                .body("data.id", equalTo(userId))
                .body("data.first_name", equalTo(expectedUserName));
    }
}
```

### **Parte 5: Validaciones Comunes en Rest-Assured**
Una vez que enviamos una petición, la parte más importante es validar la respuesta. A continuación, veremos las validaciones más comunes que puedes realizar con Rest-Assured.

#### **1. Validar el Código de Estado (Status Code)**

**Propósito:** Es la primera y más fundamental validación. Nos permite saber si la petición fue entendida y procesada correctamente por el servidor.

Ejemplo en Código:

```Java
@Test
public void validateStatusCode() {
    RestAssured
        .when()
            .get("/users/2")
        .then()
            .log().status() // Imprime solo la línea del status
            .statusCode(200); // ✅ Verifica que el código sea 200 (OK)
}
```

**Códigos comunes:** `200` (OK), `201` (Creado), `400` (Petición incorrecta), `404` (No encontrado), `500` (Error del servidor).

#### **2. Validar un Campo Específico en el Cuerpo (Body)**

**Propósito:** Asegurarnos de que un dato concreto dentro de la respuesta JSON es el que esperamos.

Ejemplo en Código:

```Java
@Test
public void validateSpecificField() {
    RestAssured
        .when()
            .get("/users/2")
        .then()
            .statusCode(200)
            // ✅ Verifica que el campo 'id' dentro del objeto 'data' sea igual a 2
            .body("data.id", equalTo(2));
}
```

- `body("data.id", ...)`: Usamos "JSONPath" para navegar dentro del JSON. `data.id` significa: "entra al objeto `data` y dame el valor del campo `id`".

- `equalTo(2)`: Es un "matcher" de Hamcrest que comprueba si el valor es exactamente `2`.

### **3. Validar que un Campo NO sea Nulo**

**Propósito:** Muy útil cuando no conocemos el valor exacto de un campo, pero necesitamos asegurarnos de que exista y no esté vacío.

Ejemplo en Código:

```Java
@Test
public void validateFieldIsNotNull() {
    RestAssured
        .when()
            .get("/users/2")
        .then()
            .statusCode(200)
            // ✅ Verifica que el campo 'email' existe y no es nulo
            .body("data.email", notNullValue());
}
```

#### **4. Validar el Tamaño de una Lista**

**Propósito:** Contar cuántos elementos hay en una respuesta que devuelve una lista (un array de JSON).

Ejemplo en Código: Para este caso, usaremos un endpoint diferente que devuelve una lista de usuarios.

```Java
@Test
public void validateListSize() {
    int expectedUsersInPage = 6;

    RestAssured
        .when()
            // Este endpoint devuelve una lista de usuarios
            .get("/users?page=2")
        .then()
            .statusCode(200)
            // ✅ Verifica que la lista 'data' contenga 6 elementos
            .body("data.size()", equalTo(expectedUsersInPage));
}
```

- "`data.size()`": `data` es el nombre de la lista (array) en el JSON de respuesta. `.size()` es una función especial de Rest-Assured para obtener su tamaño.

#### **5. Validar una Condición en Todos los Elementos de una Lista**

**Propósito:** Aplicar una misma regla de validación a cada uno de los elementos de una lista, sin tener que revisarlos uno por uno.

Ejemplo en Código: Usando la misma respuesta de lista del punto anterior.

```Java
@Test
public void validateAllItemsInList() {
    RestAssured
        .when()
            .get("/users?page=2")
        .then()
            .statusCode(200)
            // ✅ Verifica que CADA elemento en la lista 'data' tenga un campo 'id' que no sea nulo.
            .body("data.id", everyItem(notNullValue()));
}
```

- `everyItem(...)`: Es un matcher poderoso que aplica la condición que está dentro de su paréntesis (en este caso, `notNullValue()`) a todos los elementos de la colección `data.id`.
---
#### Código completo con las validaciones anteriores integradas:

```Java
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class FirstApiTest {

    String apiKey = "reqres-free-v1"; // Definimos la API key como una variable

    @Before
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    /**
     * Esta prueba verifica un único usuario, ahora incluyendo la API key.
     */
    @Test
    public void getSingleUserAndValidateFields() {
        int userId = 2;
        String expectedUserName = "Janet";

        RestAssured
            .given() // Inicia la preparación de la petición
                .header("X-Api-Key", apiKey) // 🔑 AÑADIMOS LA API KEY AQUÍ
            .when()
                .get("/users/" + userId)
            .then()
                .log().body()
                .statusCode(200)
                .body("data.id", equalTo(userId))
                .body("data.first_name", equalTo(expectedUserName))
                .body("data.email", notNullValue());
    }

    /**
     * Esta prueba verifica una lista de usuarios, ahora incluyendo la API key.
     */
    @Test
    public void getUserListAndValidateContent() {
        int expectedUsersInPage = 6;

        RestAssured
            .given() // Inicia la preparación de la petición
                .header("X-Api-Key", apiKey) // 🔑 AÑADIMOS LA API KEY AQUÍ
            .when()
                .get("/users?page=2")
            .then()
                .log().body()
                .statusCode(200)
                .body("data.size()", equalTo(expectedUsersInPage))
                .body("data.id", everyItem(notNullValue()));
    }
}
```

### 6. Sintaxis basica de una clase java


## Sintaxis básica de una clase en Java

En Java, **una clase** es como una plantilla o molde que define cómo será un objeto: qué datos tendrá y qué podrá hacer. Es el punto de partida para organizar y reutilizar tu código.

### ¿Cómo se crea una clase?

Para crear una clase, creas un archivo `.java` y usas la palabra clave `class`, seguida del nombre que tú elijas (por convención, la primera letra va en mayúscula):

```java
public class Persona {
    // Aquí van las características (atributos) y acciones (métodos)
}
```

- `public` significa que la clase puede ser usada desde cualquier parte del proyecto.
- `class` es la palabra clave para definir la clase.
- `Persona` es el nombre de la clase.

### ¿Qué puede tener una clase?

1. **Atributos:** Son las características o datos que describen a la clase.
2. **Métodos:** Son las acciones o comportamientos que puede realizar.

#### Ejemplo sencillo

```java
public class Persona {
    // Atributos
    String nombre;
    int edad;

    // Método
    void saludar() {
        System.out.println("¡Hola! Mi nombre es " + nombre);
    }
}
```

- `String nombre;` y `int edad;` son atributos.
- `void saludar()` es un método que imprime un saludo.

### ¿Cómo se usa una clase?

Para usar una clase, primero debes **crear un objeto** (una "instancia" de la clase):

```java
public class Main {
    public static void main(String[] args) {
        Persona persona1 = new Persona(); // Creamos un objeto de tipo Persona
        persona1.nombre = "Ana";
        persona1.edad = 25;
        persona1.saludar(); // Llama al método saludar()
    }
}
```

**Salida:**
```
¡Hola! Mi nombre es Ana
```

### ¿Qué es importar una clase?

Si tu clase está en otro paquete (una carpeta especial de Java), debes **importarla** para poder usarla. Por ejemplo:

```java
import com.miempresa.modelos.Persona;

public class Main {
    // Aquí puedes usar la clase Persona
}
```

## 7. ¿Qué es Cucumber y cómo aplicarlo en Java?

**Cucumber** es una herramienta que permite escribir pruebas automatizadas en un lenguaje sencillo y entendible por cualquier persona, llamado **Gherkin**. Este enfoque se conoce como **BDD (Behavior-Driven Development)**, donde las pruebas se describen como escenarios de negocio que cualquier miembro del equipo puede leer y entender.

### ¿Por qué usar Cucumber?

- Permite que testers, desarrolladores y clientes colaboren en la definición de requisitos.
- Las pruebas se escriben en lenguaje natural (español o inglés).
- Se integra fácilmente con Java y frameworks de pruebas como JUnit.

### Estructura de un escenario en Cucumber (Gherkin)

Un archivo de Cucumber se llama **feature file** y tiene extensión `.feature`. Ejemplo:

```gherkin
Feature: Obtener información de usuario

    Scenario: Consultar usuario existente
        Given la API está disponible
        When consulto el usuario con id 2
        Then la respuesta debe tener status 200
        And el nombre debe ser "Janet"
```

- **Feature:** Describe la funcionalidad general.
- **Scenario:** Un caso de uso específico.
- **Given/When/Then/And:** Palabras clave para describir los pasos.

### ¿Cómo se conecta Cucumber con Java?

Cada línea del escenario se conecta con un método Java llamado **step definition**. Estos métodos ejecutan el código real de la prueba.

#### Ejemplo de integración básica

1. **Dependencias en `pom.xml`:**

```xml
<dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
</dependency>
<dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
</dependency>
<dependency>
        <groupId>io.rest-assured</groupId>
        <artifactId>rest-assured</artifactId>
        <version>5.4.0</version>
        <scope>test</scope>
</dependency>
```

2. **Archivo de características (`user.feature`):**

```gherkin
Feature: Consultar usuario

    Scenario: Usuario existente
        Given la API está disponible
        When consulto el usuario con id 2
        Then el nombre del usuario debe ser "Janet"
```

3. **Step Definitions en Java:**

```java
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
                response = when().get("/users/" + id);
        }

        @Then("el nombre del usuario debe ser {string}")
        public void el_nombre_del_usuario_debe_ser(String nombreEsperado) {
                response.then().statusCode(200)
                                .body("data.first_name", equalTo(nombreEsperado));
        }
}
```

4. **Runner de pruebas (JUnit):**

```java
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources", glue = "com.tuempresa.steps")
public class RunCucumberTest {
}
```

### Resumen del flujo

1. Escribes escenarios en `.feature` usando Gherkin.
2. Implementas los pasos en Java (step definitions).
3. Ejecutas las pruebas con JUnit y Cucumber.
4. Puedes combinar Cucumber con Rest-Assured para pruebas de APIs.

**¡Así puedes automatizar pruebas legibles y colaborativas usando Cucumber en Java!**

## 8. ¿Qué es un POJO y por qué es importante en pruebas de APIs?

### Definición de POJO

Un **POJO** (Plain Old Java Object) es una clase Java simple, sin dependencias especiales, que solo contiene atributos privados, constructores, getters y setters. No extiende ni implementa ninguna clase o interfaz específica de frameworks.

Ejemplo básico de POJO:

```java
public class Usuario {
    private int id;
    private String nombre;
    private String email;

    // Constructor vacío (necesario para algunas librerías)
    public Usuario() {}

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
```

### ¿Por qué crear modelos POJO para request y response?

Cuando trabajas con APIs, los datos suelen viajar en formato **JSON**. Convertir ese JSON a objetos Java (POJOs) tiene varias ventajas:

- **Legibilidad:** Puedes manipular los datos como objetos Java, en vez de navegar estructuras JSON complejas.
- **Mantenibilidad:** Si la estructura del JSON cambia, solo actualizas el POJO.
- **Reutilización:** El mismo POJO puede usarse para múltiples pruebas o servicios.
- **Validaciones más simples:** Puedes comparar objetos completos o acceder a campos específicos fácilmente.
- **Menos errores:** Evitas errores de tipeo en los nombres de campos y reduces el uso de "cadenas mágicas".

### ¿Cómo se usan los POJO en la automatización de APIs?

1. **Deserialización:** Convertir la respuesta JSON de la API a un objeto POJO usando librerías como Jackson o Gson (Rest-Assured lo hace automáticamente).
2. **Serialización:** Enviar un objeto POJO como cuerpo de una petición (por ejemplo, en un POST), y Rest-Assured lo convierte a JSON.


#### Ejemplo: Deserializar la respuesta de una API

Supón que la API responde con este JSON:

```json
{
  "id": 2,
  "first_name": "Janet",
  "email": "janet@reqres.in"
}
```

POJO correspondiente:

```java
public class Usuario {
    private int id;
    private String first_name;
    private String email;

    // Getters y setters...
}
```

Uso en Rest-Assured:

```java
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsuarioTest {
    @Test
    public void deserializarUsuario() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        Usuario usuario = response.jsonPath().getObject("data", Usuario.class);

        assertEquals(2, usuario.getId());
        assertEquals("Janet", usuario.getFirst_name());
    }
}
```

#### Ejemplo: Serializar un POJO para enviar en un request

```java
Usuario nuevoUsuario = new Usuario();
nuevoUsuario.setFirst_name("Carlos");
nuevoUsuario.setEmail("carlos@correo.com");

RestAssured
    .given()
        .contentType("application/json")
        .body(nuevoUsuario) // Rest-Assured lo convierte a JSON automáticamente
    .when()
        .post("/users")
    .then()
        .statusCode(201);
```

### Beneficios de usar POJOs en la automatización

- **Código más limpio y fácil de leer.**
- **Facilita el mantenimiento ante cambios en la API.**
- **Permite validaciones más robustas y menos propensas a errores.**
- **Mejor integración con frameworks de pruebas y herramientas de serialización/deserialización.**

9. Mini desafio


## 9. Mini desafío: 

A continuación tenemos ejercicios sencillos para practicar la creación de clases, POJOs y archivos feature. No necesitas escribir pruebas automatizadas ni código avanzado, solo enfócate en la estructura y sintaxis.

### Instrucciones generales

- Realiza cada ejercicio en un archivo separado dentro de tu proyecto Java.
- Usa solo lo aprendido sobre clases, POJOs y archivos feature.
- No es necesario ejecutar pruebas ni integrar frameworks.

### Ejercicios prácticos

#### 1. **Crea una clase Java básica**
- Crea un archivo llamado `Animal.java`.
- Define una clase `Animal` con los atributos `especie` (String) y `edad` (int).
- Agrega un método `hacerSonido()` que imprima un mensaje como "El animal hace un sonido".

#### 2. **Crea un POJO para un producto**
- Crea un archivo llamado `Producto.java`.
- Define una clase `Producto` con los atributos privados: `codigo` (int), `nombre` (String), y `precio` (double).
- Agrega un constructor vacío, getters y setters para cada atributo.

#### 3. **Crea un archivo feature sencillo**
- Crea un archivo llamado `producto.feature` en formato texto.
- Escribe una feature titulada "Consultar producto".
- Agrega un escenario que describa consultar el producto con código 101 y validar que el nombre sea "Laptop", usando solo lenguaje Gherkin (Given, When, Then).


## 10. **Tips:**

- Concéntrate en la sintaxis y estructura. No necesitas conectar estos archivos ni escribir lógica de pruebas.

- **Usa variables y constantes:** Declara datos clave (URLs, IDs, nombres esperados) como variables o constantes al inicio de tus pruebas para facilitar cambios y evitar errores de tipeo.
- **Aprovecha los logs:** Usa `.log().all()` o `.log().body()` en Rest-Assured para ver exactamente qué envías y recibes. Es tu mejor aliado para depurar.
- **Valida más allá del status code:** Siempre verifica campos importantes del body, headers y tamaños de listas para asegurar que la API responde correctamente.
- **Agrupa tus escenarios:** Usa tags en Cucumber (`@regression`, `@smoke`, etc.) para ejecutar subconjuntos de pruebas según el objetivo.
- **Utiliza POJOs para requests complejos:** Cuando el body de tu request es grande, crea un POJO y pásalo a `.body()`. Así tu código será más limpio y fácil de mantener.
- **Documenta tus pruebas:** Agrega comentarios breves sobre el propósito de cada prueba o escenario. Ayuda a otros (¡y a ti mismo en el futuro!) a entender rápidamente el objetivo.
- **Aísla la configuración:** Centraliza la configuración común (baseURI, headers, autenticación) en métodos `@Before` o clases utilitarias para evitar repeticiones.
- **Prueba casos negativos:** No solo valides respuestas exitosas; asegúrate de cubrir errores esperados (404, 400, etc.) para robustecer tus pruebas.
- **Explora matchers avanzados:** Usa matchers de Hamcrest como `containsString`, `hasItems`, `greaterThan`, etc., para validaciones más flexibles y potentes.









