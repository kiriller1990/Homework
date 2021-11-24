package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    public void addUser(User user);
    public User getUser(long userId);
    public Page<User> getUserList(Pageable pageable);
    public void updateUser(User user, long id);
    public User getByLogin(String login);
    public User getByLoginAndPassword(LoginDTO loginDto);
}
