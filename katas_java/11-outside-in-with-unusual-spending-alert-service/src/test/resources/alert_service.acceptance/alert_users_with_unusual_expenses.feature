Feature: Alert Users With Unusual Expenses

  Scenario: User has a bunch of payments that belong to a category and these payments exceed
  the 50% of the amount of previous month. System will send an alert to the user notifying this amount
    Given a user with 1000 on '10/01/2012'
    And a deposit of 2000 on '13/01/2012'
    And a withdrawal of 500 on '14/01/2012'
    When print the statement
    Then the client sees its banking transactions sorted by date from most recent to oldest
