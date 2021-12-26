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
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.CheckUtil;
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
    private final CheckUtil checkUtil;
    private final IIngredientService ingredientService;

    public DishService(IDishDAO dishDAO, UserHolder userHolder,
                       IProductService productService, CheckUtil checkUtil, IIngredientService ingredientService) {
        this.dishDAO = dishDAO;
        this.userHolder = userHolder;
        this.productService = productService;
        this.checkUtil = checkUtil;
        this.ingredientService = ingredientService;
    }

    @Override
    public Dish addDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        LocalDateTime createDateTime = LocalDateTime.now();
        dish.setCreateDate(createDateTime);
        dish.setUpdateDate(createDateTime);
        dish.setUserWhoMadeTheEntry(userHolder.getUser());
        dish.setTitle(dishDTO.getName());
        dish.setIngredients(getIngredientListInDish(dishDTO));
        Dish savedDish = dishDAO.save(dish);
        return savedDish;
    }

    public List<Ingredient> getIngredientListInDish (DishDTO dishDTO) {
        List<Ingredient> ingredients = dishDTO.getIngredients();
        List<Ingredient> ingredientInDishList = new ArrayList<>();
        Ingredient ingredientInDish = new Ingredient();
        for(Ingredient ingredient : ingredients) {
            Ingredient ingredientSaved = this.ingredientService.addIngredient(ingredient);
            ingredientInDishList.add(ingredientSaved);
        }
        return  ingredientInDishList;
    }

    @Override
    public Dish getDish(long dishId) {
        return dishDAO.findById(dishId).orElseThrow(()-> new IllegalArgumentException ("По данному ID блюдо не найдено"));
    }

    @Override
    public Page<Dish> getDishList(Pageable pageable, String name) {
        if(name != null) {
            return dishDAO.findDishByTitleContains(name, pageable);
        } else {
            return dishDAO.findAll(pageable);
        }
    }


    @Override
    public void deleteDish(long id) {
        Dish updatedDish = getDish(id);
        User updateUser = updatedDish.getUserWhoMadeTheEntry();
        if (updateUser==userHolder.getUser() || checkUtil.isAdminRoleCheck()) {
            dishDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException("Вы не можете удалять чужое блюдо");
        }
    }
}
