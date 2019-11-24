Feature: Print statement
  Print a statement after deposit and withdraw money

  Scenario: After deposit and withdraw money, print a statement showing the banking transactions
    Given a deposit of 1000 on '10/01/2012'
    And a deposit of 2000 on '13/01/2012'
    And a withdrawal of 500 on '14/01/2012'
    When print the statement
    Then the client sees its banking transactions sorted by date from most recent to oldest
