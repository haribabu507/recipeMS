package com.recipesMS.Controller;

import com.recipesMS.controller.RecipesController;
import com.recipesMS.model.Ingredient;
import com.recipesMS.model.Recipe;
import com.recipesMS.service.RecipesService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipesControllerTest {
    RecipesController recipesController;

    @Mock RecipesService recipesService;

    @BeforeEach
    public void setUp() {
        recipesController = new RecipesController(recipesService);
    }

    @Test
    @DisplayName("Get all recipes")
    public void testGetAllRecipes() {
        Recipe recipe = getRecipeDetails();
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);
        when(recipesService.getAllRecipes()).thenReturn(recipeList);

        ResponseEntity<List<Recipe>> recipeList1 = recipesController.getAllRecipes();

        assertThat(recipeList1.getBody().size(), Matchers.is(1));
    }

    private Recipe getRecipeDetails() {
        Recipe recipe = new Recipe();
        recipe.setRecipeName("Pasta");
        recipe.setCategory("Veg");
        recipe.setInstructions("Gas");
        recipe.setServings(String.valueOf(3));
        Ingredient ingredient1 = new Ingredient();
        Ingredient ingredient2 = new Ingredient();
        Ingredient ingredient3 = new Ingredient();
        ingredient1.setName("Oil");
        ingredient2.setName("Salt");
        ingredient3.setName("Pepper");

        List<Ingredient> ingredientList = new ArrayList<>();
        ingredientList.add(ingredient1);ingredientList.add(ingredient2);ingredientList.add(ingredient3);
        recipe.setIngredients(ingredientList);

        return recipe;
    }
}
