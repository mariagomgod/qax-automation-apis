# Exercise 02:  Uso de token y generación de request body dinámicos

En este ejercicio, nos vamos a centrar únicamente en el flujo de **crear un usuario**, **generar el token** y luego **utilizar esos valores para obtener la información del usuario creado** en la **[Book Store API](https://demoqa.com/swagger/)**. El objetivo es practicar la autenticación basada en tokens y la generación dinámica de cuerpos de peticiones (JSON), simulando un escenario real de QA.

Este flujo es fundamental para validar que la API maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible.

## Historia de Usuario

- Título: Registro y acceso seguro a la Book Store API

  Como usuario/automatizador de pruebas,
  quiero poder registrar un usuario, obtener un token de autenticación y consumir endpoints protegidos con ese token,
  para verificar que la API maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible.

- BaseUrl:https://demoqa.com

## Flujo para obtener la información del usuario

- POST `/Account/v1/User` → crear usuario con userName y password.

- POST `/Account/v1/GenerateToken` → obtener token JWT con userName y password.

- GET `/Account/v1/User/{userId}` → obtener información del usuario, usando Authorization: Bearer <token>.

## Coleccion Postman

> Descargar e importar la colección: [Api Testing - Book Store - QAX.postman_collection](/Assets/02_Stage_2/02_Training/03_Exercise_2/Api%20Testing%20-%20Book%20Store%20-%20QAX.postman_collection.json)

## Casos de prueba

- CP-1 — Crear usuario exitosamente

-   **Dado** que el usuario desea crear un nuevo usuario
-   **Cuando**  el Usuario envia la peticion para la creación del usuario
-   **Entonces** la API responde con HTTP 201 y devuelve userId (UUID)

Esperado: status_code == 201 y respuesta contiene una clave userId.

- CP-2 — Generar token válido

-   **Dado** que tengo credenciales correctas de un usuario ya creado,
-   **Cuando** que envío POST /Account/v1/GenerateToken ,
-   **Entonces** la API responde con HTTP 200 y devuelve un token.

Esperado: status_code == 200 y token existe

- CP-3 — Obtener info del usuario con token válido

-  **Dado** que tengo un token válido y un userId validos
-  **Cuando** envío GET /Account/v1/User/{userId} con header Authorization: Bearer <token>,
-  **Entonces** la API responde con HTTP 200 y devuelve el JSON del usuario.

Esperado: status_code == 200 y respuesta contiene userID igual al {userId} consultado.
