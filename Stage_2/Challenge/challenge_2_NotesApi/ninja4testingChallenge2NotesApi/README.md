# Challenge 2: Automatizando mi primera API.

## Introducción
En este challenge, tomarás los casos de prueba que creaste en Postman para la API de **[Notes Api](https://practice.expandtesting.com/notes/api/api-docs/)** y los automatizarás usando **Rest Assured** en Java.  
El objetivo es convertir pruebas manuales en tests automatizados, validando tanto códigos de estado como contenido de la respuesta.
Base URL: https://practice.expandtesting.com/notes/api/

## Instrucciones

1. **Configura tu proyecto Java**
    - Crea un proyecto Maven.
    - Agrega las dependencias de **Rest Assured** y **Junit**  en tu `pom.xml` .
    - Asegúrate de poder ejecutar tests desde tu IDE o desde línea de comandos.

2. **Crea un archivo de tests**
    - Define una clase de test, por ejemplo `NotesApiTests.java`.
    - Importa Rest Assured y el framework de test elegido.

3. **Mapea los casos de prueba**
    - **CP01:** Registrar usuario (`POST /users/register`)
        - Envía un payload válido con name, email y password.
        - Verifica código HTTP 201.
        - Confirma que la respuesta contenga `id` y `name`.
        - body `{   
                    "name": "string",
                    "email": "string",
                    "password": "string"
                }`
    - **CP02:** Login de usuario (`POST /users/login`)
        - Envía credenciales válidas.
        - Verifica código HTTP 200.
        - Confirma que la respuesta contenga un `token`.
        - body `{   
                    "email": "string",
                    "password": "string"
                }`
    - **CP03:** Listar usuario (`GET /api/profile`)
        - Verifica código HTTP 200.
        - Valida que cada usuario tenga `id`, `name`, y `email`.
        - Para la correcta ejecución de este escenario, se debe capturar el token y añadirlo en el `Header`

4. **Agrega validaciones**
    - Usa `statusCode()` para verificar el HTTP status.
    - Usa `body()` con matchers de Rest Assured (`equalTo`, `containsString`, `notNullValue`) para validar campos de la respuesta.

5. **Ejecuta y verifica**
    - Corre tus tests desde el IDE o con Maven.
    - Asegúrate de que todos los tests pasen y los resultados sean consistentes.
    - Si algún test falla, revisa la URL, payload y los criterios de aceptación.


