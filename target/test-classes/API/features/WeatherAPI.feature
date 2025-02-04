Feature: Weather API Search

  @weatherAPIGET
  Scenario Outline: GET REQUEST Validation of the forecast API - '<cityName>'
    Given the Weather API is available
    When I request the current weather for '<cityName>'
    Then the response status should be 200

    Examples:
    |cityName|
    |Chennai|
    |London|
    |Banglore|
