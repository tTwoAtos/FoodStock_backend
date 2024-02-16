package org.aelion.categories.productToCategory;

import jakarta.websocket.OnOpen;
import org.aelion.categories.productToCategory.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories/products")
public class ProductToCategoryController {
    @Autowired
    private ProductToCategoryService service;

    @GetMapping("/{productEan}")
    public ResponseEntity<?> getCategoriesIdsByProductId(@PathVariable String productEan) {
        List<ProductToCategory> productToCategories = service.getCategoriesIdsByProductEan(productEan);

        if(productToCategories == null)
            return new ResponseEntity<>("Not categories was found with this product id " , HttpStatus.NOT_FOUND);

        List<Long> categoriesIds = productToCategories.stream().map((ProductToCategory cat) -> cat.getCategoryId()).toList();
        return new ResponseEntity<>(categoriesIds, HttpStatus.FOUND);
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getRandomProductByCategory(@PathVariable Long categoryId) {
        ProductToCategory product = service.getProductsByCategoryId(categoryId);

        if(product == null)
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @GetMapping("/related/{categoryId}")
    public  ResponseEntity<?> getRelatedCategories(@PathVariable Long categoryId){
        List<ProductToCategory> productToCategories =  service.getRelatedCategories(categoryId);

        if (productToCategories.isEmpty())
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(productToCategories, HttpStatus.OK);
    }
    @PostMapping("/{productEan}")
    public ResponseEntity<?> add(@PathVariable String productEan, @RequestBody CategoriesDto dto) {
        return service.add(productEan, dto.getCategoriesIds());
    }

}
