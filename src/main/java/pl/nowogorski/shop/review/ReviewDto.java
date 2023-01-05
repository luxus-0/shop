package pl.nowogorski.shop.review;

import lombok.Builder;

@Builder
public record ReviewDto(Long id, Long productId, String authorName, String content, boolean moderate) {
}
