@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url mockServerURL
    And path 'getAllRecipes'
    And method get
    Then status 200
    And print response