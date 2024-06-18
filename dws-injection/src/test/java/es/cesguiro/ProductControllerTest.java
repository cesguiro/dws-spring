package es.cesguiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getAll() throws Exception {
        doNothing().when(productService).getAll();
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));;
    }
}