Feature: Google search

  @tag1 @postUI
  Scenario: Sample scenario1
    Given Browser open
    When Read the value

  @tag2 @postUI
  Scenario: Sample scenario2
    Given Browser open2
    When Read the value

  @tag3 @postUI
  Scenario Outline: Sample scenario3
    Given the user opens the browser and navigates to the login page
    When the user enters username "shrrevishnu" and password "123"
    Then the user should see the dashboard page with title "Customer Login"

    Examples:
      |val|
      |s1|
#      |s2|


  @parallel @postUI
  Scenario: Sample scenario4
    Given the user opens the browser and navigates to the login page
    When the user enters username "shrrevishnu" and password "123"
    Then the user should see the dashboard page with title "Customer Login"
