package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IProductDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductDAO productDAO;
    private final CheckUtil checkUtil;
    private final UserHolder userHolder;


    public ProductService(IProductDAO productDAO, CheckUtil checkUtil, UserHolder userHolder) {
        this.productDAO = productDAO;
        this.checkUtil = checkUtil;
        this.userHolder = userHolder;
    }

    @Override
    public void addProduct(Product product) {
        if(checkUtil.isAdminRoleCheck()) {
            LocalDateTime timeStamp = LocalDateTime.now();
            product.setCreateDate(timeStamp);
            product.setUpdateDate(timeStamp);
            product.setUserWhoMadeTheEntry(userHolder.getUser());
            productDAO.save(product);
        } else {
            throw new IllegalCallerException ("Добавлять продукты может только администратор");
        }
    }

    @Override
    public Product getProduct(long productId) {
        return productDAO.findById(productId).orElseThrow(()->
                new IllegalArgumentException ("По данному ID продукт не найден"));
    }

    @Override
    public Page<Product> getProductList(Pageable pageable, String name) {
        if(name != null) {
            return productDAO.findProductsByProductNameContains(name, pageable);
        } else {
            return productDAO.findAll(pageable);
        }
    }

    @Override
    public void updateProduct(Product product, long id) {
        if(checkUtil.isAdminRoleCheck()) {
            LocalDateTime timeStamp = LocalDateTime.now();
            Product updatedProduct = getProduct(id);
            updatedProduct.setProductName(product.getProductName());
            updatedProduct.setProductBrand(product.getProductBrand());
            updatedProduct.setCalories(product.getCalories());
            updatedProduct.setProteins(product.getProteins());
            updatedProduct.setFats(product.getFats());
            updatedProduct.setWeight(product.getWeight());
            updatedProduct.setUserWhoMadeTheEntry(userHolder.getUser());
            updatedProduct.setUpdateDate(timeStamp);
            productDAO.save(updatedProduct);
        } else {
            throw new IllegalArgumentException("Обновлять продукты может только администратор");
        }
    }

    @Override
    public void deleteProduct(long id) {
        if (productDAO.getById(id) == null) {
            throw new IllegalArgumentException(" Продукт по данному ID не найден");
        }
        if(checkUtil.isAdminRoleCheck()) {
            productDAO.deleteById(id);
        } else {
            throw new IllegalArgumentException ("Удалять продукты может только администратор");
        }
    }
}
