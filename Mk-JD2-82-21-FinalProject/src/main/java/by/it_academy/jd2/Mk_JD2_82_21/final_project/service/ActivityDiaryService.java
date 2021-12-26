package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IActivityDiaryDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
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
public class ActivityDiaryService implements IActivityDiaryService {
    private final IActivityDiaryDAO activityDiaryDAO;
    private final IProfileService profileService;
    private final TimeConverter timeConverter;
    private final UserHolder userHolder;
    private final CheckUtil checkUtil;

    public ActivityDiaryService(IActivityDiaryDAO activityDiaryDAO, IProfileService profileService,
                                TimeConverter timeConverter, UserHolder userHolder, CheckUtil checkUtil) {
        this.activityDiaryDAO = activityDiaryDAO;
        this.profileService = profileService;
        this.timeConverter = timeConverter;
        this.userHolder = userHolder;
        this.checkUtil = checkUtil;
    }

    @Override
    public ActivityDiary addActivityDiary(ActivityDiary activityDiary, long idProfile) {
        User user = userHolder.getUser();
        User userFromProfile = profileService.getProfile(idProfile).getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            LocalDateTime timeStamp = LocalDateTime.now();
            LocalDate dateStamp = LocalDate.now();
            activityDiary.setDateOfActivity(dateStamp);
            activityDiary.setCreateDate(timeStamp);
            activityDiary.setUpdateDate(timeStamp);
            activityDiary.setProfile(profileService.getProfile(idProfile));
            return activityDiaryDAO.save(activityDiary);

        } else {
            throw new IllegalArgumentException("вы можете добавлять активности только в свой профиль");
        }
    }

    @Override
    public ActivityDiary getActivityDiary(long activityDiaryId) {
        return activityDiaryDAO.findById(activityDiaryId).orElseThrow(()-> new IllegalArgumentException ("По" +
                " данному ID дневник активности не найден"));
    }

    @Override
    public ActivityDiary getActivityDiaryCard(long idActive) {
        User user = userHolder.getUser();
        ActivityDiary activityDiary = activityDiaryDAO.findById(idActive).orElseThrow(()->
                new IllegalArgumentException("По данному ID активность не найдена"));
        User userFromProfile = activityDiary.getProfile().getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            return activityDiary;
        } else {
            throw new IllegalArgumentException("вы можете получать только свои карточки активности");
        }
    }

    @Override
    public Page<ActivityDiary> getActivityDiaryList(Pageable pageable, long idProfile, long dtStart, long dtEnd) {
        User user = userHolder.getUser();
        User userFromProfile = profileService.getProfile(idProfile).getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            LocalDateTime startDate = timeConverter.convertFromMillsToLocalDateTime(dtStart);
            LocalDateTime endDay = timeConverter.convertFromMillsToLocalDateTime(dtEnd);
            return activityDiaryDAO.findActivityDiaryByProfileIdAndUpdateDateBetween( pageable,idProfile,
                    startDate, endDay);
        } else {
            throw new IllegalArgumentException("вы можете получать только свои дневники активности");
        }
    }

    @Override
    public void updateActivityDiary(ActivityDiary activityDiary, long idProfile, long idActive) {
        User user = userHolder.getUser();
        User userFromProfile = profileService.getProfile(idProfile).getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            ActivityDiary updateActivityDiary = getActivityDiary(idActive);
            LocalDateTime updateTime = LocalDateTime.now();
            updateActivityDiary.setUpdateDate(updateTime);
            updateActivityDiary.setDateOfActivity(LocalDate.now());
            updateActivityDiary.setProfile(profileService.getProfile(idProfile));
            updateActivityDiary.setActivityType(activityDiary.getActivityType());
            updateActivityDiary.setCalories(activityDiary.getCalories());
            activityDiaryDAO.save(updateActivityDiary);
        } else {
            throw new IllegalArgumentException("Вы можете обновлять только свои активности");
        }
    }

    @Override
    public void deleteActivityDiary(long idActive) {
        User user = userHolder.getUser();
        User userFromProfile = getActivityDiaryCard(idActive).getProfile().getUser();
        if(user == userFromProfile || checkUtil.isAdminRoleCheck()) {
            activityDiaryDAO.deleteById(idActive);
        } else {
            throw new IllegalArgumentException("Вы можете удалять только свои активности");
        }
    }
}
