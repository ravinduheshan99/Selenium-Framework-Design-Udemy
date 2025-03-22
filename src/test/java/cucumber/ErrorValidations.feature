@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Positive Test of Submitting the order
    Given I Landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password" message is displayed

    Examples: 
      | name           | password |
      | test@gmail.com | invalid  |