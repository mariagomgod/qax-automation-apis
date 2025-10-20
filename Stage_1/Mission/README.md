# Automatización de APIs – The Simpsons
En esta sección voy a utilizar la api de **The Simpsons** ([The Simpsons Docs](https://thesimpsonsapi.com/#docs)), una API pública que proporciona información sobre los personajes, episodios y locaciones de la serie animada `The Simpsons`.

**Objetivo:** Aprender a transformar una historia de usuario y sus criterios de aceptación en **casos de prueba claros**, ejecutarlos primero manualmente en **Postman** y luego automatizarlos usando **Rest Assured**.

## Historia de Usuario

### Título: Exponer listado y detalle de personajes de The Simpsons con paginación

**Como** consumidor externo de la API (aplicaciones cliente y servicios internos)
**Quiero** obtener un listado paginado de personajes y consultar el detalle por id,
**Para** mostrar información consistente y navegable en mis aplicaciones y reutilizarla en otros servicios.

```javascript
Feature: Exponer listado y detalle de personajes de The Simpsons con paginación
  Como consumidor externo de la API (aplicaciones cliente y servicios internos
  Quiero obtener un listado paginado de personajes y consultar el detalle por id
  Para mostrar información consistente y navegable en mis aplicaciones y reutilizarla en otros servicios
```

### Alcance de esta historia

- Base Url: `https://thesimpsonsapi.com/api`

- GET /api/characters — listado paginado (query param page).

- GET /api/characters/{id} — detalle de un personaje por identificador.

### Criterios de Aceptación


- Listado 200 y contrato base

    - `GET /api/characters` responde 200 OK y el cuerpo incluye exactamente las claves:
    - `count` (int), `next` (string|null), `prev` (string|null), `pages` (int), `results` (array de objetos).

    - `Content-Type` = `application/json; charset=utf-8`

- Paginación por query param

    - El parámetro `page` es query param (`?page={n}`) y acepta enteros ≥ 1.

    - Si `page` no se envía, se asume `page=1`

    - `next` y `prev` son URLs absolutas correctas o `null` cuando no aplican.

- Cálculo de metadatos

    - `count` refleja el total de personajes disponibles.

    - `results.length` <= `pageSize.`

- Estructura mínima de cada personaje en results. Cada objeto posee:
    -  `id` (int), `age` (int), `birthdate` (string YYYY-MM-DD), `gender` (string),
    - `name` (string), `occupation` (string), `portrait_path` (string ruta /`character/{id}.webp)`,
    - `phrases` (array<string>), `status` (string).

- Validación de formatos
    - `birthdate` cumple ^\d{4}-\d{2}-\d{2}$.

    - `portrait_path` cumple ^/character/\d+\.webp$.

    - `phrases` es array y puede estar vacío, pero nunca `null`.

- Detalle por ID 200

    - `GET /api/characters/{id}` responde 200 OK cuando existe y retorna las mismas claves del objeto personaje del listado.

    - Para `id=1`, `name = "Homer Simpson"` (dato de referencia para pruebas).

- Errores bien definidos

    - `GET /api/characters/{id}` con un `id` inexistente responde 404 Not Found con cuerpo:
      `{ "error": "Character not found", "id": <enviado> }`.

    - `GET /api/characters?page=<valor no entero o <1>` responde 400 Bad Request con cuerpo:
      `{ "error": "Invalid page parameter" }`.

- Orden estable del listado

    - El listado es estable y determinista (por defecto ordenado por id asc).

## **Instrucciones:**

1. Diseñar los  casos de prueba que cubran todos los criterios de aceptacion. `Min 20`
2. Probarlo en **Postman** y validar manualmente que los endpoints respondan correctamente.
3. Automatizar los mismos casos en **Rest Assured** siguiendo buenas prácticas:
    - Validar códigos HTTP.
    - Verificar campos obligatorios en las respuestas.
4. Subir los casos de pruebas en lenguaje `markdown`, el proyecto de `Rest Assured`, reporte de ejecucion y colección en `postman` al respositorio personal.


