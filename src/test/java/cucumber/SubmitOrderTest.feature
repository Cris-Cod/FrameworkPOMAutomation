Feature: Purchase the order from Eccomerce WebSite

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> from cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples:
    | name                | password           | productName     |
    |cristian@prueba.com  |Robot182#           | ADIDAS ORIGINAL |
