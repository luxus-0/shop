package pl.nowogorski.shop.admin.product;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.admin.product.dto.AdminProductDto;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.util.List;

import static pl.nowogorski.shop.admin.product.AdminProductMapper.mapAdminProduct;

@RestController
class AdminProductController {

    private final AdminProductImpl adminProductImpl;

    AdminProductController(AdminProductImpl adminProductImpl) {
        this.adminProductImpl = adminProductImpl;
    }

    @GetMapping("/admin/products")
    ResponseEntity<List<AdminProductDto>> readProducts() {
        return ResponseEntity.ok(adminProductImpl.readProducts());
    }

    @GetMapping("/admin/products/{id}")
    ResponseEntity<AdminProductDto> readProducts(@PathVariable Long id) throws AdminProductNotFoundException {
        return ResponseEntity.ok(adminProductImpl.readProducts(id));
    }

    @GetMapping("/admin/products/page")
    ResponseEntity<Page<AdminProduct>> readProducts(@RequestBody @Valid PageRequest pageRequest) {
        return ResponseEntity.ok(adminProductImpl.readProducts(pageRequest));
    }

    @PostMapping("/admin/products")
    @ResponseStatus(HttpStatus.CREATED)
    AdminProduct createProduct(@RequestBody @Valid AdminProductDto adminProductDto) {
        return adminProductImpl.addProduct(mapAdminProduct(adminProductDto));
    }

    @PutMapping("/admin/products/{id}")
    ResponseEntity<AdminProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        return ResponseEntity.ok(adminProductImpl.actualizeProduct(id, productDto));
    }

    @DeleteMapping("/admin/products")
    ResponseEntity<ProductDto> removeProduct() {
        adminProductImpl.clearProduct();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/products/{id}")
    ResponseEntity<ProductDto> removeProduct(@PathVariable Long id) {
        adminProductImpl.clearProduct(id);
        return ResponseEntity.noContent().build();
    }
}
