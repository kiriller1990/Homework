package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model;

import java.util.Map;

public class Users {

    private String login;
    private String password;
    private String name;
    private String date;

    public Users(String login, String password, String name, String date) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Integer age) {
        this.date = date;
    }


}
