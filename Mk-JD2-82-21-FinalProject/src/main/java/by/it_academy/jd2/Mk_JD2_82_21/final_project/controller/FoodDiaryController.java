package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.FoodDiaryDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IFoodDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
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

    public FoodDiaryController(IFoodDiaryService foodDiaryService) {
        this.foodDiaryService = foodDiaryService;
    }

    @GetMapping
    public ResponseEntity<?> getFoodDiaryList (@PathVariable("id_profile") Long idProfile,
                                                             @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(value = "size", required = false, defaultValue = "30") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<FoodDiary> foodDiaries = foodDiaryService.getFoodDiaryList(pageable , idProfile);
        return new ResponseEntity<>(foodDiaries , HttpStatus.OK);
    }

    @GetMapping(value = "{day}")
    public ResponseEntity<List<FoodDiary>> getAllMealsInADay (@PathVariable("day") long day,
                                                              @PathVariable("id_profile") long id_profile) {

        List<FoodDiary> foodDiaryList = foodDiaryService.getAllMealsInADay(day,id_profile) ;
        return foodDiaryList != null &&  !foodDiaryList.isEmpty()
                ? new ResponseEntity<>(foodDiaryList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  ResponseEntity<?> addFoodDiary (@RequestBody FoodDiary foodDiary,
                                                         @RequestParam("id_profile") long id_profile){
        try{
            foodDiaryService.addFoodDiary(foodDiary, id_profile);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalCallerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "{id_food}")
    public ResponseEntity<FoodDiaryDTO> getFoodDiaryCard(@RequestParam("id_profile") long idProfile,
                                                         @RequestParam("id_food") long idFood) {
        FoodDiaryDTO foodDiary = foodDiaryService.getFoodDiaryCard(idProfile, idFood);
        return foodDiary != null
                ? new ResponseEntity<>(foodDiary, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id_food}/dt_update/{dt_update}")
    public ResponseEntity<?> updateDiary(@RequestParam FoodDiary foodDiary,
                                         @RequestParam("id_profile") long idProfile,
                                         @RequestParam("id_food") long idFood,
                                         @RequestParam("dt_update")LocalDateTime dt_update) {
        foodDiaryService.updateFoodDiary(foodDiary,dt_update,idProfile,idFood);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
