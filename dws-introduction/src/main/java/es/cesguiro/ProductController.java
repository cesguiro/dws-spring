package es.cesguiro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ProductController.URL)
@RestController
@RequiredArgsConstructor
public class ProductController {
    public static final String URL = "/products";
    private final ProductService productService;

    @GetMapping
    public void getAll() {
        productService.getAll();
    }
}
