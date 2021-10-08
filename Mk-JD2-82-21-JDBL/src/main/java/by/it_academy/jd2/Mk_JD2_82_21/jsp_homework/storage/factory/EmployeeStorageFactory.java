package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.factory;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.api.IEmployeeDAO;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.hibernate.EmployeeDAOImpl;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.sql.DBEmployeeStorage;

public class EmployeeStorageFactory {
    private static EStorageType type = null;

    private EmployeeStorageFactory() {
    }

    public static synchronized void setType(EStorageType type) {
        if(type != null){
            EmployeeStorageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static IEmployeeDAO getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type){
            case SQL:
                return DBEmployeeStorage.getInstance();
            case HIBERNATE:
                return EmployeeDAOImpl.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
