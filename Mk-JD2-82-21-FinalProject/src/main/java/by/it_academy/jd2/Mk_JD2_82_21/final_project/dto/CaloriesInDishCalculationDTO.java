package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;


public class CaloriesInDishCalculationDTO {
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private double weight;

    public CaloriesInDishCalculationDTO() {
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
