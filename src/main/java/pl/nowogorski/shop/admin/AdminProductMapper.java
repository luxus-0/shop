package pl.nowogorski.shop.admin;

import pl.nowogorski.shop.admin.dto.AdminProductDto;

class AdminProductMapper {
    private static final Long EMPTY_ID = null;

    static AdminProduct mapAdminProduct(AdminProductDto adminProductDto) {
        return new AdminProduct(EMPTY_ID,
                adminProductDto.name(),
                adminProductDto.category(),
                adminProductDto.description(),
                adminProductDto.price(),
                adminProductDto.currency(),
                adminProductDto.image());
    }
}
