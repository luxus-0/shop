package pl.nowogorski.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c "+
    "left join fetch c.products " +
    "where c.slug = :slug")
    Category findBySlug(String slug);
}
