package pl.nowogorski.shop.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;

@RestController
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

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto createProduct(@RequestBody @Valid Product product) throws ProductNotFoundException {
        return productDatabaseImpl.addProduct(product);
    }

    @PutMapping("products/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(productDatabaseImpl.actualizeProduct(id, productDto));
    }

    @DeleteMapping
    ResponseEntity<ProductDto> removeProduct() {
        productDatabaseImpl.clearProduct();
        return ResponseEntity.noContent().build();
    }
}
