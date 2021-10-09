package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IEmployeeDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeDAOImpl implements IEmployeeDAO {
    private static final EmployeeDAOImpl instance = new EmployeeDAOImpl();

     public EmployeeDAOImpl (){
     }

    @Override
    public long addEmployee(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long employeId = (Long) session.save(employee);
        transaction.commit();
        session.close();
        return employeId;
    }

    @Override
    public Employee getEmployeeCard(String idEmployee) {
         long id = Long.parseLong(idEmployee);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Employee getEmployee = session.get(Employee.class,id);
        transaction.commit();
        session.close();
        return getEmployee;
    }

    @Override
    public long getEmployeeCount() {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.select(criteriaBuilder.count(itemRoot));
        Query<Long> query = sessionOne.createQuery(criteriaQuery);
        Long count = query.getSingleResult();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return count;

    }

    @Override
    public List<Employee> getEmployeeList(String offset, String employeeInOnePage) {
        int limit = Integer.parseInt(employeeInOnePage);
        int offsetInt = Integer.parseInt(offset);
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> itemRoot = criteriaQuery.from(Employee.class);
        CriteriaQuery<Employee>select = criteriaQuery.select(itemRoot);
        Query<Employee>query = session.createQuery(select);
        query.setFirstResult(offsetInt);
        query.setMaxResults(limit);
        List<Employee>employeeList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        return employeeList;
    }

    @Override
    public List<Employee> getEmployeeListSortedByDepartment(String parameter) {
         Long id = Long.parseLong(parameter);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootAuto = criteriaQuery.from(Employee.class);
        criteriaBuilder.equal(rootAuto.get("departments"), id);
        List<Employee> list = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return list;
    }

    @Override
    public List<Employee> getEmployeeListSortedByPosition(String parameter) {
        return null;
    }

    public static EmployeeDAOImpl getInstance() {
        return instance;
    }
}
