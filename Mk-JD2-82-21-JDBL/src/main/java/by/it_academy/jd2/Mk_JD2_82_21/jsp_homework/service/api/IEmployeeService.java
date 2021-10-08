package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;

import java.util.List;

public interface IEmployeeService {
    public long addEmployee(Employee employee);
    public Employee getEmployeeCard(String idEmployee);
    public List<Employee> getEmployees(long page , long employeeInOnePage);
    public List<Employee> getEmployeesSortedByDepartment(String parameter);
    public List<Employee> getEmployeesSortedByPosition(String parameter);
    public long getTheNumberOfPages(long employeesInOnePage);
    public long getOffsetParameter(Long page,long employeeInOnePage);
    public List<Employee> getEmployeeFilteredByNameOrSalary(Search search);
    public long gerTheNumberOfPagesAfterFilteredByEmployeeOrSalary (List<Employee> employees,long employeesInOnePage);

}
