package pl.nowogorski.shop.admin;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.admin.dto.AdminProductDto;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;

@RestController
class AdminProductController {

    private final AdminProductImpl adminProductImpl;

    AdminProductController(AdminProductImpl adminProductImpl) {
        this.adminProductImpl = adminProductImpl;
    }
    @GetMapping("/admin/products")
    Page<AdminProduct> getProducts(@RequestParam int page, @RequestParam int size){
        return adminProductImpl.readProducts(page, size);
    }

    @GetMapping("/admin/products")
    ResponseEntity<List<AdminProductDto>> readProducts() {
        return ResponseEntity.ok(adminProductImpl.readProducts());
    }

    @GetMapping("/admin/products/{id}")
    ResponseEntity<AdminProductDto> readProducts(@PathVariable Long id) throws AdminProductNotFoundException {
        return ResponseEntity.ok(adminProductImpl.readProducts(id));
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    AdminProductDto createProduct(@RequestBody AdminProduct product) throws AdminProductNotFoundException {
        return adminProductImpl.addProduct(product);
    }

    @PutMapping("/{id}")
    ResponseEntity<AdminProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(adminProductImpl.actualizeProduct(id, productDto));
    }

    @DeleteMapping
    ResponseEntity<ProductDto> removeProduct() {
        adminProductImpl.clearProduct();
        return ResponseEntity.noContent().build();
    }
}
