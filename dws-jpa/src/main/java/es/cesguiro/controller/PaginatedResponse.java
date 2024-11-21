package es.cesguiro.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class PaginatedResponse<T> {


    private List<T> data;
    private long total;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private String next;
    private String previous;

    public PaginatedResponse(List<T> data, long total, int currentPage, int pageSize, String baseUrl) {
        this.data = data;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        //this.totalPages = (int) (total + pageSize - 1) / pageSize;
        this.totalPages = (int) Math.ceil((double) total / pageSize);
        this.createPaginatedLinks(baseUrl);
    }

    private void createPaginatedLinks(String baseUrl) {
        this.next = createNextLink(baseUrl);
        this.previous = createPreviousLink(baseUrl);
    }

    private String createNextLink(String baseUrl) {
        if(currentPage * pageSize < total) {
            return baseUrl + "?page=" + (currentPage + 1) + "&size=" + pageSize;
        }
        return null;
    }

    private String createPreviousLink(String baseUrl) {
        if(currentPage > 1) {
            return baseUrl + "?page=" + (currentPage - 1) + "&size=" + pageSize;
        }
        return null;
    }
}
