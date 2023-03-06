package pl.nowogorski.shop.admin.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
class AdminCartController {

    private AdminCartService adminCartService;

    AdminCartController(AdminCartService adminCartService) {
        this.adminCartService = adminCartService;
    }

    @GetMapping("/{id}")
    AdminCartSummaryDto readCart(@PathVariable Long id){
        return AdminCartMapper.mapToCartSummary(adminCartService.readCart(id));
    }

    @PutMapping("/{id}")
    AdminCartSummaryDto addProductToCart(@PathVariable Long id, @RequestBody AdminCartProductDto adminCartProductDto) {
        return AdminCartMapper.mapToCartSummary(adminCartService.addProductToCart(id, adminCartProductDto));
    }

    @PutMapping("/{id}/update")
    AdminCartSummaryDto updateCart(@PathVariable Long id, @RequestBody List<AdminCartProductDto> adminCartProductDtos){
        return AdminCartMapper.mapToCartSummary(adminCartService.actualizeCart(id, adminCartProductDtos));

    }


}
