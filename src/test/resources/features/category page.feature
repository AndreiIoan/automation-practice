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

  Scenario: Product can be added to cart from category page
    When The user navigates to the Women page
    And the user adds a product to the cart from Category Page
    Then the product should be added to the cart

  Scenario: Product can be added to wishlist from category page when user is logged in
    And the user is logged in
    When the user adds a product to the wishlist
    Then the user should be able to view the product in the wishlist

  Scenario: Product cannot be added to wishlist if the user is not logged in
    When The user navigates to the Women page
    And the user adds a product to the wishlist
    Then the user should not be able to add it unless he logs in

