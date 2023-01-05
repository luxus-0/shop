package pl.nowogorski.shop.admin.review;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reviews")
class AdminReviewController {

    private final AdminReviewDatabaseImpl adminReviewDatabaseImpl;

    AdminReviewController(AdminReviewDatabaseImpl adminReviewDatabaseImpl) {
        this.adminReviewDatabaseImpl = adminReviewDatabaseImpl;
    }

    @GetMapping
    List<AdminReview> getReviews() {
        return adminReviewDatabaseImpl.readReviews();
    }

    @PutMapping("/{id}/moderate")
    void moderate(@PathVariable Long id) {
        adminReviewDatabaseImpl.moderateReview(id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        adminReviewDatabaseImpl.removeReview(id);
    }
}
