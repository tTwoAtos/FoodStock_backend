package org.aelion.categories.categories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Category category1 = new Category(1L, "Category1");
        Category category2 = new Category(2L, "Category2");
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryService.getAll()).thenReturn(categories);

        List<Category> result = categoryController.getAll();
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getName());
        assertEquals("Category2", result.get(1).getName());
    }

    @Test
    public void testGetById() {
        Category category = new Category(1L, "Category1");

        when(categoryService.getById("1")).thenReturn(new ResponseEntity<>(category, HttpStatus.OK));

        ResponseEntity<?> result = categoryController.get("1");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(category, result.getBody());
    }

    @Test
    public void testAdd() {
        List<String> categories = Arrays.asList("Category1", "Category2");
        String productCode = "12345";

        Category category1 = new Category(1L, "Category1");
        Category category2 = new Category(2L, "Category2");
        List<Category> savedCategories = Arrays.asList(category1, category2);

        when(categoryService.add(categories, productCode)).thenReturn(new ResponseEntity<>(savedCategories, HttpStatus.OK));

        ResponseEntity<?> result = categoryController.add(categories, productCode);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(savedCategories, result.getBody());
    }
}