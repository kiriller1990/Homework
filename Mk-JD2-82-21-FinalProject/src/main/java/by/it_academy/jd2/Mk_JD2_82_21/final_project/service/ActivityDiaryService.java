package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IActivityDiaryDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class ActivityDiaryService implements IActivityDiaryService {
    private static IActivityDiaryDAO activityDiaryDAO;
    private static IProfileService profileService;

    public ActivityDiaryService(IActivityDiaryDAO activityDiaryDAO) {
        this.activityDiaryDAO = activityDiaryDAO;
        this.profileService = profileService;
    }

    @Override
    public void addActivityDiary(ActivityDiary activityDiary, long id_profile) {
        activityDiary.setProfile(profileService.getProfile(id_profile));
        activityDiaryDAO.save(activityDiary);
    }

    @Override
    public ActivityDiary getActivityDiary(long activityDiaryId) {
        return activityDiaryDAO.findById(activityDiaryId).orElseThrow(()-> new IllegalArgumentException ("По" +
                " данному ID дневник активности не найден"));
    }

    @Override
    public List<ActivityDiary> getActivityDiaryList(long id_profile, LocalDate dt_start,LocalDate dt_end) {
        return activityDiaryDAO.findActivityDiaryByProfileIdAndDateOfActivityBetween(id_profile,dt_start, dt_end);
    }

    @Override
    public void updateActivityDiary(ActivityDiary activityDiary, long id) {
        ActivityDiary updateActivityDiary = getActivityDiary(id);
        updateActivityDiary.setProfile(activityDiary.getProfile());
        updateActivityDiary.setActivityType(activityDiary.getActivityType());
        updateActivityDiary.setCalories(activityDiary.getCalories());
        activityDiaryDAO.save(updateActivityDiary);


    }

    @Override
    public void deleteActivityDiary(long id) {
        activityDiaryDAO.deleteById(id);
    }
}
