package by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model;

import java.io.Serializable;

public class Users implements Serializable {

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

    public String getPassword() {
        return password;
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

}
