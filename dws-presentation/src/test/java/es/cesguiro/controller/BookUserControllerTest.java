package es.cesguiro.controller;

import es.cesguiro.controller.user.BookUserController;
import es.cesguiro.data.BookTestData;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.user.service.BookUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookUserControllerTest {

    @Autowired
    private BookUserController bookUserController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookUserService bookUserService;

    @Test
    @DisplayName("Test BookController implementation")
    void testBookController() {
        String url = bookUserController.URL;
        assertEquals("/api/books", url);
    }


    @Test
    @DisplayName("Test getAll method")
    void testGetAll() throws Exception {
        when(bookUserService.getAll()).thenReturn(BookTestData.getBooks());

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Verificar que el código de estado sea 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].isbn").value("1111"))
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[0].price").value(12.30))
                .andExpect(jsonPath("$[0].discount").value(10.0f))
                .andExpect(jsonPath("$[0].cover").value("cover1.jpg"))
                .andExpect(jsonPath("$[0].synopsis").doesNotExist())
                .andExpect(jsonPath("$[0].publisher").doesNotExist())
                .andExpect(jsonPath("$[0].category").doesNotExist())
                .andExpect(jsonPath("$[0].authors").doesNotExist())
                .andExpect(jsonPath("$[0].genres").doesNotExist());
    }

    @Test
    @DisplayName("Test findByIsbn method")
    void testFindByIsbn() throws Exception {
        when(bookUserService.findByIsbn("1111")).thenReturn(BookTestData.getBooks().get(0));

        mockMvc.perform(get("/api/books/1111")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Verificar que el código de estado sea 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isbn").value("1111"))
                .andExpect(jsonPath("$.title").value("Book 1"))
                .andExpect(jsonPath("$.price").value(12.30))
                .andExpect(jsonPath("$.discount").value(10.0f))
                .andExpect(jsonPath("$.cover").value("cover1.jpg"))
                .andExpect(jsonPath("$.synopsis").exists())
                .andExpect(jsonPath("$.publisher").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.authors").exists())
                .andExpect(jsonPath("$.authors").isArray())
                .andExpect(jsonPath("$.authors").isNotEmpty())
                .andExpect(jsonPath("$.authors.length()").value(2))
                .andExpect(jsonPath("$.authors[0].name").value("Author 1"))
                .andExpect(jsonPath("$.genres").exists());
    }

    @Test
    @DisplayName("Test findByIsbn method with isbn not found")
    void testFindByIsbnNotFound() throws Exception {
        String isbn = "9999";
        when(bookUserService.findByIsbn(isbn)).thenThrow(new ResourceNotFoundException("Book isbn " + isbn + " not found"));

        mockMvc.perform(get("/api/books/{isbn}", isbn)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())  // Verificar que el código de estado sea 404
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("ResourceNotFoundException"))
                .andExpect(jsonPath("$.message").value("Resource not found. Book isbn 9999 not found"));
    }
}