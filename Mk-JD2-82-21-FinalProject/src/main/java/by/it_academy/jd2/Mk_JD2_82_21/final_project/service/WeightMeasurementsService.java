package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IWeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IWeightMeasurementsDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.CheckUtil;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.TimeConverter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WeightMeasurementsService implements IWeightMeasurementsService {
    private final IWeightMeasurementsDAO weightMeasurementsDAO;
    private final IProfileService profileService;
    private final UserHolder userHolder;
    private final CheckUtil checkUtil;
    private final TimeConverter timeConverter;


    public WeightMeasurementsService(IWeightMeasurementsDAO weightMeasurementsDAO, IProfileService profileService,
                                     UserHolder userHolder, CheckUtil checkUtil, TimeConverter timeConverter) {
        this.weightMeasurementsDAO = weightMeasurementsDAO;
        this.profileService = profileService;
        this.userHolder = userHolder;
        this.checkUtil = checkUtil;
        this.timeConverter = timeConverter;
    }

    @Override
    public WeightMeasurements addWeightMeasurements(WeightMeasurements weightMeasurements, long idProfile) {
        User user = userHolder.getUser();
        User userFromProfile = profileService.getProfile(idProfile).getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            LocalDateTime timeStamp = LocalDateTime.now();
            LocalDate dateStamp = LocalDate.now();
            weightMeasurements.setDateOfWeighings(dateStamp);
            weightMeasurements.setCreateDate(timeStamp);
            weightMeasurements.setUpdateDate(timeStamp);
            Profile getProfileToChangeTheWeight = profileService.getProfile(idProfile);
            getProfileToChangeTheWeight.setWeight(weightMeasurements.getWeight());
            weightMeasurements.setProfile(profileService.getProfile(idProfile));
            return  weightMeasurementsDAO.save(weightMeasurements);

        } else {
            throw new IllegalArgumentException("вы можете добавлять взвешивания только в свой профиль");
        }
    }

    @Override
    public WeightMeasurements getWeightMeasurementsCard( long idWeight) {
        User user = userHolder.getUser();
        WeightMeasurements weightMeasurements = weightMeasurementsDAO.findById(idWeight).orElseThrow(()->
                new IllegalArgumentException("По данному ID взвешивание не найдено"));
        User userFromProfile = weightMeasurements.getProfile().getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {

            return weightMeasurements;
        } else {
            throw new IllegalArgumentException("вы можете получать только свои карточки взвешивания");
        }
    }

    @Override
    public Page<WeightMeasurements> getWeightMeasurementsList(Pageable pageable,long idProfile, long dtStart, long dtEnd) {
        User user = userHolder.getUser();
        User userFromProfile = profileService.getProfile(idProfile).getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            LocalDateTime startDate = timeConverter.convertFromMillsToLocalDateTime(dtStart);
            LocalDateTime endDate = timeConverter.convertFromMillsToLocalDateTime(dtEnd);
            LocalDateTime endDay = endDate.plusDays(1);
            return weightMeasurementsDAO.findWeightMeasurementsByProfileIdAndUpdateDateBetween(pageable,idProfile,
                                                                                                    startDate, endDay);
        } else {
            throw new IllegalArgumentException("вы можете получать только свои карточки взвешивания");
        }
    }

    @Override
    public void updateWeightMeasurements(WeightMeasurements weightMeasurements,
                                         long idWeight) {
        User user = userHolder.getUser();
        User userFromProfile = getWeightMeasurementsCard(idWeight).getProfile().getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            WeightMeasurements updateWeightMeasurements = getWeightMeasurementsCard(idWeight);
            LocalDateTime updateTime = LocalDateTime.now();
            updateWeightMeasurements.setUpdateDate(updateTime);
            updateWeightMeasurements.setDateOfWeighings(LocalDate.now());
            updateWeightMeasurements.setWeight(weightMeasurements.getWeight());
            weightMeasurementsDAO.save(updateWeightMeasurements);
        } else {
            throw new IllegalArgumentException("Вы можете обновлять только свои взвешивания");
        }
    }

    @Override
    public void deleteWeightMeasurements(long idWeight) {
        User user = userHolder.getUser();
        User userFromProfile = getWeightMeasurementsCard(idWeight).getProfile().getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            weightMeasurementsDAO.deleteById(idWeight);
        } else {
            throw new IllegalArgumentException("Вы можете удалять только свои взвешивания");
        }
    }
}
