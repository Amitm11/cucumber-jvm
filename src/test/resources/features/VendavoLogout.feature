Feature: Logout from Vendavo website

  Scenario: Logout from Vendavo site
    Given user is logged in to vendavo website as vendavosystem for 3M
    Then user signs out
