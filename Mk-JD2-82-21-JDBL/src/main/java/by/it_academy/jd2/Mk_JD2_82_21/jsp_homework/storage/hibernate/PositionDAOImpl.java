package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IPositionDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.util.List;

public class PositionDAOImpl implements IPositionDAO {
    private static final PositionDAOImpl instance = new PositionDAOImpl();

    @Override
    public List<Position> getPositionList() {
        return null;
    }

    public static PositionDAOImpl getInstance() {
        return instance;
    }
}
