package by.it_academy.jd2.Mk_JD2_82_21.final_project.controller;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.service.api.IProductService;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.Product;
import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;
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


    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProductList (@RequestParam(value = "page",required = false, defaultValue = "0") int page,
                                                         @RequestParam(value = "size",required = false, defaultValue = "30") int size,
                                                         @RequestParam(value = "name",required = false) String name) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
            Page <Product> productList = productService.getProductList(pageable,name);
      return productList != null &&  !productList.isEmpty()
              ? new ResponseEntity<>(productList, HttpStatus.OK)
              : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> addProduct (@RequestBody Product product) {
        try {
            productService.addProduct(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalCallerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update,
                                           @RequestBody Product product) {
        productService.updateProduct(product, id,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id,
                                           @PathVariable("dt_update") LocalDateTime dt_update) {
        productService.deleteProduct(id,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
