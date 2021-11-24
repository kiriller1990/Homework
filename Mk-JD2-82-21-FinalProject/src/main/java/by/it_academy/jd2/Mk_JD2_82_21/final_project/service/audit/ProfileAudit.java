package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ProfileAudit {

    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ProfileAudit(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.addProfile(..))", returning = "result")
    public void methodSaveProfile(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            Profile profile = (Profile) result;

            Audit audit = new Audit();
            audit.setDateOfCreate(profile.getCreateDate());
            audit.setActionInformation("Создан Profile " + profile.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.getByLogin(login);
            audit.setUser(user);
            audit.setEntityType(EntityType.PROFILE);
            audit.setIdEntityOnWithTheActionIsPerformed(profile.getId());
            auditService.addAudit(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.updateProfile(..))")
    public void methodUpdateProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Profile arg = (Profile) args[0];

            Audit audit = new Audit();
            audit.setDateOfCreate(arg.getCreateDate());
            audit.setActionInformation("Изменен Profile " + arg.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.getByLogin(login);
            audit.setUser(user);
            audit.setEntityType(EntityType.PROFILE);
            audit.setIdEntityOnWithTheActionIsPerformed(arg.getId());
            auditService.addAudit(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.deleteProfile(..))")
    public void methodDeleteProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Удален Profile " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.getByLogin(login);
            audit.setUser(user);
            audit.setEntityType(EntityType.PROFILE);
            audit.setIdEntityOnWithTheActionIsPerformed(id);
            auditService.addAudit(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
