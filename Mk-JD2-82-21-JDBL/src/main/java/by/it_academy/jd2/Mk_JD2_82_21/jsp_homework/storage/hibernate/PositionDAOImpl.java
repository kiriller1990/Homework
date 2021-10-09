package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IPositionDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Department;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.util.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class PositionDAOImpl implements IPositionDAO {
    private static final PositionDAOImpl instance = new PositionDAOImpl();

    @Override
    public List<Position> getPositionList() {
        Session sessionOne = HibernateUtil.getSessionFactory().openSession();
        sessionOne.beginTransaction();
        CriteriaBuilder criteriaBuilder = HibernateUtil.getSessionFactory().createEntityManager().getCriteriaBuilder();
        CriteriaQuery<Position> criteriaQuery = criteriaBuilder.createQuery(Position.class);
        Root<Position> itemRoot = criteriaQuery.from(Position.class);
        criteriaQuery.select(itemRoot);
        List<Position> positionList = sessionOne.createQuery(criteriaQuery).getResultList();
        sessionOne.getTransaction().commit();
        sessionOne.close();
        return positionList;

    }

    public static PositionDAOImpl getInstance() {
        return instance;
    }
}
