package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class UserAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public UserAuditService(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.UserService.addUser(..))")
    public void addUser(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            User user = (User) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(user.getUpdateDate());
            audit.setActionInformation("Пользователь "+user.getName()+" зарегистрировался в приложении!");
            audit.setEntityType(EntityType.USER);
            audit.setIdEntityOnWithTheActionIsPerformed(user.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении пользователя");
        }
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.UserService.updateUser(..))")
    public void updateUser(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            User user = (User) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(user.getUpdateDate());
            audit.setActionInformation("Пользователь "+user.getName()+" обновил данные");
            String userLogin = userHolder.getAuthentication().getName();
            User userByLogin = userService.getByLogin(userLogin);
            audit.setUser(userByLogin);
            audit.setEntityType(EntityType.USER);
            audit.setIdEntityOnWithTheActionIsPerformed(user.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении пользователя");
        }
    }
}
