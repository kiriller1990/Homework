package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "employes")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column (name = "salary")
    private double salary;
    private Department department;
    private  Position position;


    public Employee(){

    }

    public Employee(long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(long id, String name, double salary, Department department, Position position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

