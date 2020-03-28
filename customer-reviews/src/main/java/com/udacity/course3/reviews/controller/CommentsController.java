package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.CommentDoc;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDoc;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewDocRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with {@link Comment} entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final ReviewDocRepository reviewDocRepository;

    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository, ReviewDocRepository reviewDocRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.reviewDocRepository = reviewDocRepository;
    }

    /**
     * Creates {@link Comment} for a {@link Review}.
     *
     * @param reviewId The id of the review.
     * @param comment The comment to create.
     * @return the created comment or NOT_FOUND if review is not found.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<CommentDoc> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Optional<ReviewDoc> optionalReviewDoc = reviewDocRepository.findById(reviewId);
        //System.out.println(optionalReview);
        //System.out.println(optionalReviewDoc);
        if (!optionalReview.isPresent() || !optionalReviewDoc.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comment.setReview(optionalReview.get());
        commentRepository.save(comment);  // MySQL

        ReviewDoc reviewDoc = optionalReviewDoc.get();
        CommentDoc commentDoc = new CommentDoc(comment.getId(), comment.getTitle(), comment.getCommentText());
        reviewDoc.addComment(commentDoc);
        reviewDocRepository.save(reviewDoc);  // MongoDB

        return ResponseEntity.ok(commentDoc);
    }

    /**
     * List {@link Comment}s for a {@link Review}.
     *
     * @param reviewId The id of the review.
     * @return The list of comments.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDoc>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<ReviewDoc> optionalReviewDoc = reviewDocRepository.findById(reviewId);
        if (!optionalReviewDoc.isPresent()) {
            System.out.println("test");
            return ResponseEntity.notFound().build();
        }
        List<CommentDoc> comments = optionalReviewDoc.get().getComments();
        return ResponseEntity.ok(comments);
    }
}
