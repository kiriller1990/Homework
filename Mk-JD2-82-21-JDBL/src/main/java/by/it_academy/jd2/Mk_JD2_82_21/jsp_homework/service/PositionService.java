package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.DBPositionStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.util.List;

public class PositionService {
    public static PositionService getInstance() {
        return instance;
    }

    public List<Position> getPositionList(){
        return DBPositionStorage.getInstance().getPositionList();
    }

    private static final PositionService instance = new PositionService();
}
