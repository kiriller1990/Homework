package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class UserAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;

    public UserAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.UserService.addUser(..))")
    public void addUser(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            User user = (User) args[0];
            Audit audit = new Audit();
            User addUser = authService.getByLogin(user.getLogin());
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+user.getName()+" зарегистрировался в приложении!");
            audit.setEntityType("User");
            audit.setEntityId(addUser.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении пользователя");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.UserService.updateUser(..))")
    public void updateUser(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            User user = (User) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+userWhoMadeTheChange.getName()+" обновил данные");
            long userId =userHolder.getUser().getId();
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("User");
            audit.setEntityId(userId);
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении пользователя");
        }
    }
}
