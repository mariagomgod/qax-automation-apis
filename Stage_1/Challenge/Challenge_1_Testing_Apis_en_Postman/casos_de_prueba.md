```javascript
Feature: Listar usuarios, registrar nuevos usuarios y realizar login
  Como tester de APIs
  Quiero poder listar usuarios, registrar nuevos usuarios y realizar login en el sistema de prueba
  Para asegurarme de que la API funcione correctamente y que los datos de usuario sean consistentes

  Scenario: CP01 - Listar usuarios - Consulta de listado exitosa
    Given el servicio de usuarios está disponible
    When el usuario solicita el listado de usuarios 
    Then la petición se procesa correctamente
    And el sistema devuelve la información esperada del listado de usuarios

  Scenario: CP02 - Listar usuario - Consulta de detalle exitosa
    Given el servicio de usuarios está disponible
    And existe un usuario registrado con un identificador válido
    When el usuario consulta la información de un usuario concreto 
    Then la petición se procesa correctamente
    And el sistema devuelve la información esperada de ese usuario

  Scenario: CP03 - Login de usuario - Inicio de sesión exitoso
    Given el servicio de autenticación está disponible
    When el usuario inicia sesión con credenciales válidas
    Then el acceso es concedido correctamente
    And el sistema devuelve la información esperada del usuario
```