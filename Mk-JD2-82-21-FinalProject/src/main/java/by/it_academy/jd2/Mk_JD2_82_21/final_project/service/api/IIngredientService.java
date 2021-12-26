package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;

import java.util.List;

public interface IIngredientService {
    public Ingredient addIngredient(Ingredient ingredient);
    public Ingredient getIngredient(long ingredientId);
    public List<Ingredient> getIngredientList();
    void updateIngredient(Ingredient ingredient, long id);
    void deleteIngredient(long id);
}
