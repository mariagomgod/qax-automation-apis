# User Management

End-to-end behavior for listing users via the public API.

## Behaviour (Gherkin)

```gherkin
Feature: User Management

  Scenario: List users
    Given the API is up
    When I list users
    Then I should see a list of users
    And the status code is 200

