Feature: Consultar producto

  Scenario: Consultar un producto
    Given un producto con código 101
    When envio la petición correspondiente
    Then validar que el nombre sea "Laptop"