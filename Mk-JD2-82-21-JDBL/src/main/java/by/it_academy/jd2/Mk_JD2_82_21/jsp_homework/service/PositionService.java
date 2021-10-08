package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.IPositionService;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IEmployeeDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IPositionDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.EmployeeStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory.PositionStorageFactory;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBPositionStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Position;

import java.util.List;

public class PositionService implements IPositionService {
    private static final PositionService instance = new PositionService();
    private IPositionDAO iPositionDAO;

    public PositionService() {
        this.iPositionDAO = PositionStorageFactory.getInstance();
    }


    @Override
    public List<Position> getPositionList(){
        return iPositionDAO.getPositionList();
    }

    public static PositionService getInstance() {
        return instance;
    }
}
