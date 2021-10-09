package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "position")
    private String position;

    public Position(){}

    public Position(long id, String position) {
        this.id = id;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }
}
