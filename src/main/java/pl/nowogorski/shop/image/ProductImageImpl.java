package pl.nowogorski.shop.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.nowogorski.shop.admin.AdminProductFileException;
import pl.nowogorski.shop.utils.RenameFileUtils;
import pl.nowogorski.shop.utils.SlugifyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
class ProductImageImpl {

    private final SlugifyUtils slugifyUtils;
    private final RenameFileUtils renameFileUtils;
    @Value(("${app.uploadDir}"))
    private String uploadDir;

    ProductImageImpl(SlugifyUtils slugifyUtils, RenameFileUtils renameFileUtils) {
        this.slugifyUtils = slugifyUtils;
        this.renameFileUtils = renameFileUtils;
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
        String newFileName = slugifyUtils.generateSlugFromFileName(fileName);
        newFileName = renameFileUtils.renameIfExists(Path.of(uploadDir), newFileName);
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
