package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.IEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.EmployeeStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate.DBEmployeWithCriteria;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBEmployeeStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate.EmployeeDAOImpl;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IEmployeeDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;

import java.util.List;

public class EmployeeService implements IEmployeeService {
    private static final EmployeeService instance = new EmployeeService();
    private IEmployeeDAO iEmployeeDAO;

    public EmployeeService() {
        this.iEmployeeDAO = EmployeeStorageFactory.getInstance();
    }

    @Override
    public long addEmployee(Employee employee) {
        return iEmployeeDAO.addEmployee(employee);
    }

    public Employee getEmployeeCard(String idEmployee){
        return iEmployeeDAO.getEmployeeCard(idEmployee);
    }
    @Override
    public List<Employee> getEmployees(long page , long employeeInOnePage) {
        String offset = String.valueOf(getOffsetParameter(page,employeeInOnePage));
        String employeeInOnePageString = String.valueOf(employeeInOnePage);
        return iEmployeeDAO.getEmployeeList(offset,employeeInOnePageString);
    }

    @Override
    public List<Employee> getEmployeesSortedByDepartment(String parameter){
        return iEmployeeDAO.getEmployeeListSortedByDepartment(parameter);
    }

    @Override
    public List<Employee> getEmployeesSortedByPosition(String parameter){
        return iEmployeeDAO.getEmployeeListSortedByPosition(parameter);
    }

    @Override
    public long getTheNumberOfPages(long employeesInOnePage) {
        long numbersOfPages ;
        double employeesInOnePageDouble = (double) employeesInOnePage;
        double EmployeesCount = (double) DBEmployeeStorage.getInstance().getEmployeeCount();
        return numbersOfPages= (long) Math.ceil(EmployeesCount/employeesInOnePageDouble);
    }

    @Override
    public long getOffsetParameter(Long page,long employeeInOnePage) {
        long employeeListUsed=employeeInOnePage;
        long requiredEmployeeOffset = (page*employeeInOnePage) - employeeListUsed;
        return requiredEmployeeOffset;
    }

    //Переписать под интерфейс
    public List<Employee> getEmployeeFilteredByNameOrSalary(Search search) {
       return DBEmployeWithCriteria.getInstance().getSortedListOfEmployees(search);
    }

    @Override
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
