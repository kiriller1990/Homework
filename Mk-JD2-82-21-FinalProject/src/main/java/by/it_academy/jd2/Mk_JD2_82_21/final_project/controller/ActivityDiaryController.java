package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/active/")
public class ActivityDiaryController {
    private static IActivityDiaryService activityDiaryService;

    public ActivityDiaryController(IActivityDiaryService activityDiaryService) {
        this.activityDiaryService = activityDiaryService;
    }

    @GetMapping
    public ResponseEntity<?> getActivityList (@PathVariable("id_profile") Long idProfile,
                                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                                @RequestParam(value = "size", required = false, defaultValue = "30") Integer size,
                                                                @PathVariable("dt_start") LocalDate dtStart,
                                                                @PathVariable("dt_end") LocalDate dtEnd) {
        Pageable pageable = PageRequest.of(page, size);
        Page<FoodDiary> foodDiaries = activityDiaryService.t(pageable , idProfile);
        return new ResponseEntity<>(foodDiaries , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addActivityDiary(@RequestBody ActivityDiary activityDiary,
                                              @RequestParam("id_profile") long id_profile) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
