@tag
Feature: Purchase the Order from Ecommerce Website
  I want to use this template for my feature file

  Background:
  Given I Landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name           | password | productName |
      | test@gmail.com | test123 | ZARA COAT 3 |