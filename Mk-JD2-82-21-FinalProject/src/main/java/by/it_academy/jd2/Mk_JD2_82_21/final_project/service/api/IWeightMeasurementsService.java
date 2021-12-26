package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IWeightMeasurementsService {
    public WeightMeasurements addWeightMeasurements(WeightMeasurements weightMeasurements, long id_profile);
    public WeightMeasurements getWeightMeasurementsCard( long idWeight);
    public Page<WeightMeasurements> getWeightMeasurementsList(Pageable pageable,long idProfile, long dtStart, long dtEnd);
    public void updateWeightMeasurements(WeightMeasurements weightMeasurements, long idWeight);
    public void deleteWeightMeasurements(long id_weight);
}
