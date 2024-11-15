package es.cesguiro.domain.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.model.Author;
import es.cesguiro.domain.repository.AuthorRepository;
import es.cesguiro.domain.service.AuthorService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getByIdBook(long idBook) {
        return authorRepository.getByIdBook(idBook);
    }

    @Override
    public List<Author> getByIsbnBook(String isbn) {
        return authorRepository.getByIsbnBook(isbn);
    }

    @Override
    public List<Author> findAllById(List<Author> authors) {
        List<Author> foundAuthors =  authorRepository.findAllById(
                authors
                        .stream()
                        .map(Author::getId)
                        .toArray(Long[]::new)
        );
        if(foundAuthors.size() != authors.size()) {
            throw new ResourceNotFoundException("Some authors were not found");
        }
        return foundAuthors;
    }
}
