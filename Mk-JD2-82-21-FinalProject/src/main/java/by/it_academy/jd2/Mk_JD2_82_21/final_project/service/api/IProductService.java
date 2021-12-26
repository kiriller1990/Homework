package by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface IProductService {
    public void addProduct(Product product);
    public Product getProduct(long productId);
    public Page<Product> getProductList(Pageable pageable, String name);
    void updateProduct(Product product, long id);
    void deleteProduct(long id);
}
