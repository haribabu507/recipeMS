package com.recipesMS.pojo;

import com.recipesMS.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {

    Recipe recipe;
}
