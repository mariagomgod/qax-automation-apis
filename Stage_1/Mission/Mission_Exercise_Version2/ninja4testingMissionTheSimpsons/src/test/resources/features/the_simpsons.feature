Feature: Listado y detalle de personajes de The Simpsons
  Como consumidor externo de la API de The Simpsons
  Quiero obtener un listado paginado de personajes
  Y consultar el detalle por id
  Para mostrar información consistente y navegable en mis aplicaciones

  @simpsons @list
  Scenario: CP01 — Listar personajes sin enviar page devuelve la primera página
    When realizo una petición GET a /characters sin enviar el query param page
    Then la respuesta tiene status_code 200
    And el cuerpo del listado de personajes corresponde a la primera página por defecto

  @simpsons @list @pagination
  Scenario: CP02 — Listar personajes enviando un page válido
    When realizo una petición GET a /characters con el query param page=2
    Then la respuesta tiene status_code 200
    And el cuerpo del listado de personajes incluye metadatos de paginación válidos

  @simpsons @list @structure
  Scenario: CP03 — Validar estructura mínima y formatos de personajes en el listado
    When realizo una petición GET a /characters con el query param page=1
    Then la respuesta tiene status_code 200
    And cada personaje del listado cumple la estructura mínima y formatos requeridos

  @simpsons @detail
  Scenario: CP04 — Obtener detalle de un personaje existente por id
    When realizo una petición GET a /characters/1
    Then la respuesta tiene status_code 200
    And el cuerpo del detalle de personaje corresponde a Homer Simpson

  @simpsons @detail @error
  Scenario: CP05 — Detalle de personaje inexistente devuelve 404
    When realizo una petición GET a /characters/99999
    Then la respuesta tiene status_code 404
    And el cuerpo de la respuesta indica que el personaje no existe

  @simpsons @list @order
  Scenario: CP06 — Listado estable y ordenado por id ascendente
    When realizo una petición GET a /characters sin enviar el query param page
    Then la respuesta tiene status_code 200
    And el listado de personajes es estable y está ordenado por id ascendente
