package es.cesguiro;

public class ProductService {

    private final ProductRepository productRepository = new ProductRepository();

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
