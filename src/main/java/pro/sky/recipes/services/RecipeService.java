package pro.sky.recipes.services;

import pro.sky.recipes.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Recipe addRecipe(Recipe recipe);

    Recipe getRecipe(Integer id);


    Recipe editRecipe(Integer id, Recipe recipe);

    Recipe deleteRecipe(Integer id);

    Collection<Recipe> getAll();
}
