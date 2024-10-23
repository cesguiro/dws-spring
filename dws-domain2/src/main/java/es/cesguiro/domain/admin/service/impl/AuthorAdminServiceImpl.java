package es.cesguiro.domain.admin.service.impl;

import es.cesguiro.common.annotation.DomainService;
import es.cesguiro.common.exception.ResourceNotFoundException;
import es.cesguiro.domain.admin.model.Author;
import es.cesguiro.domain.admin.repository.AuthorAdminRepository;
import es.cesguiro.domain.admin.service.AuthorAdminService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class AuthorAdminServiceImpl implements AuthorAdminService {

    private final AuthorAdminRepository authorAdminRepository;

    @Override
    public List<Author> getByIdBook(long idBook) {
        return authorAdminRepository.getByIdBook(idBook);
    }

    @Override
    public List<Author> findAllById(List<Author> authors) {
        List<Author> foundAuthors =  authorAdminRepository.findAllById(
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
