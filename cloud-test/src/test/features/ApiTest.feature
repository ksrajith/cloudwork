#Feature: Feature file to test api services

@assignment
Feature: APIs verifications

  @api
  Scenario Outline: verify api invocations
   Given user invoke "<api>" with test case "<testCaseName>"
    When user should receive the response
    Then verify response code
    Then Verify response body
    Examples:
    |api|testCaseName|
    |create|createValidUser|
    |create|createInvalidUser|
    |read|readValidUser    |
    |read|readInvalidUser|
    |update|updateValidUser  |
    |update|updateInvalidUser|
    |delete|deleteValidUser  |
    |delete|deleteInvalidUser|