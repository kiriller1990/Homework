package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ActivityDiaryAudit {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IUserService userService;

    public ActivityDiaryAudit(IAuditService auditService, UserHolder userHolder, IUserService userService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.userService = userService;
    }

    @AfterReturning(pointcut = "execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.save(..))", returning = "result")
    public void methodSaveActive(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            ActivityDiary active = (ActivityDiary) result;

            Audit audit = new Audit();
            audit.setDateOfCreate(active.getCreateDate());
            audit.setActionInformation("Создан Active " + active.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.getByLogin(login);
            audit.setUser(user);
            audit.setEntityType(EntityType.EXERCISES);
            audit.setIdEntityOnWithTheActionIsPerformed(active.getId());
            auditService.addAudit(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ActiveService.update(..))")
    public void methodUpdateActive(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Active arg = (Active) args[0];

            Audit audit = new Audit();
            audit.setCreationDate(arg.getCreationDate());
            audit.setText("Изменен Active " + arg.getId());
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.ACTIVE);
            audit.setEntityId(arg.getId());
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.my_application.services.dataBaseService.ProductService.delete(..))")
    public void methodDeleteActive(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long id = (Long) args[0];
            Audit audit = new Audit();
            audit.setCreationDate(LocalDateTime.now().withNano(0));
            audit.setText("Удален Active " + id);
            String login = userHolder.getAuthentication().getName();
            User user = userService.findByLogin(login);
            audit.setUser(user);
            audit.setTypeOfEntity(ETypeOfEntity.ACTIVE);
            audit.setEntityId(id);
            auditGeneralService.save(audit);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
