package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.util.Map;

public interface IUserService {
    public Users getUser(String login);
    public void setUser(String login, Users user);
    public Map<String,Users> getUserMap();
}
