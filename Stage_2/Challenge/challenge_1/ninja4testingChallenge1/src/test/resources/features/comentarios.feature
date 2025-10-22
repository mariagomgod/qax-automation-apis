Feature: Crear Post en JSONPlaceholder
  Como tester de APIs
  Quiero crear un post con Rest Assured
  Para validar que el servicio devuelve 201 y refleja los datos enviados

  @create-post
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

  @create-post
  Scenario: CP02 Listar comentarios de un post
    Given path '/comments'
    And param postId = 1
    When method get
    Then status 200
    And match response.size() > 0
    And match each response == { id: '#notnull', name: '#notnull', email: '#string', body: '#notnull' }
    And match each response.email contains '@'