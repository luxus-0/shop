package pl.nowogorski.shop.admin.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminOrderService {

    private final AdminOrderRepository adminOrderRepository;

    Page<AdminOrder> getOrders(Pageable pageable){
        return adminOrderRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("id").descending())
        );
    }

    AdminOrder getOrder(Long id){
        return adminOrderRepository.findById(id).orElseThrow();
    }
}
