package pl.nowogorski.shop.admin.order;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;

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

    @Transactional
    public void updateOrder(Long id, Map<String, String> values) {
        AdminOrder adminOrder = adminOrderRepository.findById(id).orElseThrow();
        patchValues(adminOrder, values);
    }

    private void patchValues(AdminOrder adminOrder, Map<String, String> values) {
        if(values.get("orderStatus") != null){
            adminOrder.setAdminOrderStatus(AdminOrderStatus.valueOf(values.get("orderStatus")));
        }
    }
}
