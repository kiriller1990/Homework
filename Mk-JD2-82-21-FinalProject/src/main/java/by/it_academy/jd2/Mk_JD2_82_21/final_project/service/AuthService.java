package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IUserDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final IUserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public AuthService(IUserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByLoginAndPassword(LoginDTO loginDTO) {
        User user = userDAO.findByLogin(loginDTO.getLogin());
        if(user!=null){
            if (this.passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        User byLogin = this.userDAO.findByLogin(login);
        return byLogin;
    }
}
