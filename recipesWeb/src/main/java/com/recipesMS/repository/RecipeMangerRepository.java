package com.recipesMS.repository;

import com.recipesMS.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface RecipeMangerRepository extends JpaRepository<Recipe, String> {
    Recipe findByCategory(String category);
    Recipe findByRecipeName(String recipeName);
    Integer deleteByRecipeName(String recipeName);
}
