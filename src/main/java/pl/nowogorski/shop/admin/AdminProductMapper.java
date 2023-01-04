package pl.nowogorski.shop.admin;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;
import pl.nowogorski.shop.admin.dto.AdminProductDto;

@Service
class AdminProductMapper {
    private static final Long EMPTY_ID = null;
    
    static AdminProduct mapAdminProduct(AdminProductDto adminProductDto) {
        return new AdminProduct(EMPTY_ID,
                adminProductDto.name(),
                adminProductDto.category(),
                adminProductDto.description(),
                adminProductDto.price(),
                adminProductDto.currency(),
                adminProductDto.image(),
                slugify(adminProductDto.slug()));
    }

    static AdminProductDto mapAdminProductDto(AdminProduct adminProduct) {
        return new AdminProductDto(
                adminProduct.getName(),
                adminProduct.getCategory(),
                adminProduct.getDescription(),
                adminProduct.getPrice(),
                adminProduct.getCurrency(),
                adminProduct.getImage(),
                slugify(adminProduct.getSlug()));
    }

    private static String slugify(String slug) {
        Slugify createdSlug = Slugify.builder()
                .customReplacement("_","-")
                .build();
        return createdSlug.slugify(slug);
    }
}
