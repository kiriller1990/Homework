package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.dto.DishDTO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IDishService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.EntityType;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Dish;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class DishAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IDishService dishService;

    public DishAuditService(IAuditService auditService,
                            UserHolder userHolder, IAuthService authService, IDishService dishService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.dishService = dishService;
    }

    @AfterReturning(value ="execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.addDish(..))"
            ,returning = "result")
    public void addDish(JoinPoint joinPoint, Object result){
        try {
            Dish dish = (Dish) result;
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+" Добавил блюдо: " + dish.getTitle());
            audit.setEntityType("Dish");
            audit.setEntityId(dish.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении блюда");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.updateDish(..))")
    public void updateDish(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long dishId = (long) args[1];
            Dish dish = dishService.getDish(dishId);
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил данные блюда:"
                    + dish.getTitle());
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("Dish");
            audit.setEntityId(dish.getId());
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении блюда");
        }
    }

        @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.DishService.deleteDish(..))")
        public void deleteProduct(JoinPoint joinPoint) {
            try {
                Object[] args = joinPoint.getArgs();
                long dishId = (Long) args[0];
                Audit audit = new Audit();
                User userWhoMadeTheChange = userHolder.getUser();
                audit.setDateOfCreate(LocalDateTime.now());
                audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил блюдо");
                audit.setUser(userWhoMadeTheChange);
                audit.setEntityType("Dish");
                audit.setEntityId(dishId);
                auditService.addAudit(audit);
            } catch (Throwable e) {
                throw new IllegalArgumentException("Ошибка в работе аудита при удалении блюда");
            }
        }
}