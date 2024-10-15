package es.cesguiro.controller;

import es.cesguiro.controller.webModel.publisher.PublisherQuery;
import es.cesguiro.domain.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PublisherController.URL)
@RequiredArgsConstructor
public class PublisherController {

    public static final String URL = "/api/publishers";

    private final PublisherService publisherService;

    @GetMapping("/{slug}")
    public ResponseEntity<PublisherQuery> findBySlug(@PathVariable String slug) {
       return null;
    }
}
