package pro.sky.recipes.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.recipes.model.Recipe;
import pro.sky.recipes.services.RecipeService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD - операции для работы с рецептами")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    @Operation(summary = "Добавление рецепта")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт добавлен")})
    public ResponseEntity<Recipe> addRecipe(@Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск рецепта по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт найден")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Recipe> getRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение рецептов по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт изменен",
    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Recipe.class))})})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Recipe> editRecipe(@PathVariable Integer id, @Valid @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.editRecipe(id, recipe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепт удален")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Recipe> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(recipeService.deleteRecipe(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех рецептов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Рецепты получены")})
    public ResponseEntity<Collection<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
