package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

import java.util.Map;

public class UserStorageService {
    private static final UserStorageService instance = new UserStorageService();

    public UserStorageService() {
    }

    public Users getUser(String login) {
        Users user = null;;
        for(Map.Entry<String, Users> item : getUsersMap().entrySet() ) {
            if(item.getKey().equals(login)) {
                user = item.getValue();
            }
        }
        return user;
    }

    public static UserStorageService getInstance() {
        return instance;
    }

    public Map<String,Users> getUsersMap() {
        return UserStorage.getInstance().getUserList();
    }


    public void setUser(String login, Users user) {
        getUsersMap().put(login,user);
    }

}
