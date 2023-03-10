package pl.nowogorski.shop.category;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.category.dto.CategoryDto;

import java.util.List;

@RestController
class CategoryController {

    private final CategoryDatabaseImpl categoryDatabaseImpl;

    CategoryController(CategoryDatabaseImpl categoryDatabaseImpl) {
        this.categoryDatabaseImpl = categoryDatabaseImpl;
    }


    @GetMapping("/categories")
    List<Category> readCategories(){
        return categoryDatabaseImpl.findCategories();
    }

    @GetMapping("categories/{id}")
    Category readCategory(@PathVariable Long id){
        return categoryDatabaseImpl.findCategory(id);
    }

    @GetMapping("/{slug}/products")
    CategoryProductDto getCategoriesWithProducts(@PathVariable
                                       @Pattern(regexp = "[a-zA-Z0-9\\-]+")
                                       @Length(max = 255) String slug, Pageable pageable){
        return categoryDatabaseImpl.findCategoriesWithProducts(slug, pageable);
    }
    @PostMapping
    Category addCategory(@RequestBody CategoryDto categoryDto){
        return categoryDatabaseImpl.createCategory(categoryDto);
    }

    @PutMapping("categories/{id}")
    Category updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long id){
        return categoryDatabaseImpl.actualizeCategory(categoryDto, id);
    }

    @DeleteMapping("categories/{id}")
    void deleteCategory(@PathVariable Long id){
        categoryDatabaseImpl.removeCategory(id);
    }
}
