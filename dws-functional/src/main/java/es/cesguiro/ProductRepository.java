package es.cesguiro;

import java.util.List;
import java.util.Optional;

public class ProductRepository {

    List<Product> products = List.of(
            new Product(1L, "Product 1"),
            new Product(2L, "Product 2"),
            new Product(3L, "Product 3"),
            new Product(4L, "Product 4"),
            new Product(5L, "Product 5")
    );

    Optional<Product> findById(Long id) {
        return products
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }
}
