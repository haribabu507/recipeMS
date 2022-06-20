package com.recipesMS.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recipesMS.model.Recipe;

import java.util.UUID;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, UUID> {

}
