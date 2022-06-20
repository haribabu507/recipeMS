package com.recipesMS.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name="recipes")
public class Recipe {

	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "org.hibernate.type.UUIDCharType")
	private UUID recipeId;
	@Column(name = "recipeName", nullable = false, unique = true)
	private String recipeName;
	@Column
	private String category;

	@Column
	private String instructions;

	@Column
	private String servings;

	@OneToMany(targetEntity = Ingredient.class, cascade={CascadeType.ALL}, orphanRemoval = true)
	@PrimaryKeyJoinColumn(name = "recipeName", referencedColumnName = "recipeId")
	private List<Ingredient> ingredients;


}
