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
import pro.sky.recipes.model.Ingredient;
import pro.sky.recipes.services.IngredientService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингредиенты", description = "CRUD - операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиент добавлен")})
    public ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Поиск ингредиента по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиент найден")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.getIngredient(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменение ингредиентов по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиент изменен",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Ingredient.class))})})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Ingredient> editRecipe(@PathVariable Integer id, @Valid @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.editIngredient(id, ingredient));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиент удален")})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    public ResponseEntity<Ingredient> deleteRecipe(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientService.deleteIngredient(id));
    }

    @GetMapping("/all")
    @Operation(summary = "Получение всех ингредиентов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ингредиенты получены")})
    public ResponseEntity<Collection<Ingredient>> getAllRecipes() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}
