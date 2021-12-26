package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EEatingTimeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="food_diary")
public class FoodDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Profile profile;
    @Column(name = "eating_time_type")
    @Enumerated(EnumType.STRING)
    private EEatingTimeType eatingTimeType;
    @OneToOne
    private Product product;
    @OneToOne
    private Dish dish;
    @Column
    private int weight;

    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime updateDate;

    public FoodDiary() {
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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
