package pl.nowogorski.shop.admin.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import pl.nowogorski.shop.admin.order.dto.AdminOrderDto;

import java.util.List;

public class AdminOrderMapper {
    public static Page<AdminOrderDto> mapToPageDtos(Page<AdminOrder> orders){
        return new PageImpl<>(mapToDtoList(orders.getContent()), orders.getPageable(), orders.getTotalElements());
    }

    private static List<AdminOrderDto> mapToDtoList(List<AdminOrder> content) {
       return content.stream()
                .map(AdminOrderMapper::mapToAdminOderDto)
                .toList();
    }

    private static AdminOrderDto mapToAdminOderDto(AdminOrder adminOrder) {
        return AdminOrderDto.builder()
                .id(adminOrder.getId())
                .adminOrderStatus(adminOrder.getAdminOrderStatus())
                .placeDate(adminOrder.getPlaceDate())
                .grossValue(adminOrder.getGrossValue())
                .build();
    }
}
