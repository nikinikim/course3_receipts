package pro.sky.recipes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.RecipeService;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
