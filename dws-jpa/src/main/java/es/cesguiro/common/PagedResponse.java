package es.cesguiro.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PagedResponse<T> {

    private final List<T> content;
    private final int total;
}
