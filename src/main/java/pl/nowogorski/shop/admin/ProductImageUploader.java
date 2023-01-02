package pl.nowogorski.shop.admin;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.nowogorski.shop.admin.dto.UploadImageResponseDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.nowogorski.shop.admin.ProductImagePathFile.IMAGE_PATH;

@Service
class ProductImageUploader {
    UploadImageResponseDto uploadImage(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();

        if(fileName != null) {
            Path path = Paths.get(IMAGE_PATH).resolve(fileName);

            try (InputStream inputStream = multipartFile.getInputStream()) {
                OutputStream outputStream = Files.newOutputStream(path);
                inputStream.transferTo(outputStream);
                return new UploadImageResponseDto(fileName);
            } catch (IOException e) {
                throw new ProductFileSavedException();
            }
        }
        return new UploadImageResponseDto("File name not found");
    }

    ResponseEntity<Resource> serveFiles(String fileName) throws IOException {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        Resource resource = fileSystemResourceLoader.getResource(IMAGE_PATH + fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Path.of(fileName)))
                .body(resource);
    }
}