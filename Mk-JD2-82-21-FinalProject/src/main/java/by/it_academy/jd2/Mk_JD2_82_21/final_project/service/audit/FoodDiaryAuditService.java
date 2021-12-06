package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.FoodDiary;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class FoodDiaryAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;

    public FoodDiaryAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.addFoodDiary(..))")
    public void addJournal(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            FoodDiary foodDiary =(FoodDiary) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(foodDiary.getUpdateDate());
            String userLogin = userHolder.getAuthentication().getName();
            User user = authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.DAIRY);
            audit.setIdEntityOnWithTheActionIsPerformed(foodDiary.getId());
            audit.setActionInformation("Пользователь "+user.getName()+
                    " добавил запись ");
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении дневника питания");
        }
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.updateFoodDiary(..))")
    public void updateJournal(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            FoodDiary foodDiary =(FoodDiary) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(foodDiary.getUpdateDate());
            String userLogin = userHolder.getAuthentication().getName();
            User user = authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.DAIRY);
            audit.setIdEntityOnWithTheActionIsPerformed(foodDiary.getId());
            audit.setActionInformation("Пользователь "+user.getName()+
                    " обновил запись ");
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении дневника питания");
        }
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.deleteFoodDiary(..))")
    public void deleteJournal(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long foodDiarylId = (Long) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(LocalDateTime.now());
            String userLogin = userHolder.getAuthentication().getName();
            User user = authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.DAIRY);
            audit.setIdEntityOnWithTheActionIsPerformed(foodDiarylId);
            audit.setActionInformation("Пользователь " + user.getName() + " удалил запись "+ foodDiarylId + " из дневника питания");
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении дневника питания");
        }
    }
    
}
