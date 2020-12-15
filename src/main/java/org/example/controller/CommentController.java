package org.example.controller;

import org.example.model.Comment;
import org.example.service.CommentSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("home")
public class CommentController {

    @Autowired
    private CommentSvc commentSvc;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("comment", new Comment((short) 4, "@anhnbt", "CodeGym MonCity"));
        List<Comment> comments = commentSvc.findAll();
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @PostMapping
    public String save(Comment comment) {
        commentSvc.save(comment);
        return "redirect:/home";
    }

    @PostMapping(value = "like/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Comment> like(@PathVariable(name = "id") Long id) {
        Comment comment = commentSvc.like(id);
        System.out.println(comment.getLikeCount());
        if (comment == null) {
            return new ResponseEntity<>(comment, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

}
