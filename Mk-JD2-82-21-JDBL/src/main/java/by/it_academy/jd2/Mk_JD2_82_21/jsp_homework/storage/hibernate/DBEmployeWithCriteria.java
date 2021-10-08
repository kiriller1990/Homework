package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Search;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DBEmployeWithCriteria {
    private static final DBEmployeWithCriteria instance = new DBEmployeWithCriteria();
    public List<Employee> getSortedListOfEmployees(Search search){
        String name = search.getNameSearch();
        String salaryFilterType = search.getTypeOfSalaryFilter();
        double salaryFilteredValue = search.getSalary();

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = sessionFactory.createEntityManager().getCriteriaBuilder();

        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> rootAuto = criteriaQuery.from(Employee.class);

        if (name.equals("")){
            if(salaryFilterType.equals("больше")){
                criteriaQuery.where(criteriaBuilder.gt(rootAuto.get("salary"),salaryFilteredValue));
            } else {
                criteriaQuery.where(criteriaBuilder.lt(rootAuto.get("salary"),salaryFilteredValue));
            }
        } else {
            criteriaQuery.where(criteriaBuilder.and(
                    criteriaBuilder.equal(rootAuto.get("name"), name)
            ));
        }
        List<Employee> list = session.createQuery(criteriaQuery).getResultList();
        session.close();
        return list;
    }

    public static DBEmployeWithCriteria getInstance() {
        return instance;
    }
}
