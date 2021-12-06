package by.it_academy.jd2.Mk_JD2_82_21.final_project.service;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.security.UserHolder;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IUserService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.dao.IProductDAO;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.api.enums.ERole;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final IProductDAO productDAO;
    private final UserHolder userHolder;


    @Autowired
    public ProductService(IProductDAO productDAO, UserHolder userHolder) {
        this.productDAO = productDAO;
        this.userHolder = userHolder;
    }

    @Override
    public void addProduct(Product product) {
        User user = userHolder.getUser();
        if(productDAO.getById(product.getId()) == null || user.getRole().equals(ERole.ROLE_ADMIN)) {
            LocalDateTime timeStamp = LocalDateTime.now();
            product.setCreateDate(timeStamp);
            product.setUpdateDate(timeStamp);
            product.setUserWhoMadeTheEntry(user);
            productDAO.save(product);
        } else {
            throw new IllegalCallerException ("Добавлять продукты может только администратор, " +
                    "либо если продукта нету в базе данных");
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
            return productDAO.findProductsByProductName(name, pageable);
        } else {
            return productDAO.findAll(pageable);
        }
    }

    @Override
    public void updateProduct(Product product, long id, LocalDateTime dt_update) {
        LocalDateTime timeStamp = LocalDateTime.now();
        Product updatedProduct = getProduct(id);
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductBrand(product.getProductBrand());
        updatedProduct.setCalories(product.getCalories());
        updatedProduct.setProteins(product.getProteins());
        updatedProduct.setFats(product.getFats());
        updatedProduct.setWeight(product.getWeight());
        updatedProduct.setUserWhoMadeTheEntry(product.getUserWhoMadeTheEntry());
        updatedProduct.setUpdateDate(timeStamp);
        productDAO.save(updatedProduct);
    }

    @Override
    public void deleteProduct(long id, LocalDateTime dt_update) {
        productDAO.deleteById(id);
    }


}
