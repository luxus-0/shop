package pl.nowogorski.shop.admin.order;

import org.springframework.data.jpa.repository.JpaRepository;

interface AdminOrderRepository extends JpaRepository<AdminOrder, Long> {
}

