Feature: Tests the search functionality

  Background:
    Given homepage is opened

  Scenario: Check searching for an item works as expected
    When the user searches for an existing item Blouse
    Then the user should be taken to the search page with the item