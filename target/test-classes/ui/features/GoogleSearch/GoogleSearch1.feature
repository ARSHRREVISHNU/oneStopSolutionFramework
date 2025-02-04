Feature: Google search

  @parallel @postUI
  Scenario: Sample scenario5
    Given the user opens the browser and navigates to the login page
    When the user enters username "shrrevishnu" and password "123"
    Then the user should see the dashboard page with title "Customer Login"
