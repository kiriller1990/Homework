package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.audit;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuditService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IAuthService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Audit;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Aspect
@Service
public class ProductAuditService {
    private final IAuditService auditService;
    private final UserHolder userHolder;
    private final IAuthService authService;
    private final IProductService productService;

    public ProductAuditService(IAuditService auditService, UserHolder userHolder, IAuthService authService,
                               IProductService productService) {
        this.auditService = auditService;
        this.userHolder = userHolder;
        this.authService = authService;
        this.productService = productService;
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProductService.addProduct(..))")
    public void addProduct(JoinPoint joinPoint){
        try {
            Object[] args = joinPoint.getArgs();
            Product product = (Product) args[0];
            Audit audit = new Audit();
            User addUser = userHolder.getUser();
            audit.setUser(addUser);
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь "+addUser.getName()+" Добавил продукт: " + product.getProductName());
            audit.setEntityType("Product");
            audit.setEntityId(addUser.getId());
            auditService.addAudit(audit);
        }catch (Throwable e){
            throw new IllegalArgumentException("Ошибка в работе аудита при добавлении продукта");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProductService.updateProduct(..))")
    public void updateProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            Product product = (Product) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " обновил данные продукта:"
                    + product.getProductName());
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("Product");
            audit.setEntityId(product.getId());
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при обновлении продукта");
        }
    }

    @AfterReturning("execution(* by.it_academy.jd2.Mk_JD2_82_21.final_project.service.ProductService.deleteProduct(..))")
    public void deleteProduct(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            long productId = (Long) args[0];
            Audit audit = new Audit();
            User userWhoMadeTheChange = userHolder.getUser();
            audit.setDateOfCreate(LocalDateTime.now());
            audit.setActionInformation("Пользователь " + userWhoMadeTheChange.getName() + " удалил продукт");
            audit.setUser(userWhoMadeTheChange);
            audit.setEntityType("Product");
            audit.setEntityId(productId);
            auditService.addAudit(audit);
        } catch (Throwable e) {
            throw new IllegalArgumentException("Ошибка в работе аудита при удалении продукта");
        }
    }
}
