@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url mockServerURL
    And path '/removeByName/Spageti'
    And method delete
    Then status 200
    And print response