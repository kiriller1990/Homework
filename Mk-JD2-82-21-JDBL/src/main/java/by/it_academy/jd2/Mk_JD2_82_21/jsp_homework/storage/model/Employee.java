package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @Column
    private double salary;
    private String title;
    private  String position;

    private Employee(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Employee(long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Employee(long id, String name, double salary, String title, String position) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.title = title;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public String getPosition() {
        return position;
    }
}

