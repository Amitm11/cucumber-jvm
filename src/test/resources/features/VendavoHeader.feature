Feature: Verify Vendavo common headers left and right 
	
#	@demo
  Scenario: Vendavo header right
    Given user is logged in to vendavo website as vendavosystem for 3M
    When user verifies the right header presence
    When user verifies the right header menu options
    And user signs out
	
#	@demo
	Scenario: Vendavo header left
	Given user is logged in to vendavo website as vendavosystem for 3M
	When user verifies the left header presence
	When user verifies the left header menu options
	And user signs out