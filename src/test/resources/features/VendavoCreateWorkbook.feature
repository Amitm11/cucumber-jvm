# Author: Amit Kumar Maheshwari - amit.maheshwari@experis.com
# This feature file to creaate workbook in Price Manager tab
# This test is specifically for Price Corridor Threshold Workbook creation
#-------------- Important Notes for writing this feature file --------------
# 1. Give scenario a meaningful name
# 2. Always give complete name of workbook folder same as displayed on the screen
# 3. Always give complete name of workbook same as displayed on the screen
# 4. Always give complete name of template same as displayed on the New Workbook From Template dialog
# 5. Always give Sheet Name and Excel File name without extension same as displayed in testData folder
# 6. Always give project name and replace 3M with another project name
#----------------------------------------------------------------------------
# 
Feature: Show casing the capabilities of cucumber-jvm framework
	
	@Test 
  Scenario: Generate Country List Price at Pricing Region Price List
  	When user opens vendavo website
    Then user signs in as vendavosystem for 3M
    And navigates to Price Manager page
    Then user reads WBNames sheet from 3M_TestData excel file for Flag=Y workbook
    Then user verifies the presence of Flag=Y folder and expands it
    And user verifies the presence of Flag=Y workbook and clicks on it
    Then user creates a New Workbook From Template for Flag=Y workbook
    Then user reads WBFilterValues sheet from 3M_TestData excel file for Flag=Y workbook
    And selects the filter values for 3M
    Then user selects validity date values
    Then user fills the workbook name
    Then user opens worksheets and expands all of them and wait for data to load
    Then user reads WSFilterValues sheet from 3M_TestData excel file for Flag=Y workbook
    Then user selects Currency filter value
    And user reads GridData sheet from 3M_TestData excel file for Flag=Y workbook
    And fills up the first row data 
    Then user generates the price list
    Then user clicks on submit button 
    And user signs out
    Then user creates evidence file
    Then user sends email to stakeholders with attachment
    
#    @Test
   Scenario: capturing the error message while submitting the workbook
    When user opens vendavo website
    Then user signs in as vendavosystem for 3M
    And navigates to Price Manager page
    Then user reads WBNames sheet from 3M_TestData excel file for Flag=Y workbook
    Then user verifies the presence of Flag=Y folder and expands it
    And user verifies the presence of Flag=Y workbook and clicks on it
    Then user creates a New Workbook From Template for Flag=Y workbook
    Then user reads WBFilterValues sheet from 3M_TestData excel file for Flag=Y workbook
    And selects the filter values for 3M
    Then user selects validity date values
    Then user fills the workbook name
    Then user opens worksheets and expands all of them and wait for data to load
    Then user clicks on submit button
	  Then user creates evidence file
    Then user sends email to stakeholders with attachment
    
