
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Error validation when wrong username and password
    Given I landed on Ecommerce Page
    When Logged in with username <email> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
    	| email  						| password |
      | Test@example.com	| Test123 |
