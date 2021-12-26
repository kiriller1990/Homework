package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.*;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IFoodDiaryDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class FoodDiaryAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IFoodDiaryService foodDiaryService;
    private final IFoodDiaryDAO foodDiaryDAO;

    public FoodDiaryAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService,
                                 IFoodDiaryService foodDiaryService, IFoodDiaryDAO foodDiaryDAO) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.foodDiaryService = foodDiaryService;
        this.foodDiaryDAO = foodDiaryDAO;
    }

    @AfterReturning(value ="execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.addFoodDiary(..))"
                    ,returning = "result")
    public void addFoodDiary(JoinPoint joinPoint , Object result){
        try {
            FoodDiary foodDiary = (FoodDiary) result;
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+ " добавил дневник питания");
            audit.setEntityType("FoodDiary");
            audit.setEntityId(foodDiary.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении дневника питания");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.updateFoodDiary(..))")
    public void updateDish(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long foodDiaryId = (long) args[2];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил дневник питания");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("FoodDiary");
            audit.setEntityId(foodDiaryId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении дневника питания");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.FoodDiaryService.deleteFoodDiary(..))")
    public void deleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long foodDiaryId = (Long) args[1];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил дневник питания");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("FoodDiary");
            audit.setEntityId(foodDiaryId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении дневника питания");
        }
    }
}
