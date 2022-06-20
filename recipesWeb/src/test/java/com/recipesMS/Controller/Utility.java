package com.recipesMS.Controller;

import com.recipesMS.model.Ingredient;
import com.recipesMS.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static Recipe getRecipeDetails() {
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
