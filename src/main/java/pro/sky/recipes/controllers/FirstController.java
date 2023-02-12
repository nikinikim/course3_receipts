package pro.sky.recipes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping
    public String receipts() {
        return "Книга рецептов";
    }

    @GetMapping("/info")
    public String book() {
        return "Книга рецептов Мосунова Н.Н.";
    }
}
