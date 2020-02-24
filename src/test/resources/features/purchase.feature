Feature: Tests purchase feature from adding a product to cart until finishing the purchase

  Background:
    Given homepage is opened

  @HappyPath
  Scenario Outline: Make a successful purchase
    When the user adds a product to the cart from homepage <withOrWithout> changing size and color
    And finishes the checkout process through bank wire
    Then the order should be successfully placed

    Examples:
    |withOrWithout|
    |with         |
    |without      |
