package com.udacity.course3.reviews.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document("comment")
public class CommentDoc {

    @Id
    private Integer id;
    private String title;
    private String commentText;

    public CommentDoc() {}

    public CommentDoc(Integer id, String title, String commentText) {
        this.id = id;
        this.title = title;
        this.commentText = commentText;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}
