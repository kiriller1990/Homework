package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.DishDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IDishService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.OptimisticLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping ("api/recipe")
public class DishController {
    private final IDishService dishService;
    private final OptimisticLock optimisticLock;

    public DishController(IDishService dishService, OptimisticLock optimisticLock) {
        this.dishService = dishService;
        this.optimisticLock = optimisticLock;
    }

    @GetMapping
    public ResponseEntity<?> getDishList(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                  @RequestParam(value = "name",required = false) String name) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            Page<Dish> dishList = dishService.getDishList(pageable, name);
            return new ResponseEntity<>(dishList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Список блюд пуст",HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addDish (@RequestBody DishDTO dishDTO) {
        dishService.addDish(dishDTO);
        return new ResponseEntity<>("Блюдо успешно добавлено",HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDishById(@PathVariable("id") Long id) {
        try {
            Dish dish = dishService.getDish(id);
            return new ResponseEntity<>(dish, HttpStatus.OK);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") long dtUpdate) {
        try {
            optimisticLock.dishOptimisticLock(dtUpdate,id);
            dishService.deleteDish(id);
            return new ResponseEntity<>("Блюдо успешно удалено",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
