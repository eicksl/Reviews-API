package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDoc;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewDocRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with {@link Review} entity.
 */
@RestController
public class ReviewsController {

    private ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final ReviewDocRepository reviewDocRepository;

    public ReviewsController(ReviewRepository reviewRepository, ProductRepository productRepository, ReviewDocRepository reviewDocRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.reviewDocRepository = reviewDocRepository;
    }

    /**
     * Creates a {@link Review} for a {@link Product}.
     *
     * @param productId The id of the product.
     * @param review The {@link Review} to create.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<ReviewDoc> createReviewForProduct(@PathVariable("productId") Integer productId, @RequestBody Review review) {
        Optional<Product> optional = productRepository.findById(productId);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        review.setProduct(optional.get());
        reviewRepository.save(review);  // MySQL
        ReviewDoc reviewDoc = new ReviewDoc(
                review.getId(), review.getTitle(), review.getReviewText(), review.getRecommended(), productId
        );
        reviewDocRepository.save(reviewDoc);  // MongoDB
        return ResponseEntity.ok(reviewDoc);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDoc>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        List<ReviewDoc> reviewDocs = reviewDocRepository.findAllByProductId(productId);
        return ResponseEntity.ok(reviewDocs);
    }
}
