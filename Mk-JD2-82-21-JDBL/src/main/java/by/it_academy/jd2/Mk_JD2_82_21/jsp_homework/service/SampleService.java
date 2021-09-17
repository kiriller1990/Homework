package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService.DBDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.DBService.DBEmployeeService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

import java.util.List;

public class SampleService {
    private static final SampleService instance = new SampleService();

    public List<Employee> getEmployeesWithDepartmentSample(String parametr){
        String sqlDepartmentsSample = "SELECT employes.id, employes.name,employes.salary," +
                "departments.title,positions.position \n"+
                "FROM application.employes \n"+
                "JOIN application.departments ON employes.departments=departments.id \n"+
                "JOIN application.positions ON employes.position=positions.id \n" +
                "WHERE departments.id =" + parametr;
        return DBEmployeeService.getInstance().getEmployeeList(sqlDepartmentsSample);
    }

    public List<Employee> getEmployeesWithPositionSample(String parametr){
        String sqlPositionsSample = "SELECT employes.id, employes.name,employes.salary," +
                "departments.title,positions.position \n"+
                "FROM application.employes \n"+
                "JOIN application.departments ON employes.departments=departments.id \n"+
                "JOIN application.positions ON employes.position=positions.id \n" +
                "WHERE positions.id =" + parametr;
        return DBEmployeeService.getInstance().getEmployeeList(sqlPositionsSample);
    }

    public static SampleService getInstance() {
        return instance;
    }
}
