package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.DishDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IDishService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IIngredientService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IDishDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Ingredient;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DishService implements IDishService {
    private final IDishDAO dishDAO;
    private final UserHolder userHolder;
    private final IProductService productService;

    public DishService(IDishDAO dishDAO, UserHolder userHolder, IProductService productService) {
        this.dishDAO = dishDAO;
        this.userHolder = userHolder;
        this.productService = productService;
    }

    @Override
    public void addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        LocalDateTime createDateTime = LocalDateTime.now();
        dish.setCreateDate(createDateTime);
        dish.setUpdateDate(createDateTime);
        dish.setUserWhoMadeTheEntry(userHolder.getUser());
        dish.setTitle(dishDTO.getName());

            // Извлекая id продуктов и вес продуктов добавляем в блюдо список ингредиентов
        List<Ingredient> ingredients = dishDTO.getIngredients();
        List<Ingredient> ingredientInDishList = new ArrayList<>();
        Ingredient ingredientInDish = new Ingredient();
        for(Ingredient ingredient : ingredients) {
                //получаем ID продукта которое передали
            long idProductInDish = ingredient.getProduct().getId();

                // По ID получаем продукт который будкт в блюде
            Product productInDish = productService.getProduct(idProductInDish);

                //добавляем продукт в ингредиент блюда
            ingredientInDish.setProduct(productInDish);

                //получаем вес который передали
            double weightProductInDish = ingredient.getWeight();

                //добавляем вес в ингредиент болюда
            ingredientInDish.setWeight(weightProductInDish);

            //добавляем в список ингредиентов блюда
            ingredientInDishList.add(ingredientInDish);
        }
        dish.setIngredients(ingredientInDishList);
        dishDAO.save(dish);
    }

    @Override
    public Dish getDish(long dishId) {
        return dishDAO.findById(dishId).orElseThrow(()-> new IllegalArgumentException ("По данному ID блюдо не найдено"));
    }

    @Override
    public Page<Dish> getDishList(Pageable pageable, String name) {
        if(name != null) {
            return dishDAO.findDishByTitle(name, pageable);
        } else {
            return dishDAO.findAll(pageable);
        }
    }

    @Override
    public void updateDish(Dish dish, long id, LocalDateTime dt_update) {
        LocalDateTime timeStamp = LocalDateTime.now();
        Dish updatedDish = getDish(id);
        updatedDish.setTitle(dish.getTitle());
        updatedDish.setIngredients(dish.getIngredients());
        updatedDish.setUpdateDate(timeStamp);
        dishDAO.save(updatedDish);

    }

    @Override
    public void deleteDish(long id, LocalDateTime dt_update) {
        dishDAO.deleteById(id);
    }
}
