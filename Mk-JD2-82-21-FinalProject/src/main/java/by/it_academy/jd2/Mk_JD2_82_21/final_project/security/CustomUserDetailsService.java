package by.it_academy.jd2.Mk_JD2_82_21.final_project.security;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final IAuthService authService;

    public CustomUserDetailsService(@Lazy IAuthService authService) {
        this.authService = authService;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.getByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }

}
