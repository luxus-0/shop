package pl.nowogorski.shop.admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {
}
