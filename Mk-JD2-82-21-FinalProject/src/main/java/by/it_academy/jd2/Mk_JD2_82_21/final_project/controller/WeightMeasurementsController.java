package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IWeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/profile/{id_profile}/journal/weight/")
public class WeightMeasurementsController {
    private final IWeightMeasurementsService weightMeasurementsService;

    public WeightMeasurementsController(IWeightMeasurementsService weightMeasurementsService) {
        this.weightMeasurementsService = weightMeasurementsService;
    }

    @GetMapping
    public ResponseEntity<List<WeightMeasurements>> getListOfWeighings(@RequestParam("id_profile") long id_profile,
                                                                       @PathVariable("page") int page,
                                                                       @PathVariable("size") int size,
                                                                       @PathVariable("dt_start")LocalDate dt_start,
                                                                       @PathVariable("dt_end") LocalDate dt_end) {
        List<WeightMeasurements> ListOfWeighings = weightMeasurementsService.getWeightMeasurementsList(id_profile, dt_start, dt_end);
        return  ListOfWeighings != null &&  ListOfWeighings.isEmpty()
                ? new ResponseEntity<>(ListOfWeighings, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public  ResponseEntity<List<WeightMeasurements>> addWeightMeasurements (@RequestBody WeightMeasurements weightMeasurements,
                                                          @RequestParam("id_profile") long id_profile) {
        weightMeasurementsService.addWeightMeasurements(weightMeasurements, id_profile);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{id_weight}")
    public ResponseEntity<WeightMeasurements> getWeighingCard (@RequestParam("id_weight") long id_weight,
                                                               @RequestParam("id_profile") long id_profile) {
        WeightMeasurements weightMeasurements = weightMeasurementsService.getWeightMeasurementsCard(id_profile,id_weight);
        return weightMeasurements != null
                ? new ResponseEntity<>(weightMeasurements, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> putWeighting (@RequestParam("id_weight") long id_weight,
                                           @RequestParam("dt_update") LocalDateTime dt_update,
                                           @RequestParam("id_profile") long id_profile,
                                           @RequestParam WeightMeasurements weightMeasurements) {
        weightMeasurementsService.updateWeightMeasurements(weightMeasurements, id_profile,id_weight, dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id_weight}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteWeighting(@RequestParam("id_weight") long id_weight,
                                             @RequestParam("dt_update") LocalDateTime dt_update,
                                             @RequestParam("id_profile") long id_profile) {
        weightMeasurementsService.deleteWeightMeasurements(id_profile,id_weight,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
