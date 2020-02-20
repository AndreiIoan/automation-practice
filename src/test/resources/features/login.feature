Feature: Tests login functionality

  Background:
    Given The web app is running

  Scenario: Try to login with invalid credentials - should fail
    When I navigate to the "Sign in" page
    And I try to log in with invalid "test@mail.com" and "password"
    Then I should receive an error message

#  Scenario: Try to login with valid credentials - should pass
#    Given
#    When
#    Then