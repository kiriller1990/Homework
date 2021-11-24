package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.DishDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IDishService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
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

    public DishController(IDishService dishService) {
        this.dishService = dishService;
    }

    @GetMapping
    public ResponseEntity<?> getDishList(@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                  @RequestParam(value = "name",required = false) String name) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        Page<Dish> dishList = dishService.getDishList(pageable, name);
        return  dishList != null &&  dishList.isEmpty()
                ? new ResponseEntity<>(dishList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<List<Dish>> addDish (@RequestBody DishDTO dishDTO) {
        dishService.addDish(dishDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dish> getProductById(@PathVariable("id") Long id) {
        Dish dish = dishService.getDish(id);
        return dish != null
                ? new ResponseEntity<>(dish, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateDish(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Dish dish) {
        dishService.updateDish(dish, id,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDish(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        dishService.deleteDish(id,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
