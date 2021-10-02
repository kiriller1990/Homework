package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

public class Search {
    private String nameSearch;
    private double salary;
    private String typeOfSalaryFilter;

    public Search (String nameSearch,double salary,String typeOfSalaryFilter) {
        this.nameSearch = nameSearch;
        this.salary = salary;
        this.typeOfSalaryFilter = typeOfSalaryFilter;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getTypeOfSalaryFilter() {
        return typeOfSalaryFilter;
    }

    public void setTypeOfSalaryFilter(String typeOfSalaryFilter) {
        this.typeOfSalaryFilter = typeOfSalaryFilter;
    }
}
