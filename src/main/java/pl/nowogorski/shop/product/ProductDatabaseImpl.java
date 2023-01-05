package pl.nowogorski.shop.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nowogorski.shop.product.dto.ProductDto;
import pl.nowogorski.shop.review.Review;
import pl.nowogorski.shop.review.ReviewDto;

import java.util.List;
import java.util.Optional;

@Service
class ProductDatabaseImpl {

    private final ProductRepository productRepository;

    ProductDatabaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    List<Product> readProducts() {
        return productRepository.findAll();
    }
    Optional<Product> readProducts(Long id) {
        return productRepository.findById(id);
    }

    Page<Product> readProducts(Pageable pageable){
        return productRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    ProductDto mapToProductDto(Product product, List<Review> reviews){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .description(product.getDescription())
                .fullDescription(product.getFullDescription())
                .price(product.getPrice())
                .currency(product.getCurrency())
                .image(product.getImage())
                .slug(product.getSlug())
                .reviews(reviews.stream().map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .productId(review.getId())
                        .authorName(review.getAuthorName())
                        .content(review.getContent())
                        .moderate(review.isModerated())
                        .build())
                        .toList()
                ).build();
    }

    public Product readProductBySlug(String slug) {
        return productRepository.findBySlug(slug).orElseThrow();
    }
}
