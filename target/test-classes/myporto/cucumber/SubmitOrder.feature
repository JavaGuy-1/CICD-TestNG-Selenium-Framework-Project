@tag
Feature: Purchase the item from e-commerce website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <email> and password <password>
    When add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | email  						| password | productName |
      | Test@example.com	| Test1234 | ZARA COAT 3 |
     
