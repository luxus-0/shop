package pl.nowogorski.shop.admin.review;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "review")
class AdminReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String authorName;
    private String content;
    private boolean moderated;
}
