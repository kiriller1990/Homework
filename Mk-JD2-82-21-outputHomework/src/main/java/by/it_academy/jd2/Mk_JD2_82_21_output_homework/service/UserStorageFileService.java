package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.UserStorage;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class UserStorageFileService implements IUserService {
    private static UserStorageFileService instance = new UserStorageFileService();
    private static final String USERS_FILE_PASS = "users.ser";

    @Override
    public Users getUser(String login) {
        return getUserMap().get(login);
    }

    @Override
    public Map<String,Users> getUserMap() {
        Map<String,Users> usersMap = new HashMap<>() ;
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE_PASS))) {
             usersMap = (Map<String,Users>) ois.readObject();
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
            }
        return usersMap;
    }

    @Override
    public void setUser(String login, Users user) {
        if(UserStorage.getInstance().getUserList().isEmpty()) {
            UserStorage.getInstance().getUserList().putAll(getUserMap());
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE_PASS))) {
            UserStorageService.getInstance().setUser(login, user);
            oos.writeObject(UserStorageService.getInstance().getUserMap());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static UserStorageFileService getInstance() {
        return instance;
    }
}
