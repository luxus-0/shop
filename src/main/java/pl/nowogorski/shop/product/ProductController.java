package pl.nowogorski.shop.product;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts(){
        return List.of(
                new Product("Product1","Category1","Description1",new BigDecimal("8.99"), "Currency1"),
                new Product("Product2","Category2","Description2",new BigDecimal("8.99"), "Currency2"),
                new Product("Product3","Category3","Description3",new BigDecimal("8.99"), "Currency3"),
                new Product("Product4","Category4","Description4",new BigDecimal("8.99"), "Currency4")
        );
    }
}
