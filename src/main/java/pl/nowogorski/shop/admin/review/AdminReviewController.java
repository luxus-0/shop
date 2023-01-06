package pl.nowogorski.shop.admin.review;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
