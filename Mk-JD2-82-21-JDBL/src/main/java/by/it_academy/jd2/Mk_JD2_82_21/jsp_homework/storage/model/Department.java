package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

public class Department {
    private long id;
    private String title;
    private String parentDepartment;

    public Department(String title) {
        this.title = title;
    }

    public Department(long id,String title) {
        this.id= id;
        this.title = title;
    }


    public Department(long id, String title,String parentDepartment) {
        this.id = id;
        this.title = title;
        this.parentDepartment = parentDepartment;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }
}
