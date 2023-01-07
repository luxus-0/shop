package pl.nowogorski.shop.customer;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class CustomerRepositoryImpl {

    private final CustomerRepository customerRepository;

    CustomerRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    List<Customer> readCustomers(){
        return customerRepository.findAll();
    }
    Customer readCustomer(Long id){
        return customerRepository.findById(id).orElseThrow();
    }

    Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    void removeCustomer(Long id){
        customerRepository.deleteById(id);
    }
}
