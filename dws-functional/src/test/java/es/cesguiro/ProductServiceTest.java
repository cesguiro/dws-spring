package es.cesguiro;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void findByIdWithExistingIdReturnsExpectedProduct() {
        Product expected = new Product(1L, "Product 1");
        ProductService productService = new ProductService();
        assertEquals(expected, productService.findById(1L));
    }

    @Test
    void findByIdWithNonExistingIdThrowsException() {
        ProductService productService = new ProductService();
        assertThrows(RuntimeException.class, () -> productService.findById(100L));
    };

}