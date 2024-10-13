package es.cesguiro.data;

import es.cesguiro.domain.user.model.Category;

import java.util.List;

public class CategoryTestData {

    public static List<Category> getCategories() {
        return List.of(
                new Category(1, "Category 1", "category-1"),
                new Category(2, "Category 2", "category-2"),
                new Category(3, "Category 3", "category-3")
        );
    }
}
