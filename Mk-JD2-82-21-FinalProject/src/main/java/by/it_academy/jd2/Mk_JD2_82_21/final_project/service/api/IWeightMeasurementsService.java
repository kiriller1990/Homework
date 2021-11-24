package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IWeightMeasurementsService {
    public void addWeightMeasurements(WeightMeasurements weightMeasurements, long id_profile);
    public WeightMeasurements getWeightMeasurementsCard(long id_profile, long id_weight);
    public List<WeightMeasurements> getWeightMeasurementsList(long id_profile, LocalDate dt_start, LocalDate dt_end);
    public void updateWeightMeasurements(WeightMeasurements weightMeasurements, long id_profile,
                                         long id_weight, LocalDateTime dt_update);
    public void deleteWeightMeasurements(long id_profile,
                                         long id_weight,LocalDateTime dt_update);
}
