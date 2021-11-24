package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EEatingTimeType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FoodDiaryDTO {
    private long id;
    private Profile profile;
    private EEatingTimeType eatingTimeType;
    private Product product;
    private Dish dish;
    private double weight;
    private double caloriesReceived;
    private double proteinsReceived;
    private double fatsReceived;
    private double carbohydratesReceived;
    private LocalDate date;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public FoodDiaryDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public EEatingTimeType getEatingTimeType() {
        return eatingTimeType;
    }

    public void setEatingTimeType(EEatingTimeType eatingTimeType) {
        this.eatingTimeType = eatingTimeType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCaloriesReceived() {
        return caloriesReceived;
    }

    public void setCaloriesReceived(double caloriesReceived) {
        this.caloriesReceived = caloriesReceived;
    }

    public double getProteinsReceived() {
        return proteinsReceived;
    }

    public void setProteinsReceived(double proteinsReceived) {
        this.proteinsReceived = proteinsReceived;
    }

    public double getFatsReceived() {
        return fatsReceived;
    }

    public void setFatsReceived(double fatsReceived) {
        this.fatsReceived = fatsReceived;
    }

    public double getCarbohydratesReceived() {
        return carbohydratesReceived;
    }

    public void setCarbohydratesReceived(double carbohydratesReceived) {
        this.carbohydratesReceived = carbohydratesReceived;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
