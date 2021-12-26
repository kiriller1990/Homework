package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.utils.OptimisticLock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final IProductService productService;
    private final OptimisticLock optimisticLock;


    public ProductController(IProductService productService, OptimisticLock optimisticLock) {
        this.productService = productService;
        this.optimisticLock = optimisticLock;
    }

    @GetMapping
    public ResponseEntity<?> getProductList (@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                         @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                         @RequestParam(value = "name",required = false) String name) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("productName"));
            Page <Product> productList = productService.getProductList(pageable,name);
      return productList != null &&  !productList.isEmpty()
              ? new ResponseEntity<>(productList, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<?> addProduct (@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return new ResponseEntity<>("Продукт успешно добавлен",HttpStatus.CREATED);
        } catch (IllegalCallerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") long dtUpdate,
                                           @RequestBody Product product) {
        try {
            optimisticLock.productOptimisticLock(dtUpdate,id);
            productService.updateProduct(product, id);
            return new ResponseEntity<>("Продукт успешно обновлен",HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") long dtUpdate) {
        try {
            optimisticLock.productOptimisticLock(dtUpdate,id);
            productService.deleteProduct(id);
            return new ResponseEntity<>("Продукт успешно удален" ,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
