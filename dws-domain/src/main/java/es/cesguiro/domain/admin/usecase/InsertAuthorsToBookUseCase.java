package es.cesguiro.domain.admin.usecase;

public interface InsertAuthorsToBookUseCase {

    void execute(String isbn, String[] authors);
}
