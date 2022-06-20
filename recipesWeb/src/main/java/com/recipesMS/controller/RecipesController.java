package com.recipesMS.controller;

import com.recipesMS.model.Recipe;
import com.recipesMS.pojo.RecipeRequest;
import com.recipesMS.pojo.SearchRequest;
import com.recipesMS.service.RecipesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class RecipesController {

	private RecipesService recipesService;

	public RecipesController(RecipesService recipesService) {
		this.recipesService = recipesService;
	}

	@PostMapping(path ="/addRecipe",  consumes = "application/json", produces = "application/json")
	public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeRequest recipe) throws Exception{
		return new ResponseEntity<>(recipesService.addRecipe(recipe.getRecipe()), HttpStatus.OK);
		
	}
	
	@GetMapping(path ="/getAllRecipes", produces = "application/json")
	public ResponseEntity<List<Recipe>> getAllRecipes() {
		return new ResponseEntity<>(recipesService.getAllRecipes(), HttpStatus.OK);
		
	}
	
	@GetMapping(path ="/getRecipesByCategory", produces = "application/json")
	public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestParam ("categoryType") String categoryType) {
		return new ResponseEntity<>(recipesService.getRecipesByCategory(categoryType), HttpStatus.OK);
		
	}
	
	@PutMapping(path ="/updateRecipeByName/{recipeName}", produces = "application/json")
	public ResponseEntity<Recipe> updateRecipeByName(@PathVariable("recipeName") String recipeName, @RequestBody RecipeRequest recipeRequest) {
		return new ResponseEntity<>(recipesService.updateRecipeByName(recipeName, recipeRequest), HttpStatus.OK);
		
	}

	@PostMapping(path ="/searchFilter", produces = "application/json")
	public ResponseEntity<List<Recipe>> searchFilter(@RequestBody SearchRequest searchKey) {
		return new ResponseEntity<>(recipesService.searchFilter(searchKey), HttpStatus.OK);

	}

	@DeleteMapping(path ="/removeByName/{recipeName}", produces = "application/json")
	public ResponseEntity<Integer> removeRecipe(@PathVariable ("recipeName") String recipeName) {
		return new ResponseEntity<>(recipesService.removeRecipeByName(recipeName), HttpStatus.OK);

	}
	
}
