package pl.nowogorski.shop.product;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.product.dto.ProductListDto;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@CrossOrigin("*")
class ProductController {

    private final ProductDatabaseImpl productDatabaseImpl;

    ProductController(ProductDatabaseImpl productDatabaseImpl) {
        this.productDatabaseImpl = productDatabaseImpl;
    }

    @GetMapping("/products")
    ResponseEntity<List<Product>> readProducts() {
        return ResponseEntity.ok(productDatabaseImpl.readProducts());
    }

    @GetMapping("/products/{id}")
    ResponseEntity<Optional<Product>> readProducts(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productDatabaseImpl.readProducts(id));
    }

    @GetMapping
    Page<ProductListDto> readProducts(Pageable pageable) {
        Page<Product> products = productDatabaseImpl.readProducts(pageable);
        List<ProductListDto> productListDtos = products.getContent().stream()
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
        return new PageImpl<>(productListDtos,pageable, products.getTotalElements());
    }

    @GetMapping("products/{slug}")
    Product readProductBySlug(@PathVariable
                              @Pattern(regexp = "[a-z0-9\\-]+")
                              @Length(max = 255)
                              String slug){
        return productDatabaseImpl.readProductBySlug(slug);
    }
}
