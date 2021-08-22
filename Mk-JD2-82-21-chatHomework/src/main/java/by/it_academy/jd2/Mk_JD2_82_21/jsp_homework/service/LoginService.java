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

    public void registerCheck (String login,String password, String name, String date) {
        String exMessage = "";

        if(isExist(login, password)) {
            exMessage += "Пользователь с таким именем уже существует. \n";
        }
        if(login.isEmpty()) {
            exMessage += "Введите логин. \n";
        }
        if(password.isEmpty()) {
            exMessage += "Введите пароль. \n";
        }
        if(name.isEmpty()) {
            exMessage += "Введите Фамилию Имя и Отчество. \n";
        }
        if(date.isEmpty()) {
            exMessage += "Введите дату рождения. \n";
        }
        if(!exMessage.isEmpty()) {
            throw new IllegalArgumentException(exMessage);
        }
    }

    public void loginCheck (String login,String password) {
        String exMessage = "";

        if(!isLoginCorrect(login, password)) {
            exMessage += "Неправильно введен логин или пароль! \n";
        }
        if(!exMessage.isEmpty()){
            throw new IllegalArgumentException(exMessage);
        }
    }

    public void messageParameterChek (Users recipient) {
        String exMessage = "";

        if(recipient == null) {
            exMessage += "Получатель не найден, проверьте правильность ввода получателя \n";
        }
        if(!exMessage.isEmpty()){
            throw new IllegalArgumentException(exMessage);
        }
    }

}
