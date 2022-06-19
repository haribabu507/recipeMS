@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url serverURL
    And path /getAllRecipes
    And method get
    Then status 200
    And print response
