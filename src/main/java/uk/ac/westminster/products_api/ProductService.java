package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 3L;

    // Constructor: add sample products when the application starts
    public ProductService() {

        // Add a digital product
        products.add(new DigitalProduct(
                1L,
                "Java Programming E-book",
                2500.00,
                "https://example.com/java-ebook",
                15.5
        ));

        // Add a physical product
        products.add(new PhysicalProduct(
                2L,
                "Java Programming Book",
                4500.00,
                0.8,
                "24 x 18 x 3 cm"
        ));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public void deleteProduct(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }


}
