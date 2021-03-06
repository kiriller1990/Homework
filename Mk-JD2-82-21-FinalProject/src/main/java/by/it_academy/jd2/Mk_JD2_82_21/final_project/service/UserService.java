package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IUserDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EStatus;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.CheckUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {
    private final IUserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final UserHolder userHolder;
    private final CheckUtil checkUtil;

    public UserService(IUserDAO userDAO, PasswordEncoder passwordEncoder, UserHolder userHolder, CheckUtil checkUtil) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.userHolder = userHolder;
        this.checkUtil = checkUtil;
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
        User user = userDAO.findById(userId).orElseThrow(()->
                new IllegalArgumentException ("По данному ID пользователь не найден"));
        User loginUser = userHolder.getUser();
        if(user == loginUser || checkUtil.isAdminRoleCheck()) {
            return user;
        } else {
            throw new IllegalArgumentException ("Вы можете просматривать только свои данные");
        }
    }

    @Override
    public Page<User> getUserList(Pageable pageable) {
        if (checkUtil.isAdminRoleCheck()) {
            return userDAO.findAll(pageable);
        } else {
            throw new IllegalArgumentException("Только администратор может просматривать список пользователей");
        }
    }

    @Override
    public void updateUser(User user, long id) {
        User updateUser = getUser(id);
        if (updateUser==userHolder.getUser() || checkUtil.isAdminRoleCheck()) {
            updateUser.setName(user.getName());
            updateUser.setLogin(user.getLogin());
            updateUser.setPassword(passwordEncoder.encode(user.getPassword()));
            if(checkUtil.isAdminRoleCheck()) {
                updateUser.setRole(user.getRole());
            } else {
                updateUser.setRole(userHolder.getUser().getRole());
            }
            updateUser.setStatus(userHolder.getUser().getStatus());
            updateUser.setCreateDate(userHolder.getUser().getCreateDate());
            updateUser.setUpdateDate(LocalDateTime.now());
            userDAO.save(updateUser);
        } else {
            throw new IllegalArgumentException("Только администратор может обновлять чужого пользователя");
        }
    }
}
