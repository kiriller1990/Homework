package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IWeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IWeightMeasurementsDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeightMeasurementsService implements IWeightMeasurementsService {
    private static IWeightMeasurementsDAO weightMeasurementsDAO;
    private static IProfileService profileService;

    public WeightMeasurementsService(IWeightMeasurementsDAO weightMeasurementsDAO, IProfileService profileService) {
        this.weightMeasurementsDAO = weightMeasurementsDAO;
        this.profileService = profileService;
    }

    @Override
    public void addWeightMeasurements(WeightMeasurements weightMeasurements, long id_profile) {
        LocalDateTime timeStamp = LocalDateTime.now();
        weightMeasurements.setCreateDate(timeStamp);
        weightMeasurements.setUpdateDate(timeStamp);
        weightMeasurements.setProfile(profileService.getProfile(id_profile));
        weightMeasurementsDAO.save(weightMeasurements);
    }

    @Override
    public WeightMeasurements getWeightMeasurementsCard(long id_profile, long id_weight) {
        return weightMeasurementsDAO.findWeightMeasurementsByProfileIdAndId(id_profile,id_weight);
    }

    @Override
    public List<WeightMeasurements> getWeightMeasurementsList(long id_profile, LocalDate dt_start, LocalDate dt_end) {
        return weightMeasurementsDAO.findWeightMeasurementsByProfileIdAndDateOfWeighingsBetween(id_profile,
                                                                                                    dt_start,dt_end);
    }

    @Override
    public void updateWeightMeasurements(WeightMeasurements weightMeasurements, long id_profile,
                                         long id_weight,LocalDateTime dt_update) {
        LocalDateTime updateTime = LocalDateTime.now();
        weightMeasurements.setUpdateDate(updateTime);
        WeightMeasurements updateWeightMeasurements = getWeightMeasurementsCard(id_profile,id_weight);
        updateWeightMeasurements.setProfile(weightMeasurements.getProfile());
        updateWeightMeasurements.setWeight(weightMeasurements.getWeight());
        updateWeightMeasurements.setUpdateDate(weightMeasurements.getUpdateDate());
        weightMeasurementsDAO.save(updateWeightMeasurements);
    }

    @Override
    public void deleteWeightMeasurements(long id_profile,
                                         long id_weight,LocalDateTime dt_update) {
        weightMeasurementsDAO.deleteWeightMeasurementsByProfileIdAndId(id_profile,id_weight);
    }
}
