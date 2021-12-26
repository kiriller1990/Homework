package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IWeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
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
@RequestMapping("/api/profile/{id_profile}/journal/weight")
public class WeightMeasurementsController {
    private final IWeightMeasurementsService weightMeasurementsService;
    private final OptimisticLock optimisticLock;

    public WeightMeasurementsController(IWeightMeasurementsService weightMeasurementsService,
                                        OptimisticLock optimisticLock) {
        this.weightMeasurementsService = weightMeasurementsService;
        this.optimisticLock = optimisticLock;
    }

    @GetMapping
    public ResponseEntity<?> getListOfWeighings(@PathVariable("id_profile") long idProfile,
                                                                       @RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                                       @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                                       @RequestParam("dt_start")long dt_start,
                                                                       @RequestParam("dt_end") long dt_end) {
        try {
            Pageable pageable = PageRequest.of(page, size, Sort.by("dateOfWeighings"));
            Page<WeightMeasurements> ListOfWeighings = weightMeasurementsService.getWeightMeasurementsList
                                                                        (pageable,idProfile, dt_start, dt_end);
            return new ResponseEntity<>(ListOfWeighings, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public  ResponseEntity<?> addWeightMeasurements (@RequestBody WeightMeasurements weightMeasurements,
                                                          @PathVariable("id_profile") long idProfile) {
        try {
            weightMeasurementsService.addWeightMeasurements(weightMeasurements, idProfile);
            return new ResponseEntity<>("Взвешивание добавлено",HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping(value = "/{id_weight}")
    public ResponseEntity<?> getWeighingCard (@PathVariable("id_weight") long idWeight,
                                                               @PathVariable("id_profile") long idProfile) {
        try {
            WeightMeasurements weightMeasurements = weightMeasurementsService.getWeightMeasurementsCard(idWeight);
            return new ResponseEntity<>(weightMeasurements, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> updateWeighting (@PathVariable("id_weight") long idWeight,
                                              @PathVariable("dt_update") long dtUpdate,
                                              @RequestBody WeightMeasurements weightMeasurements) {
        try {
            optimisticLock.weightMeasurementsOptimisticLock(dtUpdate,idWeight);
            weightMeasurementsService.updateWeightMeasurements(weightMeasurements,idWeight);
            return new ResponseEntity<>("Взвешивание обновлено",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(value = "/{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteWeighting(@PathVariable("id_weight") long idWeight,
                                             @PathVariable("dt_update") long dtUpdate) {
        try {
            optimisticLock.weightMeasurementsOptimisticLock(dtUpdate,idWeight);
            weightMeasurementsService.deleteWeightMeasurements(idWeight);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
