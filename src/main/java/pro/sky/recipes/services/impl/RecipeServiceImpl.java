package pro.sky.recipes.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.RecipeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipeServiceImpl implements RecipeService {

    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (!recipeMap.containsKey(id)) {
            throw new NullPointerException("Рецепт по указанному Id не найден");
        }
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(Integer id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new NullPointerException("Рецепт по указанному Id не найден");
        }
        recipeMap.put(id, recipe);
        return recipe;
    }

    @Override
    public Recipe deleteRecipe(Integer id) {
        if (!recipeMap.containsKey(id)) {
            throw new NullPointerException("Рецепт по указанному Id не найден");
        }
        Recipe deletedRecipe = recipeMap.remove(id);
        return deletedRecipe;
    }

    @Override
    public Collection<Recipe> getAll() {
       return recipeMap.values();
    }


}


