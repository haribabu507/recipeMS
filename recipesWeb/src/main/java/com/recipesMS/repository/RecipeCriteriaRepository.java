package com.recipesMS.repository;

import com.recipesMS.model.Ingredient;
import com.recipesMS.model.Recipe;
import com.recipesMS.pojo.SearchRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class RecipeCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder builder;

    public RecipeCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.builder = entityManager.getCriteriaBuilder();
    }

    public List<Recipe> recipesWithFilters(SearchRequest searchRequest) {
        CriteriaQuery<Recipe> criteriaQuery = builder.createQuery(Recipe.class);
        Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);
        ListJoin<Recipe, Ingredient> ingredients = recipeRoot.joinList("ingredients", JoinType.INNER);
        Predicate predicate = getPredicate(searchRequest, recipeRoot, ingredients);
        criteriaQuery.where(predicate);
        TypedQuery<Recipe> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    private Predicate getPredicate(SearchRequest searchRequest, Root<Recipe> recipeRoot, ListJoin<Recipe, Ingredient> ingredients) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchRequest.getCategory())) {
            predicates.add(
                    builder.like(recipeRoot.get("category"), "%"+searchRequest.getCategory()+"%")
            );
        }
        if (Objects.nonNull(searchRequest.getServings())) {
            predicates.add(
                    builder.like(recipeRoot.get("servings"), "%"+searchRequest.getServings()+"%")
            );
        }
        if (Objects.nonNull(searchRequest.getInstruction())) {
            predicates.add(
                    builder.like(recipeRoot.get("instructions"), "%"+searchRequest.getInstruction()+"%")
            );
        }

        if (Objects.nonNull(searchRequest.getIngredient())) {
            predicates.add(
                    builder.like(ingredients.get("name"), "%"+searchRequest.getIngredient()+"%")
            );
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
