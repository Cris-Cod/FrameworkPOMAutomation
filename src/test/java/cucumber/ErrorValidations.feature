Feature: Error Validation

  @ErroValidation
  Scenario Outline:
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | name                | password           |
      |cristian@prueba.com  |Robot182            |
