package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EGoals;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ELifeStyle;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ESex;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name= "profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @Column
    private double height;
    @Column
    private double weight;
    @Column
    private double weightGoal;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateOfBirthday;
    @Column
    @Enumerated(EnumType.STRING)
    private ESex sex;
    @Column
    @Enumerated(EnumType.STRING)
    private ELifeStyle lifeStyle;
    @Column
    @Enumerated(EnumType.STRING)
    private EGoals goal;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime updateDate;

    public Profile(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public ESex getSex() {
        return sex;
    }

    public void setSex(ESex sex) {
        this.sex = sex;
    }

    public ELifeStyle getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(ELifeStyle lifeStyle) {
        this.lifeStyle = lifeStyle;
    }

    public EGoals getGoal() {
        return goal;
    }

    public void setGoal(EGoals goal) {
        this.goal = goal;
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
