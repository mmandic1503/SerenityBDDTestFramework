@AllTests @UI
Feature: This feature is the greatest factorial calculator

  I as an User want to be able to calculate factorial value when I input valid number

  Background:
    Given I navigate to Factorial Calculator web page

  @UI
  Scenario: Calculate factorial UI
    Given I provide valid number
    When I press calculate button
    Then I will get factorial result for that number

  @UI
  Scenario Outline:
    Given I provide invalid data "<value>"
    When I press calculate button
    Then I will see error message
    Examples:
      | value  |
      | String |
      | 30.99  |
      | ^%$    |
      | -1     |
      | null   |
