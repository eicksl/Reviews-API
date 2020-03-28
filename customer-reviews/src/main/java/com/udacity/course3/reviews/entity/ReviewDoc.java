package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.List;

@Document("review")
public class ReviewDoc {

    @Id
    private Integer id;
    private String title;
    private String reviewText;
    private boolean recommended;
    private Integer productId;
    private List<CommentDoc> comments = new ArrayList<>();

    public ReviewDoc() {}

    public ReviewDoc(Integer id, String title, String reviewText, boolean recommended, Integer productId) {
        this.id = id;
        this.title = title;
        this.reviewText = reviewText;
        this.recommended = recommended;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<CommentDoc> getComments() {
        return comments;
    }

    public void setComments(List<CommentDoc> comments) {
        this.comments = comments;
    }

    public void addComment(CommentDoc comment){
        this.comments.add(comment);
    }

}
