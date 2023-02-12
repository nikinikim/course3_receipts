package pro.sky.recipes.services;

import pro.sky.recipes.model.Ingredient;

import java.util.Collection;

public interface IngredientService {

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(Integer id);

    Ingredient editIngredient(Integer id, Ingredient ingredient);

    Ingredient deleteIngredient(Integer id);

    Collection<Ingredient> getAll();
}
