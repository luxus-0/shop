package pl.nowogorski.shop.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AdminProductController {

    private final AdminProductImpl adminProductImpl;

    AdminProductController(AdminProductImpl adminProductImpl) {
        this.adminProductImpl = adminProductImpl;
    }
    @GetMapping("/admin/products")
    Page<AdminProduct> getProducts(@RequestParam int page, @RequestParam int size){
        return adminProductImpl.readProducts(page, size);
    }
}
