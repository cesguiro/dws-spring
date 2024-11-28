package es.cesguiro.controller;

import es.cesguiro.config.PropertiesConfig;
import es.cesguiro.controller.webmodel.PaginatedResponse;
import es.cesguiro.domain.model.ListWithCount;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.BiFunction;
import java.util.function.Function;

public abstract class BaseController {

    public static final String ADMIN_PATH = "${app.admin.path}";
    private final String DEFAULT_PAGE_SIZE = PropertiesConfig.getSetting("app.pageSize.default");

    protected abstract <R> ListWithCount<R> getList(int page, int size);


    protected  <R, T> PaginatedResponse<T> getAll(
            int page,
            Integer size,
            Function<R, T> mapper,
            BiFunction<Integer, Integer, ListWithCount<R>> useCase,,
            String baseUrl
    ) {
        int pageSize = (size != null) ? size : Integer.parseInt(DEFAULT_PAGE_SIZE);
        ListWithCount<R> listWithCount = useCase.apply(page - 1, pageSize);
        return new PaginatedResponse<>(
                listWithCount.getList().stream().map(mapper).toList(),
                listWithCount.getCount(), page, pageSize, baseUrl);
    }

    protected <T> ResponseEntity<T> buildResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

}
