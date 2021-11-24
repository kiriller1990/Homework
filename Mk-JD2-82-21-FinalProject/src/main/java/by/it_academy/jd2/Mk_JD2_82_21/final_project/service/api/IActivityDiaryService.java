package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;


import java.time.LocalDate;
import java.util.List;

public interface IActivityDiaryService {
    public void addActivityDiary(ActivityDiary activityDiary, long id_profile);
    public ActivityDiary getActivityDiary(long activityDiaryId);
    public List<ActivityDiary> getActivityDiaryList(long id_profile, LocalDate dt_start, LocalDate dt_end);
    void updateActivityDiary(ActivityDiary activityDiary, long id);
    void deleteActivityDiary(long id);
}
