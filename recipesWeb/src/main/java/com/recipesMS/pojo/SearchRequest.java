package com.recipesMS.pojo;

import lombok.Data;

@Data
public class SearchRequest {
    private String category;
    private Integer servings;
    private String instruction;
    private String ingredient;
}
