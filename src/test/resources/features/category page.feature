Feature: Tests category page functionality

  Background:
    Given homepage is opened

  Scenario Outline: All super categories have at least 1 product
    When The user navigates to the <category> page
    Then the category should have items

    Examples:
      | category |
      | Women    |
      | Dresses  |
      | T-shirts |

    @AddProduct
  Scenario: Product can be added to cart from category page
    When The user navigates to the Women page
    And the user adds a product to the cart from Category Page
    Then the product should be added to the cart
