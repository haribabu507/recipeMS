@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url mockServerURL
    And path 'searchFilter'
    And request {"ingredient": "Salt"}
    And method post
    Then status 200
    And print response