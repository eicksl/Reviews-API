package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.CommentDoc;
import com.udacity.course3.reviews.entity.ReviewDoc;
import com.udacity.course3.reviews.repository.ReviewDocRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest(excludeAutoConfiguration = {EmbeddedMongoAutoConfiguration.class})
public class ReviewDocRepositoryTests {

    @Autowired
    private ReviewDocRepository reviewDocRepository;

    @Test
    public void testFindAllReviewDocsByProductId() {
        ReviewDoc reviewDoc = new ReviewDoc(
                1, "Test ReviewDoc", "Testing ReviewDoc creation", true, 1
        );
        CommentDoc commentDoc = new CommentDoc(
                1, "Test CommentDoc", "Testing CommentDoc creation"
        );
        reviewDoc.addComment(commentDoc);
        reviewDocRepository.save(reviewDoc);
        List<ReviewDoc> reviewDocs = reviewDocRepository.findAllByProductId(reviewDoc.getProductId());
        assertNotNull(reviewDocs);
    }

}
