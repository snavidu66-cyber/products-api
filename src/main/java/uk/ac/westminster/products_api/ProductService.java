package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductService {
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;
    public List<Product> getAllProducts() {
        return products;
    }
    // ---- in ProductService.java ----
    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
    // ---- in ProductController.java ----
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id).orElse(null);
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product) {

        ProductService productService;
        return productService.addProduct(product);
    }

    public Product addProduct(Product product) {
        product.setId(nextId++); // use the value, then add one
        products.add(product);
        return product;
    }
    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

