package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IFoodDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.OptimisticLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/food/")
public class FoodDiaryController {
    private final IFoodDiaryService foodDiaryService;
    private final OptimisticLock optimisticLock;

    public FoodDiaryController(IFoodDiaryService foodDiaryService, OptimisticLock optimisticLock) {
        this.foodDiaryService = foodDiaryService;
        this.optimisticLock = optimisticLock;
    }

    @GetMapping
    public ResponseEntity<?> getFoodDiaryList (@PathVariable("id_profile") Long idProfile,
                                                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", required = false, defaultValue = "30") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<FoodDiary> foodDiaries = foodDiaryService.getFoodDiaryList(pageable , idProfile);
            return new ResponseEntity<>(foodDiaries , HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage() , HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping(value = "byDay/{day}")
    public ResponseEntity<?> getAllMealsInADay (@PathVariable("day") long day,
                                                @PathVariable("id_profile") long idProfile) {
        try {
            List<FoodDiaryDTO> foodDiaryList = foodDiaryService.getAllMealsInADay(day,idProfile) ;
            return new ResponseEntity<>(foodDiaryList, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public  ResponseEntity<?> addFoodDiary (@RequestBody FoodDiary foodDiary,
                                            @PathVariable("id_profile") long idProfile){
        try{
            foodDiaryService.addFoodDiary(foodDiary, idProfile);
            return new ResponseEntity<>("Дневник питания успешно добавлен",HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "{id_food}")
    public ResponseEntity<?> getFoodDiaryCard(@PathVariable("id_profile") long idProfile,
                                              @PathVariable("id_food") long idFood) {
        try {
            FoodDiaryDTO foodDiary = foodDiaryService.getFoodDiaryCard(idProfile, idFood);
            return new ResponseEntity<>(foodDiary, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
                return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> updateDiary(@RequestBody FoodDiary foodDiary,
                                         @PathVariable("id_profile") long idProfile,
                                         @PathVariable("id_food") long idFood,
                                         @PathVariable("dt_update")long dtUpdate) {
        try {
            optimisticLock.foodDiaryOptimisticLock(idFood,idProfile,dtUpdate);
            foodDiaryService.updateFoodDiary(foodDiary,idProfile,idFood);
            return new ResponseEntity<>("Дневник питания успешно обновлен",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping(value = "{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteDiary( @PathVariable("id_profile") long idProfile,
                                          @PathVariable("id_food") long idFood,
                                          @PathVariable("dt_update")long dtUpdate) {
        try {
            optimisticLock.foodDiaryOptimisticLock(idFood,idProfile,dtUpdate);
            foodDiaryService.deleteFoodDiary(idProfile,idFood);
            return new ResponseEntity<>("Дневник питания удален",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }
}
