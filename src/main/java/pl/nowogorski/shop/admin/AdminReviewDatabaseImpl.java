package pl.nowogorski.shop.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminReviewDatabaseImpl {

    private final AdminReviewRepository adminReviewRepository;

    public AdminReviewDatabaseImpl(AdminReviewRepository adminReviewRepository) {
        this.adminReviewRepository = adminReviewRepository;
    }

    public List<AdminReview> readReviews() {
        return adminReviewRepository.findAll();
    }
    @Transactional
    public void moderateReview(Long id) {
        adminReviewRepository.moderate(id);
    }

    public void removeReview(Long id) {
        adminReviewRepository.findById(id);
    }
}
