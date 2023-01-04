package pl.nowogorski.shop.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.nowogorski.shop.admin.AdminProductFileException;
import pl.nowogorski.shop.utils.ExistingFileRenameUtils;
import pl.nowogorski.shop.utils.UploadedFileNameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
class ProductImageImpl {

    private final UploadedFileNameUtils uploadedFileNameUtils;
    private final ExistingFileRenameUtils existingFileRenameUtils;
    @Value(("${app.uploadDir}"))
    private String uploadDir;

    ProductImageImpl(UploadedFileNameUtils uploadedFileNameUtils, ExistingFileRenameUtils existingFileRenameUtils) {
        this.uploadedFileNameUtils = uploadedFileNameUtils;
        this.existingFileRenameUtils = existingFileRenameUtils;
    }

    UploadImageResponseDto transferImage(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            String savedFileName = uploadImage(multipartFile.getOriginalFilename(), inputStream);
            return new UploadImageResponseDto(savedFileName);
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong, while input file", e);
        }
    }
    String uploadImage(String fileName, InputStream inputStream) {
        String newFileName = uploadedFileNameUtils.generateSlugFromString(fileName);
        newFileName = existingFileRenameUtils.renameIfExists(Path.of(uploadDir), newFileName);
        Path path = Paths.get(uploadDir).resolve(newFileName);
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new AdminProductFileException();
        }
        return newFileName;
    }

    Resource serveFiles(String fileName) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + fileName);

    }
}
