@AllTests @API
Feature: This feature is the greatest factorial calculator

  I as an User want to be able to calculate factorial value when I input valid number

  @API
  Scenario Outline: Calculate factorial of integer value
    Given I provide number "<value>"
    When I try to calculate
    Then I will get factorial result for that number <value>
    Examples:
      | value |
      | -1    |
      | 0     |
      | 5     |
      | 171   |
      | 767   |
      | 999   |

  @API
  Scenario Outline: Provide invalid value
    Given I provide non integer "<value>"
    When I try to calculate
    Then I will get error message
    Examples:
      | value  |
      | String |
      | 30.99  |
      | ^%$    |


