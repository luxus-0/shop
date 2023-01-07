package pl.nowogorski.shop.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private CustomerRepositoryImpl customerRepositoryImpl;

    public CustomerController(CustomerRepositoryImpl customerRepositoryImpl) {
        this.customerRepositoryImpl = customerRepositoryImpl;
    }

    @GetMapping
    List<Customer> readCustomers(){
        return customerRepositoryImpl.readCustomers();
    }

    @GetMapping("/{id}")
    Customer readCustomer(@PathVariable Long id){
        return customerRepositoryImpl.readCustomer(id);
    }

    @PostMapping
    Customer addCustomer(@RequestBody Customer customer){
        return customerRepositoryImpl.addCustomer(customer);
    }

    @DeleteMapping("/{id}")
    void deleteCustomer(@PathVariable Long id){
        customerRepositoryImpl.removeCustomer(id);
    }
}
