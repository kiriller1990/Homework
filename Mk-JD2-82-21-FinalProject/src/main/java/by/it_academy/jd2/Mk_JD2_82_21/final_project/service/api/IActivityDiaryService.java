package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;

public interface IActivityDiaryService {
    public ActivityDiary addActivityDiary(ActivityDiary activityDiary, long id_profile);
    public ActivityDiary getActivityDiary(long activityDiaryId);
    public ActivityDiary getActivityDiaryCard(long idActive);
    public Page<ActivityDiary> getActivityDiaryList(Pageable pageable, long idProfile, long dtStart, long dtEnd);
    public void updateActivityDiary(ActivityDiary activityDiary, long idProfile, long idActive);
    void deleteActivityDiary(long id);
}
