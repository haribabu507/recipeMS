@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url mockServerURL
    And path '/updateRecipeByName/Meat Balls'
    And request {"recipe":{"recipeName": "Meat Balls","category": "Meat","instructions":"Gas","servings": 4, "ingredients": [{"name":"Salt"}, {"name": "Oil"}]}}
    And method put
    Then status 200
    And print response