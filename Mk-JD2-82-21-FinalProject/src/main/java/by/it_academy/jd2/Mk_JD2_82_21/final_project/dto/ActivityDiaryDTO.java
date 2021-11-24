package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;

public class ActivityDiaryDTO {
    private String name;
    private double calories;
    private Profile profile;

    public ActivityDiaryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
