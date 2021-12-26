package by.it_academy.jd2.Mk_JD2_82_21.final_project.utils;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class CheckUtil {
    private final UserHolder userHolder;

    public CheckUtil(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    public boolean isAdminRoleCheck () {
        User user = userHolder.getUser();
        if(user.getRole().equals(ERole.ROLE_ADMIN)) {
            return true;
        }else {
            return false;
        }
    }


}
