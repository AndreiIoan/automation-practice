Feature: Tests purchase feature from adding a product to cart until finishing the purchase

  Background:
    Given The web app is running

  @HappyPath
  Scenario: Add product to cart and finish the purchase
    When I add a product to the cart
    And I proceed to checkout
    And I continue to the authentication part of checkout
    Then I "create account" in authentication page