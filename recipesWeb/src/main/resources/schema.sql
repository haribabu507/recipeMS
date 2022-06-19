CREATE TABLE IF NOT EXISTS recipes
(recipeId UUID,
recipeName VARCHAR (60) NOT NULL,
category VARCHAR (10) NOT NULL,
instructions VARCHAR (10),
servings int (10),
);


CREATE TABLE IF NOT EXISTS ingredient
(ingredientId UUID,
name VARCHAR (10)
);

