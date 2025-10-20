```javascript
Feature: Exponer listado y detalle de personajes de The Simpsons con paginación
  Como consumidor externo de la API (aplicaciones cliente y servicios internos)
  Quiero obtener un listado paginado de personajes y consultar el detalle por id
  Para mostrar información consistente y navegable en mis aplicaciones y reutilizarla en otros servicios

  Scenario: CP01 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters" 
    Then el código de respuesta debe ser 200
    And la lista de personajes debe contener "id", "age", "birthdate", "email", "gender", "name", "occupation", portrait_path, "phrases" and "status"

  Scenario: CP02 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page={número mayor a última página}" 
    Then el código de respuesta debe ser 200
    And los campos "prev" y "next" deben contener el valor null
    
  Scenario: CP03 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page={número igual a última página}" 
    Then el código de respuesta debe ser 200
    And el campo "next" debe contener el valor null
    
  Scenario: CP04 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page=0" 
    Then el código de respuesta debe ser 200
    And el campo "prev" debe contener el valor null
    
  Scenario: CP05 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page={número página negativo}" 
    Then el código de respuesta debe ser 200
    And el campo "prev" debe contener el valor null
    
  Scenario: CP06 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page={valor distinto a un número}" 
    Then el código de respuesta debe ser 200
    And el campo "prev" debe contener el valor null
    
  Scenario: CP07 - Listado de personajes paginado
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición GET a "/api/characters?page={número página demasiado grande}}" 
    Then el código de respuesta debe ser 200
    And los campos "prev" y "next" deben contener el valor null

  Scenario: CP08 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id válido y existente}" 
    Then el código de respuesta debe ser 200
    And el personaje debe contener "id", "age", "birthdate", "email", "gender", "name", "occupation", portrait_path, "phrases" and "status"
    
  Scenario: CP09 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id inválido e inexistente}" 
    Then el código de respuesta debe ser 404
    And y la respuesta debe contener el campo "message" o "error"

  Scenario: CP10 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id válido e inexistente más grande que el máximo que acepta el servidor para ese parámetro}" 
    Then el código de respuesta debe ser 500
    And y la respuesta debe contener el campo "message" o "error"
    
  Scenario: CP11 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente expresado como decimal}" 
    Then el código de respuesta debe ser 200
    And el campo "id" en la respuesta debe ser un número entero
    
  Scenario: CP12 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id inexistente decimal}" 
    Then el código de respuesta debe ser 400
    And y la respuesta debe contener el campo "message" o "error"
    
  Scenario: CP13 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id = distinto a número}" 
    Then el código de respuesta debe ser 400
    And y la respuesta debe contener el campo "message" o "error"
    
  Scenario: CP14 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje ha fallecido 
    Then el código de respuesta debe ser 200
    And el campo "status" debe contener el valor "Deceased"
    
  Scenario: CP15 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje está vivo 
    Then el código de respuesta debe ser 200
    And el campo "status" debe contener el valor "Alive"
    
  Scenario: CP16 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje tiene género masculino
    Then el código de respuesta debe ser 200
    And el campo "gender" debe contener el valor "Male"
    
  Scenario: CP17 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje tiene género femenino
    Then el código de respuesta debe ser 200
    And el campo "gender" debe contener el valor "Female"
    
  Scenario: CP18 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje no tiene ningún valor asociado al campo "age"
    Then el código de respuesta debe ser 200
    And el campo "age" debe contener el valor null
    
  Scenario: CP19 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje no tiene ningún valor asociado al campo "birthdate"
    Then el código de respuesta debe ser 200
    And el campo "birthdate" debe contener el valor null
    
  Scenario: CP20 - Listar un personaje
    Given la API está disponible en "https://thesimpsonsapi.com"
    When realizo una petición POST a "/api/characters/{id existente}"
    And el personaje está desempleado
    Then el código de respuesta debe ser 200
    And el campo "occupation" debe contener el valor "Unemployed"
```