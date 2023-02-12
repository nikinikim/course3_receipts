package pro.sky.recipes.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipes.model.Ingredient;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.IngredientService;

import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@PathVariable Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.getIngredient(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editRecipe(@PathVariable Integer id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Ingredient>> getAllRecipes() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}
