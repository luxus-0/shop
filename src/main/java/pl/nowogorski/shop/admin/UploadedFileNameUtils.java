package pl.nowogorski.shop.admin;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

@Service
class UploadedFileNameUtils {
    String generateSlugFromString(String fileName){
        String name = FilenameUtils.getBaseName(fileName);
        final Slugify slg = Slugify.builder()
                .customReplacement("_","-")
                .customReplacement("--","-")
                .build();
        String resultFileName = slg.slugify(name);
        return resultFileName + "." + FilenameUtils.getExtension(fileName);
    }
}
