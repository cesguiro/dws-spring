package es.cesguiro.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import es.cesguiro.config.PropertiesConfig;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
    private String next;
    private String previous;

    public PaginatedResponse(List<T> data, long total, int currentPage, int pageSize) {
        this.data = data;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        /*this.next = createNextLink(baseUrl);
        this.previous = createPreviousLink(baseUrl);*/
    }


    /*public PaginatedResponse(List<T> data, int total, int currentPage, int pageSize, String baseUrl) {
        this.data = data;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.next = createNextLink(baseUrl);
        this.previous = createPreviousLink(baseUrl);
    }*/

    public void createPaginatedLinks(String baseUrl) {
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
