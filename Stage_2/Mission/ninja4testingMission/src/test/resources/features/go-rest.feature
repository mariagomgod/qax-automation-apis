Feature: Flujos de prueba API Go Rest
  Como tester de APIs
  Quiero automatizar y validar la creación de usuarios, publicaciones y comentarios
  Para que pueda asegurarme de que la API funciona correctamente y cumple los contratos esperados

  Scenario: CP-1 — Crear usuario
    Given un request body válido para crear usuario
    When realizo una petición POST a /public/v2/users
    Then la respuesta tiene status_code 201
    And validar que el usuario se ha creado correctamente

  Scenario: CP-2 — Crear publicación
    Given un usuario
    When realizo una petición POST a /public/v2/posts
    Then la respuesta tiene status_code 201
    And validar que el usuario puede crear publicaciones asociadas a su cuenta correctamente

  Scenario: CP-3 — Crear comentario
    Given un usuario
    When realizo una petición POST a /public/v2/comments
    Then la respuesta tiene status_code 201
    And validar que un comentario se puede agregar a una publicación existente correctamente

  Scenario: E2E — Crear usuario, crear publicación y agregar comentario
    Given un request body válido para crear usuario
    When realizo una petición POST a /public/v2/users
    Then la respuesta tiene status_code 201
    And validar que el usuario se ha creado correctamente

    When preparo un request body válido de publicación para el usuario creado
    And realizo una petición POST a /public/v2/posts
    Then la respuesta tiene status_code 201
    And validar que el usuario puede crear publicaciones asociadas a su cuenta correctamente

    When preparo un request body válido de comentario para la publicación creada
    And realizo una petición POST a /public/v2/comments
    Then la respuesta tiene status_code 201
    And validar que un comentario se puede agregar a una publicación existente correctamente
