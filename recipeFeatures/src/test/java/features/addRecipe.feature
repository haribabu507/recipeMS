@test
Feature: /getAllRecipes
  Background:

  Scenario: Retrieve all the recipes available
    Given url mockServerURL
    And path 'addRecipe'
    And request {"recipe":{"recipeName": "Meat Balls","category": "Meat","instructions":"Gas","servings": 4, "ingredients": [{"name": "pepper"},{"name":"Salt"}, {"name": "Oil"}]}}
    And method post
    Then status 200
    And print response