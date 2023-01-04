package pl.nowogorski.shop.product;

import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;

@RestController
@Validated
@CrossOrigin("*")
class ProductController {

    private final ProductDatabaseImpl productDatabaseImpl;

    ProductController(ProductDatabaseImpl productDatabaseImpl) {
        this.productDatabaseImpl = productDatabaseImpl;
    }

    @GetMapping("/products")
    ResponseEntity<List<ProductDto>> readProducts() {
        return ResponseEntity.ok(productDatabaseImpl.readProducts());
    }

    @GetMapping("/products/{id}")
    ResponseEntity<ProductDto> readProducts(@PathVariable Long id) throws ProductNotFoundException {
        return ResponseEntity.ok(productDatabaseImpl.readProducts(id));
    }

    @GetMapping
    ResponseEntity<Page<Product>> readProducts(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(productDatabaseImpl.readProducts(page, size));
    }

    @GetMapping("products/{slug}")
    Product readProductBySlug(@PathVariable
                              @Pattern(regexp = "[a-z0-9\\-]+")
                              @Length(max = 255)
                              String slug){
        return productDatabaseImpl.readProductBySlug(slug);
    }
}
