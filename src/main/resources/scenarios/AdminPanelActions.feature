Feature: Admin panel actions

  Background: The website is opened
    Given open website "http://test-app.d6.dev.devcaz.com/admin/login"

  Scenario: Authorization to the admin panel as administrator

    When I enter an administrator's login to the login field
    And I enter an administrator's password to the password field
    And I click on the SignIn button
    Then System authorizes me as an Administrator
    And the administrator panel is successfully loaded

  Scenario: Open a list with players

    Given I am logged in the admin panel as an Administrator
    When I click on the User tab the Players tab is shown
    And Click on the Players tab open a table with players
    Then The table with players is successfully shown

  #Description: Since it is not possible to get all external ID numbers of players, it is decided
  #to check sorting as follows:
  # 1) sort by Asc all external IDs
  # 2) remember the first 10 IDs numbers
  # 3) sort bt Desc all external IDs
  # 4) jump to the last page
  # 5) take the last 10 IDs numbers and compare them with the first 10 IDs numbers.
  # Conclusion: If the system sorts values correctly, then the comparison should be carried out smooth.

  Scenario: Sort first 10 external IDs in a table with players by Ascending order

    Given I am logged in the admin panel as an Administrator and the table with players is opened
    When Clicking on a name of the External ID column initiates sorting by Ascending order of IDs number
    And Wait for the sorting is done
    Then At least the first twenty External ID numbers should be correctly sorted by Ascending


