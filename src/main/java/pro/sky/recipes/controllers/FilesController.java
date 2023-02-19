package pro.sky.recipes.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.recipes.services.FileService;
import pro.sky.recipes.services.IngredientService;
import pro.sky.recipes.services.RecipeService;
import pro.sky.recipes.services.impl.IngredientServiceImpl;
import pro.sky.recipes.services.impl.RecipeServiceImpl;

import java.io.*;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final RecipeServiceImpl recipeService;
    private final IngredientServiceImpl ingredientService;
    private FileService fileService;

    public FilesController(RecipeServiceImpl recipeService, IngredientServiceImpl ingredientService, FileService fileService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.fileService = fileService;
    }

    @GetMapping(value = "/ingredient/export")
    public ResponseEntity<InputStreamResource> downloadIngredientFile() throws FileNotFoundException {
        File file = fileService.getDatafile(ingredientService.getIngredientFileName());

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Ingredient.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping(value = "/recipe/export")
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = fileService.getDatafile(recipeService.getRecipesFileName());

        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM).contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"Recipe.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }

    }

    @PostMapping(value = "/ingredient/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        fileService.cleanFile(ingredientService.getIngredientFileName());
        File dataFile = fileService.getDatafile(ingredientService.getIngredientFileName());

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(value = "/recipe/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        fileService.cleanFile(recipeService.getRecipesFileName());
        File dataFile = fileService.getDatafile(recipeService.getRecipesFileName());

        try (FileOutputStream fos = new FileOutputStream(dataFile)) {
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
