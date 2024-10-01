package es.cesguiro.controller;

import es.cesguiro.controller.model.author.AuthorQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AuthorController.URL)
public class AuthorController {

    public static final String URL = "/authors";

    @GetMapping("/{id}")
    public AuthorQuery findById(@PathVariable long id) {
        return null;
    }
}
