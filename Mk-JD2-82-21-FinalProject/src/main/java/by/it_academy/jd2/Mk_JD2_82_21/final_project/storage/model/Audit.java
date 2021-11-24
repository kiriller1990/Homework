package by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @Column (name = "action_information")
    private String actionInformation;
    @Column(name = "entityType")
    private EntityType entityType;
    @Column (name= "id_entity_on_witch_the_action_is_performed")
    private long idEntityOnWithTheActionIsPerformed;
    @Column
    private LocalDateTime dateOfCreate;


    public Audit() {
    }

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

    public String getActionInformation() {
        return actionInformation;
    }

    public void setActionInformation(String actionInformation) {
        this.actionInformation = actionInformation;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public long getIdEntityOnWithTheActionIsPerformed() {
        return idEntityOnWithTheActionIsPerformed;
    }

    public void setIdEntityOnWithTheActionIsPerformed(long idEntityOnWithTheActionIsPerformed) {
        this.idEntityOnWithTheActionIsPerformed = idEntityOnWithTheActionIsPerformed;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}
