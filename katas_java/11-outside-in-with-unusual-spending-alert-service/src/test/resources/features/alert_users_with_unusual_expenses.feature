Feature: Alert Users With Unusual Expenses
  User has a bunch of payments and each one belongs to a category.

  Scenario: The payments of this month on restaurant outstrip by 50% the previous month payments.
            The payments of this month on rent don't outstrip the previous month payments.
            The payments of this month on health outstrip by more than 50% the previous month payments.
            System will send an alert to the user notifying the restaurant and health current month expenses.

    Given a user with id '1234' and email 'user@mail.com'
    And being today '08/02/2020'
    And a list of payments for the user:
      | amount |  category  |   date     |
      |   100  | restaurant | 10/01/2020 |
      |   300  | restaurant | 15/01/2020 |
      |   200  |    rent    | 17/01/2020 |
      |   100  |   health   | 20/01/2020 |
      |   601  | restaurant | 01/02/2020 |
      |   300  |    rent    | 04/02/2020 |
      |   400  |   health   | 07/02/2020 |
    When execute de detection of unusual expenses of user '1234'
    Then send a mail to 'user@mail.com' with subject 'Unusual spending of $1000 detected!' with this body
      """
      Hello card user!

      We have detected unusually high spending on your card in these categories:

      * You spent $601 on restaurant
      * You spent $400 on health

      Love,

      The Credit Card Company
      """
