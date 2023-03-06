package pl.nowogorski.shop.admin.order;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.admin.order.dto.AdminOrderDto;

import static pl.nowogorski.shop.admin.order.AdminOrderMapper.mapToPageDtos;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    Page<AdminOrderDto> getOrders(Pageable pageable){
        return mapToPageDtos(adminOrderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getOrders(@PathVariable Long id) {
        return adminOrderService.getOrder(id);
    }
}
