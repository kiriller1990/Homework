package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

public class Position {
    private long id;
    private String position;

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
