package pro.sky.recipes.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.recipes.model.Ingredient;
import pro.sky.recipes.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceImpl implements IngredientService {

    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        return ingredient;
    }

    @Override
    public Ingredient getIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NullPointerException("Ингредиент по указанному id отсутствует");
        }
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new NullPointerException("Ингредиент по указанному id отсутствует");
        }
        return ingredientMap.put(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NullPointerException("Ингредиент по указанному id отсутствует");
        }
        Ingredient deletedIngredient = ingredientMap.remove(id);
        return deletedIngredient;
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }


}
