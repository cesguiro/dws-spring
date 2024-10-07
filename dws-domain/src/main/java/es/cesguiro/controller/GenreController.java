package es.cesguiro.controller;

import es.cesguiro.domain.model.command.GenreCommand;
import es.cesguiro.domain.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GenreController.URL)
@RequiredArgsConstructor
public class GenreController {

    public static final String URL = "/api/genres";
    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody GenreCommand genreCommand) {
        genreService.insert(genreCommand);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
