package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.DishDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IDishService {
    public Dish addDish(DishDTO dishDTO);
    public Dish getDish(long dishId);
    public Page<Dish> getDishList(Pageable pageable, String name);
    public void deleteDish(long id);
}
