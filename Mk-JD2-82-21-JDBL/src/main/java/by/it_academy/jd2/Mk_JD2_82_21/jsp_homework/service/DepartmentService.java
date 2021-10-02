package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;

import java.util.List;

public class DepartmentService {
    private static final DepartmentService instance = new DepartmentService();

    public List<Department> getDepartmentList(){
        return DBDepartmentStorage.getInstance().getDepartmentList();
    }

    public static DepartmentService getInstance() {
        return instance;
    }
}
