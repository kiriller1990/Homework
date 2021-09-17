package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

public class Employee {
    private long id;
    private String name;
    private double salary;
    private String title;
    private  String position;

    private Employee(){

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

