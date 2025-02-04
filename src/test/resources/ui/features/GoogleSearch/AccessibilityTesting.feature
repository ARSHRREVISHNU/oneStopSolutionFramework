Feature: Accessibilty Testing

  @access @postUI
  Scenario: Accessibilty Testing
    Given the user opens the browser and navigates to the login page
    When I analyze the page for accessibility
    Then I should see no critical accessibility violations

