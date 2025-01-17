package hotel.controller;

import hotel.entity.AppUser;
import hotel.entity.Property;
import hotel.entity.Review;
import hotel.repository.PropertyRepository;
import hotel.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private PropertyRepository propertyRepository;
    private ReviewRepository reviewRepository;

    public ReviewController(PropertyRepository propertyRepository, ReviewRepository reviewRepository) {
        this.propertyRepository = propertyRepository;
        this.reviewRepository = reviewRepository;
    }
    @PostMapping
    public ResponseEntity<?>write(
            @RequestBody Review review,
            @RequestParam long propertyId,
            @AuthenticationPrincipal AppUser user
            ){
        Property property = propertyRepository.findById(propertyId).get();
        review.setProperty(property);
        review.setAppUser(user);
        Review savedReview = reviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.OK);
    }
}
