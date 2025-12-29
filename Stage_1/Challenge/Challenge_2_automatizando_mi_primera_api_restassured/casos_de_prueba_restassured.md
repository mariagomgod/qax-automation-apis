```javascript
Feature: Listar usuarios, registrar nuevos usuarios y realizar login
  Como tester de APIs
  Quiero poder listar usuarios, registrar nuevos usuarios y realizar login en el sistema de prueba
  Para asegurarme de que la API funcione correctamente y que los datos de usuario sean consistentes

  Scenario: CP01 - Listar usuarios - Consulta de listado exitosa
    Given el servicio de usuarios está disponible
    When el usuario solicita el listado de usuarios
    Then la petición se procesa correctamente
    And el sistema devuelve un listado de usuarios
    And cada usuario del listado contiene la información básica necesaria

  Scenario: CP02 - Registrar usuario - Registro exitoso
    Given el servicio de usuarios está disponible
    And se dispone de datos de registro válidos
    When el usuario registra un nuevo usuario en el sistema
    Then el registro se completa correctamente
    And el sistema devuelve la información del nuevo usuario
    And el sistema devuelve un token de autenticación válido

  Scenario: CP03 - Login de usuario - Inicio de sesión exitoso
    Given el servicio de autenticación está disponible
    And el usuario dispone de credenciales válidas
    When el usuario inicia sesión en el sistema
    Then el acceso es concedido correctamente
    And el sistema devuelve un token de autenticación válido
```