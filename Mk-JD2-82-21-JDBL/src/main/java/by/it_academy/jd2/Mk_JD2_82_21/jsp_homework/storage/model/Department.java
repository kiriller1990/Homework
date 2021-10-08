package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "parental_department", referencedColumnName = "id")
    private Department parentDepartment;

    public  Department() {}

    public Department(String title) {
        this.title = title;
    }

    public Department(long id,String title) {
        this.id= id;
        this.title = title;
    }


    public Department(long id, String title,Department parentDepartment) {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }
}
