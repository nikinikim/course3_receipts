package pro.sky.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.FileService;
import pro.sky.recipes.services.RecipeService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Value("${name.of.recipe.file}")
    private String recipesFileName;

    public String getRecipesFileName() {
        return recipesFileName;
    }

    final private FileService fileService;

    private Map<Integer, Recipe> recipeMap = new HashMap<>();
    private static Integer id = 0;

    public Map<Integer, Recipe> getRecipeMap() {
        return recipeMap;
    }

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    @PostConstruct
    private void init() {
        fileService.readFromFile(recipesFileName);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe getRecipe(Integer id) {
        if (!recipeMap.containsKey(id)) {
            throw new NotFoundException("Рецепт по указанному Id не найден");
        }
        return recipeMap.get(id);
    }

    @Override
    public Recipe editRecipe(Integer id, Recipe recipe) {
        if (!recipeMap.containsKey(id)) {
            throw new NotFoundException("Рецепт по указанному Id не найден");
        }
        recipeMap.put(id, recipe);
        saveToFile();
        return recipe;
    }

    @Override
    public Recipe deleteRecipe(Integer id) {
        if (!recipeMap.containsKey(id)) {
            throw new NullPointerException("Рецепт по указанному Id не найден");
        }
        Recipe deletedRecipe = recipeMap.remove(id);
        saveToFile();
        return deletedRecipe;
    }

    @Override
    public Collection<Recipe> getAll() {
       return recipeMap.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json, recipesFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile(recipesFileName);
        try {
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}


