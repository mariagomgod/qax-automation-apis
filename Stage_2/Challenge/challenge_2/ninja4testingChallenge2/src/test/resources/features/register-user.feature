Feature: Registro y acceso seguro a la Book Store API
  Como usuario/automatizador de pruebas
  Quiero poder registrar un usuario
  Y consumir endpoints protegidos con ese token, para verificar que la API maneja correctamente el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible

  @user
  Scenario: CP-1a — Crear usuario con datos inválidos (userName vacío)
    Given un request body inválido para crear usuario con userName vacío
    When realizo una petición POST a /Account/v1/User
    Then la respuesta tiene status_code 400
    And el cuerpo de la respuesta contiene información de error

  Scenario: CP-1b — Crear usuario con datos inválidos (password inválida)
    Given un request body inválido para crear usuario con password inválida
    When realizo una petición POST a /Account/v1/User
    Then la respuesta tiene status_code 400
    And el cuerpo de la respuesta contiene información de error

  Scenario: CP-2 — Generar token con credenciales inválidas
    Given credenciales inválidas para generar un token
    When realizo una petición POST a /Account/v1/GenerateToken
    Then la respuesta tiene status_code 200
    And el cuerpo de la respuesta contiene el mensaje User authorization failed.

  Scenario: CP-3a — Acceso protegido sin token
    When realizo una petición GET a /Account/v1/User/{userId} sin el header Authorization
    Then la respuesta tiene status_code 401
    And el cuerpo de la respuesta contiene el mensaje User not authorized!
    And el cuerpo de la respuesta contiene el code "1200"

  Scenario: CP-3b — Acceso protegido con token inválido
    When realizo una petición GET a /Account/v1/User/{userId} con Bearer token inválido
    Then la respuesta tiene status_code 401
    And el cuerpo de la respuesta contiene el mensaje User not authorized!
    And el cuerpo de la respuesta contiene el code "1200"

  Scenario: CP-4 — Buscar libro y agregarlo a un usuario
    When realizo una petición GET a /BookStore/v1/Books con credenciales válidas y un token activo
    And guardo el ISBN del primer libro del resultado
    And envío una petición POST a /BookStore/v1/Books para agregar ese ISBN al usuario
    Then la respuesta tiene status_code 201
    And el cuerpo de la respuesta confirma que el libro fue agregado correctamente