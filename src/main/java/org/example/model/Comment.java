package org.example.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short rating;
    private String author;
    private String message;
    private Integer likeCount;

    public Comment() {
    }

    public Comment(Short rating, String author, String message) {
        this.rating = rating;
        this.author = author;
        this.message = message;
    }

    public Comment(Long id, Short rating, String author, String message, Integer likeCount) {
        this.id = id;
        this.rating = rating;
        this.author = author;
        this.message = message;
        this.likeCount = likeCount;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", rating=" + rating +
                ", author='" + author + '\'' +
                ", message='" + message + '\'' +
                ", likeCount='" + likeCount + '\'' +
                '}';
    }
}
