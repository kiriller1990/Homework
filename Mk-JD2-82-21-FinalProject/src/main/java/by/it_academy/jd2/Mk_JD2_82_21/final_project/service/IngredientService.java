package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IIngredientService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IIngredientDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngredientService implements IIngredientService {
    private static IIngredientDAO ingredientDAO;
    private static IProductService iProductService;

    public IngredientService(IIngredientDAO ingredientDAO,IProductService iProductService) {
        this.ingredientDAO = ingredientDAO;
        this.iProductService = iProductService;
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        Product product = iProductService.getProduct(ingredient.getProduct().getId());
        ingredient.setProduct(product);
        LocalDateTime createTime = LocalDateTime.now();;
        Ingredient saveIngredient = ingredientDAO.save(ingredient);
        return saveIngredient;
    }

    @Override
    public Ingredient getIngredient(long ingredientId) {
        return ingredientDAO.findById(ingredientId).orElseThrow(()->
                new IllegalArgumentException("По данному ID ингредиент не найден"));
    }

    @Override
    public List<Ingredient> getIngredientList() {
        return ingredientDAO.findAll();
    }

    @Override
    public void updateIngredient(Ingredient ingredient, long id) {
            Ingredient ingredientUpdate = getIngredient(id);
            ingredientUpdate.setProduct(ingredient.getProduct());
            ingredientUpdate.setWeight(ingredient.getWeight());
            ingredientDAO.save(ingredientUpdate);
    }

    @Override
    public void deleteIngredient(long id) {
            ingredientDAO.deleteById(id);
    }
}
