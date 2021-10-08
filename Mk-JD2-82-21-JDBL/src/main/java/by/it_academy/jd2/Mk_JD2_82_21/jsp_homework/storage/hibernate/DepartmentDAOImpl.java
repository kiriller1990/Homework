package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IDepartmentDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;

import java.util.List;

public class DepartmentDAOImpl implements IDepartmentDAO {
    public static final DepartmentDAOImpl instance = new DepartmentDAOImpl();

    @Override
    public List<Department> getDepartmentList() {
        return null;
    }

    public static DepartmentDAOImpl getInstance() {
        return instance;
    }
}
