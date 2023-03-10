package pl.nowogorski.shop.utils;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class RenameFileUtils {
    public String renameIfExists(Path uploadDir, String fileName){
        if(Files.exists(uploadDir.resolve(fileName))){
            return renameAndCheckFileName(uploadDir, fileName);
        }
        return fileName;
    }

    private String renameAndCheckFileName(Path uploadDir, String fileName) {
        String newName = renameFileName(fileName);
        if(Files.exists(uploadDir.resolve(newName))){
            newName = renameAndCheckFileName(uploadDir, newName);
        }
        return newName;
    }

    private String renameFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        String[] split = name.split("-(?=[0-9]+$)");
        int counter = split.length > 1 ? Integer.parseInt(split[1]) + 1 : 1;
        return split[0] + "-" + counter + "." + FilenameUtils.getExtension(fileName);
    }
}
