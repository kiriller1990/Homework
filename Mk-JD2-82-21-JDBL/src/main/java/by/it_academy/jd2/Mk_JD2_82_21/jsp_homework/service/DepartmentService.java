package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.IDepartmentService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IDepartmentDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.DepartmentStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBDepartmentStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;

import java.util.List;

public class DepartmentService implements IDepartmentService {
    private static final DepartmentService instance = new DepartmentService();
    private IDepartmentDAO iDepartmentDAO;

    private DepartmentService() {
        this.iDepartmentDAO= DepartmentStorageFactory.getInstance();
    }

    @Override
    public List<Department> getDepartmentList(){
        return iDepartmentDAO.getDepartmentList();
    }

    public static DepartmentService getInstance() {
        return instance;
    }
}
