Feature: Registro y acceso seguro a la API de usuarios
  Como usuario/automatizador de pruebas
  Quiero poder registrar un usuario
  Y consumir endpoints protegidos con ese token, para verificar que la API maneja correctamente
  el alta de usuarios, la emisión de tokens y el acceso autorizado a información sensible

  @user @register
  Scenario: CP01 — Registrar usuario con datos válidos
    Given un request body válido para registrar usuario con name, email y password
    When realizo una petición POST a /users/register
    Then la respuesta tiene status_code 201
    And el cuerpo de la respuesta contiene los campos id y name

  @user @login
  Scenario: CP02 — Login de usuario con credenciales válidas
    Given un request body válido para login con email y password
    When realizo una petición POST a /users/login
    Then la respuesta tiene status_code 200
    And el cuerpo de la respuesta contiene un campo token

  @user @profile
  Scenario: CP03 — Listar perfil de usuario autenticado
    Given que tengo un token de acceso válido obtenido del login
    When realizo una petición GET a /users/profile
    Then la respuesta tiene status_code 200
    And el cuerpo de la respuesta contiene los campos id, name y email
