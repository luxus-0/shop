package pl.nowogorski.shop.admin;

import pl.nowogorski.shop.admin.dto.AdminProductDto;

class AdminProductMapper {
    static AdminProduct mapAdminProduct(AdminProductDto adminProductDto) {
        return AdminProduct.builder()
                .name(adminProductDto.name())
                .category(adminProductDto.category())
                .description(adminProductDto.description())
                .price(adminProductDto.price())
                .currency(adminProductDto.currency())
                .build();
    }
}
