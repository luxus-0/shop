package pl.nowogorski.shop.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RenameFileUtilsTest {
    @Test
    void shouldRenameExistingFile(@TempDir Path pathFile) throws IOException {
        //given
        RenameFileUtils existingFileRenameUtils = new RenameFileUtils();
        //when
        Files.createFile(pathFile.resolve("file.png"));
        String changedNameFile = existingFileRenameUtils.renameIfExists(pathFile, "file.png");
        //then
        assertEquals("file-1.png", changedNameFile);
    }

    @Test
    void shouldNotRenameExistingFile(@TempDir Path pathFile) throws IOException {
        //given
        RenameFileUtils existingFileRenameUtils = new RenameFileUtils();
        //when
        String changedNameFile = existingFileRenameUtils.renameIfExists(pathFile, "file.png");
        //then
        assertEquals("file.png", changedNameFile);
    }

    @Test
    void shouldRenameManyExistingFile(@TempDir Path pathFile) throws IOException {
        //given
        RenameFileUtils existingFileRenameUtils = new RenameFileUtils();
        //when
        Files.createFile(pathFile.resolve("file.png"));
        Files.createFile(pathFile.resolve("file-1.png"));
        Files.createFile(pathFile.resolve("file-2.png"));
        Files.createFile(pathFile.resolve("file-3.png"));
        Files.createFile(pathFile.resolve("file-4.png"));

        String changedNameFile = existingFileRenameUtils.renameIfExists(pathFile, "file.png");
        //then
        assertEquals("file-5.png", changedNameFile);
    }

    @Test
    void shouldRenameExistingFileWithSpecialChar(@TempDir Path pathFile) {
        //given
        RenameFileUtils existingFileRenameUtils = new RenameFileUtils();
        //when
        String changedNameFile = existingFileRenameUtils.renameIfExists(pathFile, "file-1.png");
        //then
        assertEquals("file-1.png", changedNameFile);
    }
}