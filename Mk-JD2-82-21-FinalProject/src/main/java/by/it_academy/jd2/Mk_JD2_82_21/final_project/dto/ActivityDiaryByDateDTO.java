package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import org.springframework.data.domain.Page;

public class ActivityDiaryByDateDTO {
    private Page<ActivityDiary> activityDiariesList;
    private double caloriesSum;

    public ActivityDiaryByDateDTO() {
    }

    public Page<ActivityDiary> getActivityDiariesList() {
        return activityDiariesList;
    }

    public void setActivityDiariesList(Page<ActivityDiary> activityDiariesList) {
        this.activityDiariesList = activityDiariesList;
    }

    public double getCaloriesSum() {
        return caloriesSum;
    }

    public void setCaloriesSum(double caloriesSum) {
        this.caloriesSum = caloriesSum;
    }
}
