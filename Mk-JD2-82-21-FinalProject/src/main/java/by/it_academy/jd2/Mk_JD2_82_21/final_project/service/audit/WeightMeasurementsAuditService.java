package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.WeightMeasurementsService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class WeightMeasurementsAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final WeightMeasurementsService weightMeasurementsService;

    public WeightMeasurementsAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService,
                                          WeightMeasurementsService weightMeasurementsService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.weightMeasurementsService = weightMeasurementsService;
    }

    @AfterReturning(value ="execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
            "WeightMeasurementsService.addWeightMeasurements(..))",returning = "result")
    public void addWeightMeasurements(JoinPoint joinPoint, Object result){
        try {
            Object[] args = joinPoint.getArgs();
            WeightMeasurements weightMeasurements = (WeightMeasurements) result;
            long weightMeasurementsId = weightMeasurements.getId();
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+" Добавил взвешивание: ");
            audit.setEntityType("WeightMeasurements");
            audit.setEntityId(weightMeasurementsId);
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении взвешивания");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
                       "WeightMeasurementsService.updateWeightMeasurements(..))")
    public void updateProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long weightMeasurementsId = (Long)  args[1];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил данные взвешивания");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("WeightMeasurements");
            audit.setEntityId(weightMeasurementsId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении взвешивания");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service." +
                    "WeightMeasurementsService.deleteWeightMeasurements(..))")
    public void deleteProfile(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long weightMeasurementsId = (Long) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил взвешивание");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("WeightMeasurements");
            audit.setEntityId(weightMeasurementsId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении взвешивания");
        }
    }
}
