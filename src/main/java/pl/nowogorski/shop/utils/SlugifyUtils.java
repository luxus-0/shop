package pl.nowogorski.shop.utils;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

@Service
public class SlugifyUtils {
    public String generateSlugFromFileName(String fileName){
        String name = FilenameUtils.getBaseName(fileName);
        String resultFileName = slugifySlug(name);
        return resultFileName + "." + FilenameUtils.getExtension(fileName);
    }

    public static String slugifySlug(String slug) {
        Slugify slugify = Slugify.builder()
                .customReplacement("_", "-")
                .build();
        return slugify.slugify(slug);
    }
}
