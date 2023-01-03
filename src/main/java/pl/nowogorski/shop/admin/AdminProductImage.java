package pl.nowogorski.shop.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
class AdminProductImage {

    private final UploadedFileNameUtils uploadedFileNameUtils;
    private final ExistingFileRenameUtils existingFileRenameUtils;
    @Value(("${app.uploadDir}"))
    private String uploadDir;

    AdminProductImage(UploadedFileNameUtils uploadedFileNameUtils, ExistingFileRenameUtils existingFileRenameUtils) {
        this.uploadedFileNameUtils = uploadedFileNameUtils;
        this.existingFileRenameUtils = existingFileRenameUtils;
    }

    String uploadImage(String fileName, InputStream inputStream) {
        String newFileName = uploadedFileNameUtils.generateSlugFromString(fileName);
        newFileName = existingFileRenameUtils.renameIfExists(Path.of(uploadDir), newFileName);
        Path path = Paths.get(uploadDir).resolve(newFileName);
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            inputStream.transferTo(outputStream);
        } catch (IOException e) {
            throw new ProductFileSavedException();
        }
        return newFileName;
    }

    Resource serveFiles(String fileName) {
        FileSystemResourceLoader fileSystemResourceLoader = new FileSystemResourceLoader();
        return fileSystemResourceLoader.getResource(uploadDir + fileName);

    }
}
