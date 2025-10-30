Feature: Gestión de publicaciones y comentarios
  Como tester de APIs
  Quiero poder listar usuarios
  Para validar que la API funcione correctamente y que los datos sean consistentes

  @comments
  Scenario: CP01 Crear comentario (con valores por defecto)
    Given el endpoint /comments
    And uso el cuerpo de la solicitud por defecto
    When envío una petición POST
    Then la API responde con status_code 201
    And la respuesta incluye un id generado y refleja exactamente los datos enviados

  Scenario: CP02 Listar comentarios de un post
    Given el endpoint /comments
    And indico el parámetro postId con valor 1
    When envío una petición GET
    Then la API responde con status_code 200
    And la respuesta contiene al menos un comentario
    And cada comentario incluye id, nombre, email y cuerpo
    And cada email contiene el carácter @