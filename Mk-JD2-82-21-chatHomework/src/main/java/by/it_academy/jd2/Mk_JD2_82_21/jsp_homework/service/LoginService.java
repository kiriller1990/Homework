package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

public class LoginService {
    private static final LoginService instance = new LoginService();

    public LoginService() {

    }

    public static LoginService getInstance() {
        return instance;
    }

    public boolean isExist(String login, String password) {
       Users user = UserStorageService.getInstance().getUser(login);
       if(user != null && user.getPassword().equals(password)){
           return true;
       } else {
           return false;
       }
    }

    public boolean isLoginCorrect(String login, String password) {
        Users user = UserStorageService.getInstance().getUser(login);
        if(user!=null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
