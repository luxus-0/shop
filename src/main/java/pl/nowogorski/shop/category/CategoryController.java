package pl.nowogorski.shop.category;

import org.springframework.web.bind.annotation.*;

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
