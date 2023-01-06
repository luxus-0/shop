package pl.nowogorski.shop.admin.product;

import com.github.slugify.Slugify;
import org.springframework.stereotype.Service;
import pl.nowogorski.shop.admin.product.dto.AdminProductDto;

@Service
class AdminProductMapper {
    private static final Long EMPTY_ID = null;
    
    static AdminProduct mapAdminProduct(AdminProductDto adminProductDto) {
        return new AdminProduct(EMPTY_ID,
                adminProductDto.name(),
                adminProductDto.categoryId(),
                adminProductDto.description(),
                adminProductDto.fullDescription(),
                adminProductDto.price(),
                adminProductDto.currency(),
                adminProductDto.image(),
                slugify(adminProductDto.slug()));
    }

    static AdminProductDto mapAdminProductDto(AdminProduct adminProduct) {
        return new AdminProductDto(
                adminProduct.getName(),
                adminProduct.getCategoryId(),
                adminProduct.getDescription(),
                adminProduct.getFullDescription(),
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
