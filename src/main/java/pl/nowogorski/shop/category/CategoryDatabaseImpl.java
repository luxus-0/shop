package pl.nowogorski.shop.category;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CategoryDatabaseImpl {

    private final CategoryRepository categoryRepository;
    public static final Long EMPTY_ID = null;

    CategoryDatabaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    List<Category> findCategories() {
        return categoryRepository.findAll();
    }

    Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    Category createCategory(CategoryDto categoryDto) {
        return categoryRepository.save(mapToCategory(categoryDto));
    }

    private Category mapToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(CategoryDatabaseImpl.EMPTY_ID)
                .name(categoryDto.name())
                .description(categoryDto.description())
                .slug(slugifyCategoryName(categoryDto.slug()))
                .build();
    }

    private String slugifyCategoryName(String slug) {
        Slugify slugify = Slugify.builder()
                .customReplacement("_", "-")
                .build();
        return slugify.slugify(slug);
    }

    Category actualizeCategory(CategoryDto category, Long id) {
        return categoryRepository.save(mapToCategory(category));
    }

    void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category findCategoriesWithProducts(String slug) {
        return categoryRepository.findBySlug(slug);
    }
}
