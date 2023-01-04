package pl.nowogorski.shop.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class CategoryController {

    private final CategoryDatabaseImpl categoryDatabaseImpl;

    CategoryController(CategoryDatabaseImpl categoryDatabaseImpl) {
        this.categoryDatabaseImpl = categoryDatabaseImpl;
    }


    @GetMapping
    List<Category> readCategories(){
        return categoryDatabaseImpl.findCategories();
    }

    @GetMapping("{/id}")
    Category readCategory(@PathVariable Long id){
        return categoryDatabaseImpl.findCategory(id);
    }

    @PostMapping
    Category addCategory(@RequestBody CategoryDto categoryDto){
        return categoryDatabaseImpl.createCategory(categoryDto);
    }

    @PutMapping("/{id}")
    Category updateCategory(@RequestBody CategoryDto categoryDto){
        return categoryDatabaseImpl.actualizeCategory(categoryDto);
    }

    @DeleteMapping("/{id}")
    void deleteCategory(@PathVariable Long id){
        categoryDatabaseImpl.removeCategory(id);
    }
}
