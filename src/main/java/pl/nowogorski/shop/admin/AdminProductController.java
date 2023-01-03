package pl.nowogorski.shop.admin;

import jakarta.validation.Valid;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.nowogorski.shop.admin.dto.AdminProductDto;
import pl.nowogorski.shop.admin.dto.UploadImageResponseDto;
import pl.nowogorski.shop.product.dto.ProductDto;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static pl.nowogorski.shop.admin.AdminProductMapper.mapAdminProduct;

@RestController
class AdminProductController {

    private final AdminProductImpl adminProductImpl;
    private final AdminProductImage adminProductImage;

    AdminProductController(AdminProductImpl adminProductImpl, AdminProductImage adminProductImage) {
        this.adminProductImpl = adminProductImpl;
        this.adminProductImage = adminProductImage;
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
    ResponseEntity<AdminProductDto> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) throws AdminProductNotUpdateException {
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

    @PostMapping("/admin/products/upload_image")
    UploadImageResponseDto uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String savedFileName = adminProductImage.uploadImage(multipartFile.getOriginalFilename(), inputStream);
            return new UploadImageResponseDto(savedFileName);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong, while input file", e);
        }
    }

    @GetMapping("/admin/products/{fileName}")
    ResponseEntity<Resource> serveImages(@PathVariable String fileName) throws IOException {
        Resource file = adminProductImage.serveFiles(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
                .body(file);
    }
}
