```javascript
Feature: Listar usuarios, registrar nuevos usuarios y realizar login
  Como tester de APIs
  Quiero poder listar usuarios, registrar nuevos usuarios y realizar login en el sistema de prueba
  Para asegurarme de que la API funcione correctamente y que los datos de usuario sean consistentes

  Scenario: CP01 - Listar usuarios
    Given la API está disponible en "https://reqres.in/api-docs/"
    When realizo una petición GET a "/api/users" 
    Then el código de respuesta debe ser 200
    And la lista de usuarios debe contener "id", "first_name", "last_name" y "email"

  Scenario: CP02 - Registrar usuario
    Given la API está disponible en "https://reqres.in/api-docs/"
    When realizo una petición POST a "/api/register" 
    Then el código de respuesta debe ser 200
    And la respuesta debe contener "id" y "token"

  Scenario: CP03 - Login de usuario
    Given la API está disponible en "https://reqres.in/api-docs/"
    When realizo una petición POST a "/api/login" con credenciales válidas:

        Header:

        {
           "x-api-key":"reqres-free-v1"
        } 
        
        Body:

        {
            "username": "george.bluth@reqres.in",
            "email": "george.bluth@reqres.in",
            "password": "test"
        }

        Response

        {
            "token": "QpwL5tke4Pnpja7X1"
        }

    Then el código de respuesta debe ser 200
    And la response debe contener un "token"
```