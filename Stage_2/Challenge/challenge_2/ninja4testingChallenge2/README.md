# Challenge 2: Automatizando un flujo completo usando Token

En este reto, vamos a automatizar un flujo completo de interacción con la API de libros: desde buscar un libro, extraer el ISBN del primer libro de la lista, hasta agregarlo a un usuario existente. El objetivo es que aprendas a combinar operaciones GET y POST, manejar respuestas JSON y usar valores dinámicos de un request en otro, todo de manera práctica y reproducible en tus tests de automatización.

[Documentación oficial](https://demoqa.com/swagger/)

## Historia de Usuario

- Título: Registro y acceso seguro a la Book Store API

  Como usuario/automatizador de pruebas,
  quiero poder registrar un usuario, obtener un token de autenticación y consumir endpoints protegidos con ese token,
  para verificar que la API maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible.

- BaseUrl: https://demoqa.com

## Flujo del usuario para agregar un libro

- POST `/Account/v1/User` → crear usuario con userName y password.

- POST `/Account/v1/GenerateToken` → obtener token JWT con userName y password.

- GET `/Account/v1/User/{userId}` → obtener información del usuario, usando Authorization: Bearer <token>.

- GET `BookStore/v1/Books` Obtener la informacion de los libros

- POST `BookStore/v1/Books` → Agregar un libro al usuario, usando Authorization: Bearer <token>.

## Colección Postman 

Se facilita en el proyecto.

## Casos de prueba

1. Crear usuario con datos inválidos

```gherkin
    Dado: Enviar POST /Account/v1/User con:

    userName vacío o

    password que no cumple reglas.

    Entonces: Verificar que la API responda HTTP 400.

    Resultado esperado: status_code == 400 y el cuerpo contiene información de error.
```

2. Generar token con credenciales inválidas

```gherkin
  Dado: Usar credenciales incorrectas.

  Entonces: Verificar que la API responda HTTP 200.

  Resultado esperado: status_code en {200} y mensaje de autenticación fallida.`User authorization failed.`
```

3. Acceso protegido sin token o con token inválido

```gherkin
  Dado: Realizar petición sin header Authorization o con Bearer <invalid>.

  Entonces: Verificar que la API responda HTTP 401

  Resultado esperado: status_code en {401} y el cuerpo contiene mensaje de acceso denegado. `User not authorized!` y code `1200`
```

4. Buscar libro y agregarlo a un usuario

```gherkin
  Dado: Realizar GET /Books o endpoint equivalente para obtener la lista de libros, Extraer el primer ISBN del primer libro del array de resultados

  Cuando: Realiza la peticion para agregar el libro a un usuario

  Entonces: Usar ese ISBN para enviar POST /BookStore/v1/Book/ y agregar un nuevo libro al usuario.

  Resultado esperado:  Status code 201.   Verificar que la respuesta confirme que el libro fue agregado es correctamente.
```
## Instrucciones

> Usar el proyecto creado en el entrenamiento

1. Implementa los escenarios en Gherkin y Step Definitions con Rest Assured.
2. Usar clase `.java` para los payloads de las peticiones.
3. Organiza el proyecto en carpetas (features, steps, models, utils, runners) y reutiliza utilidades.
4. Ejecuta y verifica:
    - Corre tus tests.
    - Asegúrate de que todos los tests pasen y los resultados sean consistentes.
5. Subir el proyecto completo y reporte en su carpeta correspondiente del repositorio personal

### Importante no subir .venv, debe estar en el .gitignore

