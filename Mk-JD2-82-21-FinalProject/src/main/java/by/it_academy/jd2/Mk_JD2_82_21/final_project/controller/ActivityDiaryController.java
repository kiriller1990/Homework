package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
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
@RequestMapping("/api/profile/{id_profile}/journal/active/")
public class ActivityDiaryController {
    private final IActivityDiaryService activityDiaryService;
    private final OptimisticLock optimisticLock;

    public ActivityDiaryController(IActivityDiaryService activityDiaryService, OptimisticLock optimisticLock) {
        this.activityDiaryService = activityDiaryService;
        this.optimisticLock = optimisticLock;
    }

    @GetMapping
    public ResponseEntity<?> getListOfActivity(@PathVariable("id_profile") long idProfile,
                                                @RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                @RequestParam("dt_start")long dtStart,
                                                @RequestParam("dt_end") long dtEnd) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
            long dtEndSerach = dtEnd+5;
            Page<ActivityDiary> ListOfActivity = activityDiaryService.getActivityDiaryList
                    (pageable,idProfile, dtStart, dtEndSerach);
            return new ResponseEntity<>(ListOfActivity, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public  ResponseEntity<?> addActivityDiary (@RequestBody ActivityDiary activityDiary,
                                                     @PathVariable("id_profile") long idProfile) {
        try {
            activityDiaryService.addActivityDiary(activityDiary, idProfile);
            return new ResponseEntity<>("Активность добавлена",HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id_active}")
    public ResponseEntity<?> getActivityCard (@PathVariable("id_active") long idActive,
                                              @PathVariable("id_profile") long idProfile) {
        try {
            ActivityDiary activityDiary = activityDiaryService.getActivityDiaryCard(idActive);
            return new ResponseEntity<>(activityDiary, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> updateActivity (@PathVariable("id_active") long idActive,
                                              @PathVariable("id_profile") long idProfile,
                                              @PathVariable("dt_update") long dtUpdate,
                                              @RequestBody ActivityDiary activityDiary) {
        try {
            optimisticLock.activityDiaryOptimisticLock(dtUpdate,idActive);
            activityDiaryService.updateActivityDiary(activityDiary,idProfile,idActive);
            return new ResponseEntity<>("Активность обновлена",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id_active}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteActivity(@PathVariable("id_active") long idActive,
                                             @PathVariable("id_profile") long idProfile,
                                             @PathVariable("dt_update") long dtUpdate) {
        try {
            optimisticLock.activityDiaryOptimisticLock(dtUpdate,idActive);
            activityDiaryService.deleteActivityDiary(idActive);
            return new ResponseEntity<>("Активность удалена",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
