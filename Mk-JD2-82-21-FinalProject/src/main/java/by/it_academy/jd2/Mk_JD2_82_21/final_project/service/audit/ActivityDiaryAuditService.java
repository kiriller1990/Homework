package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.WeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.WeightMeasurements;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ActivityDiaryAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IActivityDiaryService activityDiaryService;

    public ActivityDiaryAuditService(IAuditService auditService, UserHolder userHolder,
                                     IAuthService authService, IActivityDiaryService activityDiaryService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.activityDiaryService = activityDiaryService;
    }

    @AfterReturning(value ="execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
            "ActivityDiaryService.addActivityDiary(..))",returning = "result")
    public void addActivityDiary(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            ActivityDiary activityDiary = (ActivityDiary) result;
            long activityDiaryId = activityDiary.getId();
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+" Добавил активность: ");
            audit.setEntityType("ActivityDiary");
            audit.setEntityId(activityDiaryId);
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении активности");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
            "ActivityDiaryService.updateActivityDiary(..))")
    public void updateProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long weightMeasurementsId = (Long)  args[2];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил данные активности");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("ActivityDiary");
            audit.setEntityId(weightMeasurementsId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении активности");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
            "ActivityDiaryService.deleteActivityDiary(..))")
    public void deleteProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long weightMeasurementsId = (Long) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил активность");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("ActivityDiary");
            audit.setEntityId(weightMeasurementsId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении активности");
        }
    }
}
