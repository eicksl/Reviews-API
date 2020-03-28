package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.ReviewDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewDocRepository extends MongoRepository<ReviewDoc, Integer> {

    List<ReviewDoc> findAllByProductId(Integer productId);

}
