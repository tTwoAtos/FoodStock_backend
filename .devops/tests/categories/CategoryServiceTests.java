package org.aelion.categories.categories;

import org.aelion.categories.productToCategory.ProductToCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductToCategoryService productToCategoryService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        Category category1 = new Category(1L, "Category1");
        Category category2 = new Category(2L, "Category2");
        List<Category> categories = Arrays.asList(category1, category2);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAll();
        assertEquals(2, result.size());
        assertEquals("Category1", result.get(0).getName());
        assertEquals("Category2", result.get(1).getName());
    }

    @Test
    public void testGetById() {
        Category category = new Category(1L, "Category1");

        when(categoryRepository.findById("1")).thenReturn(Optional.of(category));

        ResponseEntity<?> result = categoryService.getById("1");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(category, ((Optional<Category>) result.getBody()).get());
    }

    @Test
    public void testAdd() {
        List<String> categories = Arrays.asList("Category1", "Category2");
        String productCode = "12345";

        Category category1 = new Category(1L, "Category1");
        Category category2 = new Category(2L, "Category2");
        List<Category> savedCategories = Arrays.asList(category1, category2);

        when(categoryRepository.saveAll(any())).thenReturn(savedCategories);
        when(productToCategoryService.add(any(), any())).thenReturn(new ResponseEntity<>(savedCategories, HttpStatus.OK));

        ResponseEntity<?> result = categoryService.add(categories, productCode);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(savedCategories, result.getBody());
    }
}