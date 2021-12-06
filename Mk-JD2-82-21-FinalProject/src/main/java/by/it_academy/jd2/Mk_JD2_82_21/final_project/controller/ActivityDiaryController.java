package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
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
    public ResponseEntity<List<ActivityDiary>> getActivityList (@RequestParam("id_profile") long id_profile,
                                                                @PathVariable("page") int page,
                                                                @PathVariable("size") int size,
                                                                @PathVariable("dt_start") LocalDate dt_start,
                                                                @PathVariable("dt_end") LocalDate dt_end) {
        List<ActivityDiary> activityDiaryController = activityDiaryService.getActivityDiaryList(id_profile,dt_start,dt_end);
        return activityDiaryController != null &&  !activityDiaryController.isEmpty()
                ? new ResponseEntity<>(activityDiaryController, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addActivityDiary(@RequestBody ActivityDiary activityDiary,
                                              @RequestParam("id_profile") long id_profile) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
