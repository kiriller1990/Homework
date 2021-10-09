package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IDepartmentDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DepartmentDAOImpl implements IDepartmentDAO {
    public static final DepartmentDAOImpl instance = new DepartmentDAOImpl();

    @Override
    public List<Department> getDepartmentList() {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> itemRoot = criteriaQuery.from(Department.class);
        criteriaQuery.select(itemRoot);
        List<Department> departmentList = sessionOne.createQuery(criteriaQuery).getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return departmentList;
    }

    public static DepartmentDAOImpl getInstance() {
        return instance;
    }
}
