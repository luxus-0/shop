package pl.nowogorski.shop.review;

import org.springframework.stereotype.Service;

@Service
class ReviewDatabaseImpl {

    private final ReviewRepository reviewRepository;

    ReviewDatabaseImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
