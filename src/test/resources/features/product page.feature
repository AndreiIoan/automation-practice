Feature: Tests functionality of the product page

  Background:
    Given homepage is opened

    Scenario: Test that the user can increase and decrease product qty
      When the user goes to the product page
      Then the user can <increaseOrDecrease> product quantity