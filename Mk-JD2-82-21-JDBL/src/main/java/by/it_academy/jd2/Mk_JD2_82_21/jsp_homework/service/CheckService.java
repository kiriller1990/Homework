package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Employee;

public class CheckService {
    private static final CheckService instance = new CheckService();

    private CheckService() {
    }

    public static CheckService getInstance(){
        return instance;
    }

    public void registerCheck (String name,String salary) {
        String exMessage = "";

        if(name.isEmpty()) {
            exMessage += "Введите имя \n";
        }
        if(salary.isEmpty()) {
            exMessage += "Введите зарплату \n";

        }
        if(!exMessage.isEmpty()) {
            throw new IllegalArgumentException(exMessage);
        }
    }

    public void idCheck (String idParam) {
        String exMessage = "";

        if (idParam.isEmpty()) {
            exMessage += "Введите ID \n";
        } else if(!isExist(idParam)) {
            exMessage += "Пользователь c таким ID не найден \n";
        }
        if (!exMessage.isEmpty()) {
            throw new IllegalArgumentException(exMessage);
        }
    }

    public boolean isExist (String idParam) {
           long id = Long.parseLong(idParam);
           Employee employee = DBInitializer.getInstance().getEmployee(id);
           if(employee == null) {
               return false;
           }else {
               return true;
           }
        }
}
