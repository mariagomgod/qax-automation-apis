# Challenge 01:  Escalando mi proyecto de automatización

En este reto vamos a practicar la creación de feature, funciones y variables dentro de un proyecto estructurado de automatización. La idea es que aprendas a organizar tu código de pruebas de forma clara, reutilizable y fácil de mantener.

Vamos a enfocarnos en:

    - Definir casos de prueba individuales que puedan ejecutarse de manera aislada.

    - Crear funciones JS para que el código sea más legible y reutilizable.

    - Usar variables centralizadas que nos permitan cambiar datos de prueba sin modificar el caso principal.

    - Ejecutar pruebas desde un archivo de proyecto bien estructurado para simular un entorno real.

>El objetivo es que al finalizar, entiendas cómo dar tus primeros pasos hacia una automatización ordenada y profesional, que no solo funcione, sino que también sea fácil de escalar.

## Historia de Usuario: Gestión de Publicaciones y Comentarios

**Como** tester de APIs,  
**Quiero** poder listar usuarios
**Para** validar que la API funcione correctamente y que los datos sean consistentes.

### Criterios de Aceptación

**BaseUrl: https://jsonplaceholder.typicode.com"**

**Listar usuarios**:
- GET `/users` debe devolver HTTP 200.
- La lista no debe estar vacía y cada usuario debe tener `id`, `name`, `username` y `email`.

### Archivo `.feature`

```gherkin

Scenario: CP01 Crear comentario
    Given path '/comments'
    And request 
      """
            {
            "postId": 1,
            "name": "Juan",
            "email": "juan@email.com",
            "body": "Este es un comentario de prueba"
            }

        """
    When method post
    Then status 201
    And match response == { id: '#notnull', postId: 1, name: 'Juan', email: 'juan@email.com', body: 'Este es un comentario de prueba' }

Scenario: CP02 Listar comentarios de un post
    Given path '/comments'
    And param postId = 1
    When method get
    Then status 200
    And match response.size() > 0
    And match each response == { id: '#notnull', name: '#notnull', email: '#string', body: '#notnull' }
    And match each response.email contains '@'
```

### Instrucciones
- Usando el proyecto creado en el `Exercise_1`

1. Crear una nuevo archivo `.feature` ordenado en una carpeta.
2. Agregar el nuevo endpoint de `comments`
3. Ejecutar todos los tests
4. Subir en la carpeta correspondiente del respositorio personal

