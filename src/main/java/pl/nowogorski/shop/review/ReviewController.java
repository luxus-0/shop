package pl.nowogorski.shop.review;

import jakarta.validation.Valid;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.nowogorski.shop.review.dto.ReviewDto;

@RestController
class ReviewController {
    private final ReviewDatabaseImpl reviewDatabaseImpl;

    public ReviewController(ReviewDatabaseImpl reviewDatabaseImpl) {
        this.reviewDatabaseImpl = reviewDatabaseImpl;
    }
    @PostMapping("/review")
    Review createReview(@RequestBody @Valid ReviewDto reviewDto){
        return reviewDatabaseImpl.addReview(Review.builder()
                        .authorName(cleanContent(reviewDto.authorName()))
                        .productId(reviewDto.productId())
                        .content(cleanContent(reviewDto.content()))
                        .build());
    }

    private String cleanContent(String text) {
        return Jsoup.clean(text, Safelist.none());
    }
}
