package es.cesguiro.domain.admin.usecase.book;

import es.cesguiro.domain.admin.model.Book;

public interface InsertAdminUseCase {

    /**
     * book es de tipo Book
     * Si book = null => Excepción NullPropia
     * Si book existe => ResourceAlreadyExistsException
     * Si book.publisher no existe => ResourceNotFoundException
     * ....
     * Si no, book se inserta
     * @param book
     */
    void execute(Book book);
}
