package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBPositionStorage;

import java.util.List;

public interface IPositionDAO {
    public List<Position> getPositionList();
}
