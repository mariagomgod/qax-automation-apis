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