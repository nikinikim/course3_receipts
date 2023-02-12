package pro.sky.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {
    private String name;

    private Integer count;
    private String measureUnit;

}
