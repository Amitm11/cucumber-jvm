Feature: Vendavo Price Manager 

	@demo
  Scenario: Verify root children and sub children
    Given user is logged in to vendavo website as vendavosystem for 3M
    And navigates to Price Manager page 
    Then user verifies the number of root children presence
    Then user verifies the number of sub children presence for each of the root childeren
    And user signs out
