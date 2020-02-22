Feature: Tests login functionality

  Background:
    Given homepage is opened

  Scenario Outline: Login with invalid and valid credentials
    When The user navigates to the Sign in page
    And Tries to log in with <validOrInvalid> <email> and <passwordToBeUsed>
    Then the user <isOrIsn't> logged in

    Examples:
      | validOrInvalid | email               | passwordToBeUsed | isOrIsn't |
      | invalid        | testmail@test.com   | wrongpass        | isn't     |
      | valid          | andreitest@test.com | password         | is        |


  Scenario: User can create a new account
    When The user navigates to the Sign in page
    And user creates account in authentication page
    Then an account should successfully be created
