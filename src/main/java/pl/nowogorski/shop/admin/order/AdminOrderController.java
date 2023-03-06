package pl.nowogorski.shop.admin.order;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.nowogorski.shop.admin.order.dto.AdminOrderDto;

import java.util.Map;

import static pl.nowogorski.shop.admin.order.AdminOrderMapper.mapToPageDtos;

@RestController
@RequestMapping("/admin/orders")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    Page<AdminOrderDto> getOrders(Pageable pageable){
        return mapToPageDtos(adminOrderService.getOrders(pageable));
    }

    @GetMapping("/{id}")
    public AdminOrder getOrders(@PathVariable Long id) {
        return adminOrderService.getOrder(id);
    }

    @PatchMapping("/{id}")
    public void actualizeOrder(@PathVariable Long id, @RequestBody Map<String, String> values){
        adminOrderService.updateOrder(id, values);
    }


}
