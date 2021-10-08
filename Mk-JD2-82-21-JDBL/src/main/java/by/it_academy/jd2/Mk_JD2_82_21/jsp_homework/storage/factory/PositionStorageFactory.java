package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IPositionDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate.PositionDAOImpl;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBPositionStorage;

public class PositionStorageFactory {
    private static EStorageType type = null;

    private PositionStorageFactory() {
    }

    public static synchronized void setType(EStorageType type) {
        if(type != null){
            PositionStorageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static IPositionDAO getInstance() {
        if (type == null) {
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type) {
            case SQL:
                return DBPositionStorage.getInstance();
            case HIBERNATE:
                return PositionDAOImpl.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
