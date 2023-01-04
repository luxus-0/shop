package pl.nowogorski.shop.image;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
class ProductImageController {

    private final ProductImageImpl productImage;

    ProductImageController(ProductImageImpl productImage) {
        this.productImage = productImage;
    }

    @GetMapping("/image/{fileName}")
    ResponseEntity<Resource> serveImages(@PathVariable String fileName) throws IOException {
        Resource file = productImage.serveFiles(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
                .body(file);
    }

    @PostMapping("/image/upload")
    ResponseEntity<UploadImageResponseDto> uploadImage(@RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(productImage.transferImage(multipartFile));
    }
}
