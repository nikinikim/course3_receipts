package pro.sky.recipes.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pro.sky.recipes.model.Ingredient;
import pro.sky.recipes.services.FileService;
import pro.sky.recipes.services.IngredientService;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    final private FileService fileService;

    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();
    private static Integer id = 0;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        ingredientMap.put(id++, ingredient);
        saveToFile();
        return ingredient;
    }

    @PostConstruct
    private void init() {fileService.readFromFile(ingredientFileName);}

    @Override
    public Ingredient getIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент по указанному id отсутствует");
        }
        return ingredientMap.get(id);
    }

    @Override
    public Ingredient editIngredient(Integer id, Ingredient ingredient) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент по указанному id отсутствует");
        }
        saveToFile();
        return ingredientMap.put(id, ingredient);
    }

    @Override
    public Ingredient deleteIngredient(Integer id) {
        if (!ingredientMap.containsKey(id)) {
            throw new NotFoundException("Ингредиент по указанному id отсутствует");
        }
        Ingredient deletedIngredient = ingredientMap.remove(id);
        return deletedIngredient;
    }

    @Override
    public Collection<Ingredient> getAll() {
        return ingredientMap.values();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json, ingredientFileName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = fileService.readFromFile(ingredientFileName);
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<TreeMap<Integer, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
