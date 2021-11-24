package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Dish")
public class  Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @OneToMany
    private List<Ingredient> ingredients;
    @OneToOne
    private User userWhoMadeTheEntry;
    @Column
    private LocalDateTime createDate;
    @Column
    private LocalDateTime updateDate;

    public Dish(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public User getUserWhoMadeTheEntry() {
        return userWhoMadeTheEntry;
    }

    public void setUserWhoMadeTheEntry(User userWhoMadeTheEntry) {
        this.userWhoMadeTheEntry = userWhoMadeTheEntry;
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
