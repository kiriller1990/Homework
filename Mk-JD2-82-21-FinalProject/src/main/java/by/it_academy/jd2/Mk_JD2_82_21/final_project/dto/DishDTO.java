package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;

import java.util.List;

public class DishDTO {
    private String name;
    private List<Ingredient> ingredients;

    public DishDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
