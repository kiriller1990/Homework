package by.it_academy.jd2.Mk_JD2_82_21.final_project.security;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {
    private final IAuthService authService;

    public UserHolder(IAuthService authService) {
        this.authService = authService;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public User getUser() {
        return authService.getByLogin(getAuthentication().getName());
    }
}
