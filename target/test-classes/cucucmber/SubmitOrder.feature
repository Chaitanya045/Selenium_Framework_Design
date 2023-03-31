@tag
Feature: Purchase the order from Ecommerce Website

  Background: 
  	Given I landed on Ecommerce page

  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                | password      | productName |
      | email@chaitanya.com | Chaitanya@075 | ZARA COAT 3 |
