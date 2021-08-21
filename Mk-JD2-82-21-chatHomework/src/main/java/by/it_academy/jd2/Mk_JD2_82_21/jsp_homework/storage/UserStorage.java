package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, Users> userList;
    private static UserStorage instance = new UserStorage();

    {
        this.userList = new HashMap<>();
    }

    public UserStorage() {
    }

    public Map<String, Users> getUserList() {
        return userList;
    }

    public static UserStorage getInstance() {
        return instance;
    }
}
