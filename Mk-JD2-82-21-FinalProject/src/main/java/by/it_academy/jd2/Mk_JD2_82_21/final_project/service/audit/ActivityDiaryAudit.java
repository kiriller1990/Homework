package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IActivityDiaryService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.ActivityDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;

import java.time.LocalDateTime;

public class ActivityDiaryAudit {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IActivityDiaryService activityDiaryService;

    public ActivityDiaryAudit(IAuditService auditService, UserHolder userHolder, IAuthService authService,
                                IActivityDiaryService activityDiaryService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.activityDiaryService = activityDiaryService;
    }

    @AfterReturning(value = "execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.addActivityDiary(..))",returning = "result")
    public void addActivity(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            ActivityDiary activityDiary = (ActivityDiary) args[0];
            Long idActivityDiary = (Long) result;
            Audit audit = new Audit();
            audit.setDateOfCreate(activityDiary.getCreateDate());
            String userLogin = this.userHolder.getAuthentication().getName();
            User user = this.authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.EXERCISES);
            audit.setIdEntityOnWithTheActionIsPerformed(idActivityDiary);
            audit.setActionInformation("Пользователь "+user.getName()+
                    " внес запись "+activityDiary.getActivityType()+" в дневник активности");
            this.auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении записи в дневник активности");
        }
    }

 /*   @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.updateActivityDiary(..))")
    public void updateExercise(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            ActivityDiary activityDiary = (ActivityDiary) args[0];
            Long idProfile = (Long) args[1];
            Long idActive = (Long) args[2];
            Audit audit = new Audit();
            LocalDateTime updateTime = this.activityDiaryService.getActivityDiary(idProfile).getUpdateDate();
            audit.setDateOfCreate(updateTime);
            String userLogin = this.userHolder.getAuthentication().getName();
            User user = this.authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.EXERCISES);
            audit.setEntityId(idActive);
            audit.setText("Пользователь "+user.getName()+
                    " изменил запись "+exercise.getName()+" в дневник активности ");
            this.iAuditService.save(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка работы аудита");
        }
    }

    @AfterReturning("execution(* it.academy.by.befitapp.service.ExerciseService.delete(..))")
    public void deleteExercise(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long exerciseId = (Long) args[0];
            Audit audit = new Audit();
            audit.setCreateTime(LocalDateTime.now());
            String userLogin = this.userHolder.getAuthentication().getName();
            User user = this.iAuthService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.EXERCISES);
            audit.setEntityId(exerciseId);
            audit.setText("Пользователь " + user.getName() + " удалил запись "+exerciseId+" из дневника активности");
            this.iAuditService.save(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка работы аудита");
        }
    }*/
}
