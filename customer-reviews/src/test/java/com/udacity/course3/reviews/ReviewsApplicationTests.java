package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewsApplicationTests {

	private Product product;
	private Review review;
	private Comment comment;

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private CommentRepository commentRepository;

	@BeforeTransaction
	public void setup() {
		product = new Product(1);
		product.setName("Test Product");
		product.setDescription("Testing product creation");
		productRepository.save(product);

		review = new Review(1);
		review.setProduct(product);
		review.setRecommended(true);
		review.setTitle("Test Review");
		review.setReviewText("Testing review creation");
		reviewRepository.save(review);

		comment = new Comment(1);
		comment.setReview(review);
		comment.setTitle("Test Comment");
		comment.setCommentText("Testing comment creation");
		commentRepository.save(comment);
	}

	@Test
	public void contextLoads() {}

	@Test
	public void testFindAllReviewsByProduct() {
		List<Review> reviews = reviewRepository.findAllByProduct(product);
		assertNotNull(reviews);
	}

	@Test
	public void testFindAllCommentsByReview() {
		List<Comment> comments = commentRepository.findAllByReview(review);
		assertNotNull(comments);
	}

}
