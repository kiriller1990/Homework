package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao;


import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IWeightMeasurementsDAO extends JpaRepository<WeightMeasurements, Long> {
    List<WeightMeasurements> findWeightMeasurementsByProfileIdAndDateOfWeighingsBetween(long id_profile,
                                                                                          LocalDate dt_start,
                                                                                            LocalDate dt_end);
    WeightMeasurements findWeightMeasurementsByProfileIdAndId(long id_profile,long id_weight);

    WeightMeasurements deleteWeightMeasurementsByProfileIdAndId(long id_profile,long id_weight);
}
