package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class DishAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;

    public DishAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.addDish(..))")
    public void addDish(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            Dish dish = (Dish) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(dish.getCreateDate());
            audit.setUser(dish.getUserWhoMadeTheEntry());
            audit.setEntityType(EntityType.DISH);
            audit.setIdEntityOnWithTheActionIsPerformed(dish.getId());
            audit.setActionInformation("Пользователь "+dish.getUserWhoMadeTheEntry().getName()+
                    " добавил блюдо "+dish.getTitle());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в аудите при добавлении блюда");
        }
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.updateDish(..))")
    public void updateDish(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            Dish dish = (Dish) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(dish.getUpdateDate());
            String userLogin = this.userHolder.getAuthentication().getName();
            User user = authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.DISH);
            audit.setIdEntityOnWithTheActionIsPerformed(dish.getId());
            audit.setActionInformation("Пользователь "+user.getName()+
                    " внес измнения в блюдо "+ dish.getTitle());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении блюда");
        }
    }

    @After("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.deleteDish(..))")
    public void deleteDish(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Long dishId = (Long) args[0];
            Audit audit = new Audit();
            audit.setDateOfCreate(LocalDateTime.now());
            String userLogin = userHolder.getAuthentication().getName();
            User user = authService.getByLogin(userLogin);
            audit.setUser(user);
            audit.setEntityType(EntityType.DISH);
            audit.setIdEntityOnWithTheActionIsPerformed(dishId);
            audit.setActionInformation("Пользователь " + user.getName() +
                    " удалил запись о блюде "+dishId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в аудите при удалении блюда");
        }
    }
}
