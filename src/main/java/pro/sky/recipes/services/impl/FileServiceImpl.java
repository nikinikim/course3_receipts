package pro.sky.recipes.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

    @Value("${path.to.files}")
    private String filePath;

    @Override
    public String getFilePath() {
        return filePath;
    }


    @Override
    public boolean saveToFile(String json, String fileName) {
        try {
            cleanFile(fileName);
            Files.writeString(Path.of(filePath, fileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile(String fileName) {
        try {
            return Files.readString(Path.of(filePath, fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public File getDatafile(String fileName){
        return new File(filePath + "/" + fileName);
    }

    @Override
    public boolean cleanFile(String fileName) {
        try {
            Path path = Path.of(filePath, fileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public InputStreamResource exportRecipes(Map<Integer, Recipe> recipeMap) throws FileNotFoundException, IOException {
        Path path = this.createAllRecipesFile("allRecipes");
        for (Recipe recipe : recipeMap.values()) {
            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(" Название рецепта: ");
                writer.append(recipe.getName());
                writer.append("\n Время приготовления: ");
                writer.append(String.valueOf(recipe.getCookingTime()));
                writer.append(" ");
                writer.append("\n Ингредиенты: ");
                writer.append(String.valueOf(recipe.getIngredients()));
                writer.append("\n Шаги приготовления: ");
                writer.append(String.valueOf(recipe.getSteps()));
            }
        }

        File file = path.toFile();
        return new InputStreamResource(new FileInputStream(file));
    }

    private Path createAllRecipesFile(String suffix) throws IOException {
        if (Files.exists(Path.of(filePath, suffix))) {
            Files.delete(Path.of(filePath, suffix));
            Files.createFile(Path.of(filePath, suffix));
            return Path.of(filePath, suffix);
        }
        return Files.createFile(Path.of(filePath, suffix));
    }

}
