Feature: Consultar usuario

  Scenario: Usuario existente
    Given la API está disponible
    When consulto el usuario con id 2
    Then el nombre del usuario debe ser "Janet"