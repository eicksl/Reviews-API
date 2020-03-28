package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA Repository for {@link Review} entity.
 */
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    /**
     * Finds all {@link Review} for a product.
     *
     * @param productId The {@link Product} ID.
     * @return The list of reviews for the product.
     */
    List<Review> findAllByProductId(Integer productId);
}
