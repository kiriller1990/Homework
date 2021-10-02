package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBEmployeWithCriteria;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;

import java.util.List;

public class EmployeeService {
    private static final EmployeeService instance = new EmployeeService();

    public long addEmployee(String name, double salary) {
        return DBEmployeeStorage.getInstance().addEmployee(name, salary);
    }

    public long addEmployee(String name, double salary, long department, long position) {
        return DBEmployeeStorage.getInstance().addEmployee(name, salary, department, position);
    }

    public Employee getEmployeeCard(String idEmployee){
        return DBEmployeeStorage.getInstance().getEmployeeCard(idEmployee);
    }

    public List<Employee> getEmployees(long page , long employeeInOnePage) {
        String offset = String.valueOf(getOffsetParameter(page,employeeInOnePage));
        String employeeInOnePageString = String.valueOf(employeeInOnePage);
        return DBEmployeeStorage.getInstance().getEmployeeList(offset,employeeInOnePageString);

    }

    public List<Employee> getEmployeesSortedByDepartment(String parameter){
        return DBEmployeeStorage.getInstance().getEmployeeListSortedByDepartment(parameter);
    }

    public List<Employee> getEmployeesSortedByPosition(String parameter){
        return DBEmployeeStorage.getInstance().getEmployeeListSortedByPosition(parameter);
    }



    public long getTheNumberOfPages(long employeesInOnePage) {
        long numbersOfPages ;
        double employeesInOnePageDouble = (double) employeesInOnePage;
        double EmployeesCount = (double) DBEmployeeStorage.getInstance().getEmployeeCount();
        return numbersOfPages= (long) Math.ceil(EmployeesCount/employeesInOnePageDouble);

    }

    public long getOffsetParameter(Long page,long employeeInOnePage) {
        long employeeListUsed=employeeInOnePage;
        long requiredEmployeeOffset = (page*employeeInOnePage) - employeeListUsed;
        return requiredEmployeeOffset;
    }

    public List<Employee> getEmployeeFilteredByNameOrSalary(Search search) {
       return DBEmployeWithCriteria.getInstance().getSortedListOfEmployees(search);
    }

    public long gerTheNumberOfPagesAfterFilteredByEmployeeOrSalary (List<Employee> employees,long employeesInOnePage){
        long numbersOfPages ;
        long amountOfEmployees = (long) employees.size();
        double amountOfEmployeesDouble = (double) amountOfEmployees;
        double employeesInOnePageDouble = (double) employeesInOnePage;
        return numbersOfPages= (long) Math.ceil(amountOfEmployeesDouble/employeesInOnePageDouble);
    }

    public static EmployeeService getInstance() {
        return instance;
    }
}
