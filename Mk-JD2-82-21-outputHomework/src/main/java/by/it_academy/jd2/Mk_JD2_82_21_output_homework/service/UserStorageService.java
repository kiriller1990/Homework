package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.util.Map;

public class UserStorageService implements IUserService {
    private static final UserStorageService instance = new UserStorageService();

    public UserStorageService() {
    }
    @Override
    public Users getUser(String login) {
        return UserStorage.getInstance().getUserList().get(login);
    }

    @Override
    public void setUser(String login, Users user) {
        UserStorage.getInstance().getUserList().put(login,user);
    }

    @Override
    public Map<String,Users> getUserMap() {
        return UserStorage.getInstance().getUserList();
    }

    public static UserStorageService getInstance() {
        return instance;
    }


}
