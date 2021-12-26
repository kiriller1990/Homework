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
    @Column(name = "action_information")
    private String actionInformation;
    @Column(name = "entityType")
    private String entityType;
    @Column(name = "entity_id")
    private long entityId;
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public LocalDateTime getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(LocalDateTime dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
}

