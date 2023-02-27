package pro.sky.recipes.services;

import org.springframework.core.io.InputStreamResource;
import pro.sky.recipes.model.Recipe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FileService {


    String getFilePath();

    boolean saveToFile(String json, String fileName);

    String readFromFile(String fileName);

    File getDatafile(String fileName);

    boolean cleanFile(String fileName);

    InputStreamResource exportRecipes(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException;
}
