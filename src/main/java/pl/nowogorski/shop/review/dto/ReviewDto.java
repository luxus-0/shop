package pl.nowogorski.shop.review.dto;

import lombok.Builder;

@Builder
public record ReviewDto(Long id, Long productId, String authorName, String content, boolean moderate) {
}
