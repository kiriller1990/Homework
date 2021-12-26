package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProfileService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Profile;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ProfileAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IProfileService profileService;

    public ProfileAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService, IProfileService profileService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.profileService = profileService;
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProfileService.addProfile(..))")
    public void addProfile(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            Profile profile = (Profile) args[0];
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+ " создал профиль");
            audit.setEntityType("Profile");
            audit.setEntityId(profile.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении профиля");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProfileService.updateProfile(..))")
    public void updateProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Profile profile = (Profile) args[0];
            long profileId = (Long)  args[1];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил данные профиля");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("Profile");
            audit.setEntityId(profile.getId());
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении профиля");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProfileService.deleteProfile(..))")
    public void deleteProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long profileId = (Long) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил профиль");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("Profile");
            audit.setEntityId(profileId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении продукта");
        }
    }
}
