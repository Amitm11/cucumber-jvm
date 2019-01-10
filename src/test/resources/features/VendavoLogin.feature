Feature: Login to Vendavo website
  
#  @Test
  Scenario: Sign in and verify page title
#  	Given user is logged in to vendavo website as vendavosystem for 3M
	When user opens vendavo website
	Then user signs in as vendavosystem for 3M
	Then user verifies page title
	Then user creates evidence file
