package es.cesguiro.domain.admin.usecase;

public interface InsertGenresToBookUseCase {

    void execute(String isbn, String[] genres);
}
