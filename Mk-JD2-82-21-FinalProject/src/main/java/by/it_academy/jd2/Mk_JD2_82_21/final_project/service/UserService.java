package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IUserDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EStatus;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {
    private static IUserDAO userDAO;
    private static PasswordEncoder passwordEncoder;

    public UserService (IUserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void addUser(User user) {
        LocalDateTime createTime = LocalDateTime.now();
        user.setCreateDate(createTime);
        user.setUpdateDate(createTime);
        user.setRole(ERole.ROLE_USER);
        user.setStatus(EStatus.NO_ACTIVE);
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        userDAO.save(user);
    }

    @Override
    public User getUser(long userId) {
        return userDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException ("По данному ID пользователь не найден"));
    }

    @Override
    public Page<User> getUserList(Pageable pageable) {
        return userDAO.findAll(pageable);
    }

    @Override
    public void updateUser(User user, long id) {
        User updateUser = getUser(id);
        if (updateUser == null) {
            throw new IllegalArgumentException("Пользователь с таким ID не найден");
        } else {
            updateUser.setName(user.getName());
            updateUser.setLogin(user.getLogin());
            updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
            updateUser.setRole(user.getRole());
            updateUser.setStatus(user.getStatus());
            userDAO.save(updateUser);
        }
    }

    @Override
    public User getByLoginAndPassword(LoginDTO loginDto) {
        User byLoginAndPassword = userDAO.findByLoginAndPassword(loginDto.getLogin(), loginDto.getPassword());
        return byLoginAndPassword;
    }

    @Override
    public User getByLogin(String login) {
        User byLogin = userDAO.findByLogin(login);
        return byLogin;
    }

}
