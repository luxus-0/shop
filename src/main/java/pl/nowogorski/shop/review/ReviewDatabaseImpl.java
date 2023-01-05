package pl.nowogorski.shop.review;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReviewDatabaseImpl {

    private final ReviewRepository reviewRepository;

    ReviewDatabaseImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    Review addReview(Review review){
        return reviewRepository.save(review);
    }

    List<Review> readAllByProductIdAndModerated(Long productId, boolean moderated){
        return reviewRepository.findAllByProductIdAndModerated(productId, moderated);
    }
}
