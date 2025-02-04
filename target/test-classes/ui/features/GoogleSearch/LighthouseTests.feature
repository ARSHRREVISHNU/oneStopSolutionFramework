Feature: Run Lighthouse Tests

  @lighthouse @postUI
  Scenario: Generate Lighthouse Report for a Website
    Given the user opens the browser and navigates to the login page
    Then I run Lighthouse tests and generate a report
