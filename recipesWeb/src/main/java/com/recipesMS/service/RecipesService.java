package com.recipesMS.service;

import com.recipesMS.model.Recipe;
import com.recipesMS.pojo.RecipeRequest;
import com.recipesMS.pojo.SearchRequest;
import com.recipesMS.repository.RecipeCriteriaRepository;
import com.recipesMS.repository.RecipeMangerRepository;
import com.recipesMS.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class RecipesService {

	private final RecipeRepository recipeRepository;
	private final RecipeMangerRepository recipeMangerRepository;
	private final RecipeCriteriaRepository repository;

	public RecipesService(RecipeRepository recipeRepository, RecipeMangerRepository recipeMangerRepository, RecipeCriteriaRepository repository) {
		this.recipeRepository = recipeRepository;
		this.recipeMangerRepository = recipeMangerRepository;
		this.repository = repository;
	}

	public Recipe addRecipe(Recipe recipe) throws Exception{
		try {
			return recipeRepository.save(recipe);
		} catch(Exception exe) {
			throw new Exception(exe);
		}
	}

	public List<Recipe> getAllRecipes() {
		List<Recipe> list=(List<Recipe>) recipeRepository.findAll();
		return list;
	}

	public List<Recipe> getRecipesByCategory(String categoryType) {
		return recipeMangerRepository.findByCategory(categoryType);
	}

	public Recipe updateRecipeByName(String recipeName, RecipeRequest newRecipeName) {

		Recipe recipe = recipeMangerRepository.findByRecipeName(recipeName);
		recipe.setIngredients(newRecipeName.getRecipe().getIngredients());
		recipe.setCategory(newRecipeName.getRecipe().getCategory());
		recipe.setInstructions(newRecipeName.getRecipe().getInstructions());
		return recipeRepository.save(recipe);
	}

	public List<Recipe> searchFilter(SearchRequest request) {
		return repository.recipesWithFilters(request);
	}

	public Integer removeRecipeByName(String recipeName) {
		return recipeMangerRepository.deleteByRecipeName(recipeName);
	}

}
