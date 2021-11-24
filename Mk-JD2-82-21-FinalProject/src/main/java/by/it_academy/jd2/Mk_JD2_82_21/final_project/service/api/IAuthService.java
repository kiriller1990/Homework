package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.LoginDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;

public interface IAuthService {
    public User getByLoginAndPassword(LoginDTO loginDTO);
    public User getByLogin(String login);
}
