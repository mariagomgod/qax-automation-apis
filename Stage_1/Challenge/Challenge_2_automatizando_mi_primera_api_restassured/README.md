# Challenge 2: Automatizar mi primera API con Rest Assured

## Introducción
Tomaré los casos de prueba que creé en Postman para la API de **[ReqRes](https://reqres.in/api-docs/)** y los automatizaré usando **Rest Assured** en Java.  
**Objetivo:** Cómo convertir pruebas manuales en tests automatizados, validando tanto códigos de estado como contenido de la respuesta.

---

## Instrucciones

1. **Configurar tu proyecto Java**
    - Crear un proyecto Maven.
    - Agregar las dependencias de **Rest Assured** y **Junit**  en el `pom.xml` .
    - Asegurar poder ejecutar tests desde mi IDE o desde línea de comandos.

2. **Crear un archivo de tests**
    - Definir una clase de test, por ejemplo `ReqResApiTest.java`.
    - Importar Rest Assured y el framework de test elegido.

3. **Mapea los casos de prueba**
    - **CP01:** Listar usuarios (`GET /api/users`)
        - Verificar código HTTP 200.
        - Validar que cada usuario tenga `id`, `first_name`, `last_name` y `email`.
    - **CP02:** Registrar usuario (`POST /api/register`)
        - Envíar un payload válido con email y password.
        - Verificar código HTTP 200.
        - Confirmar que la respuesta contenga `id` y `token`.
    - **CP03:** Login de usuario (`POST /api/login`)
        - Envíar credenciales válidas.
        - Verificar código HTTP 200.
        - Confirmar que la respuesta contenga un `token`.

4. **Agregar validaciones**
    - Usar `statusCode()` para verificar el HTTP status.
    - Usar `body()` con matchers de Rest Assured (`equalTo`, `containsString`, `notNullValue`) para validar campos de la respuesta.

