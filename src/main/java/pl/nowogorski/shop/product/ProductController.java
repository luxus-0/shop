package pl.nowogorski.shop.product;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;

@RestController
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

    @GetMapping("/{page}/{size}")
    ResponseEntity<Page<Product>> readProducts(@PathVariable int page,@PathVariable int size){
        return ResponseEntity.ok(productDatabaseImpl.readProducts(page, size));
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto createProduct(@RequestBody Product product) throws ProductNotFoundException {
        return productDatabaseImpl.addProduct(product);
    }

    @PutMapping("/{id}")
    ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productDatabaseImpl.actualizeProduct(id, productDto));
    }

    @DeleteMapping
    ResponseEntity<ProductDto> removeProduct() {
        productDatabaseImpl.clearProduct();
        return ResponseEntity.noContent().build();
    }
}
