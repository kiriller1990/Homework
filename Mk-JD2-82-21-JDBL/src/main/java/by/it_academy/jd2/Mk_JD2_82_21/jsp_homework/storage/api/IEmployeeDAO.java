package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import java.util.List;

public interface IEmployeeDAO {

    public long addEmployee(Employee employee);
    public Employee getEmployeeCard(String idEmployee);
    public long getEmployeeCount ();
    public List<Employee> getEmployeeList(String offset , String employeeInOnePage);
    public List<Employee> getEmployeeListSortedByDepartment(String parameter);
    public List<Employee> getEmployeeListSortedByPosition(String parameter);

}
