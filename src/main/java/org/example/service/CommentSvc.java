package org.example.service;

import org.example.model.Comment;

import java.util.List;

public interface CommentSvc {

    List<Comment> findAll();

    void save(Comment comment);

    Comment update(Comment comment);

    Comment like(Long id);

    Comment findById(Long id);
}
