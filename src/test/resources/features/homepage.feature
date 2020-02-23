Feature: Tests functionality of homepage

  Background:
    Given homepage is opened

  Scenario: Product can be added to cart from homepage
    When the user adds a product to the cart from homepage
    Then the product should be added to the cart

  Scenario: Product can be removed from cart dropdown
    When the user adds a product to the cart from homepage
    And the user removes the product from the cart
    Then the product should be removed from the cart