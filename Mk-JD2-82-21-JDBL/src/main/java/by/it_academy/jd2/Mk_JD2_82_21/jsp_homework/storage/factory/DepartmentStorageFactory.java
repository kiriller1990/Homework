package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IDepartmentDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate.DepartmentDAOImpl;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBDepartmentStorage;

public class DepartmentStorageFactory {
    private static EStorageType type = null;

    private DepartmentStorageFactory() {
    }

    public static synchronized void setType(EStorageType type) {
        if(type != null){
            DepartmentStorageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static IDepartmentDAO getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type){
            case SQL:
                return DBDepartmentStorage.getInstance();
            case HIBERNATE:
                return DepartmentDAOImpl.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
