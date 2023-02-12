package pro.sky.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor

public class Recipe {

    private String name;

    private Integer cookingTime;

    private List<Ingredient> ingredients;

    private List<String> steps;



}
