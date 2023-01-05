package pl.nowogorski.shop.category;

import com.github.slugify.Slugify;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.product.Product;
import pl.nowogorski.shop.product.ProductRepository;
import pl.nowogorski.shop.product.dto.ProductListDto;

import java.util.List;

@Service
class CategoryDatabaseImpl {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public static final Long EMPTY_ID = null;

    CategoryDatabaseImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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

    @Transactional(readOnly = true)
    public CategoryProductDto findCategoriesWithProducts(String slug, Pageable pageable) {
        Category category = categoryRepository.findBySlug(slug);
        Page<Product> page = productRepository.findByCategoryId(category.getId(), pageable);
        List<ProductListDto> productListDtos = page.getContent().stream()
                .map(product -> ProductListDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .currency(product.getCurrency())
                        .image(product.getImage())
                        .slug(product.getSlug())
                        .build())
                .toList();
        return new CategoryProductDto(category ,new PageImpl<>(productListDtos, pageable, page.getTotalElements()));
    }
}
