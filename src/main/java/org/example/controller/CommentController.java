package org.example.controller;

import org.example.model.Comment;
import org.example.service.CommentSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        modelAndView.addObject("comment", new Comment());
        List<Comment> comments = commentSvc.findAll();
        modelAndView.addObject("comments", comments);
        return modelAndView;
    }

    @PostMapping
    public String save(Comment comment) {
        commentSvc.save(comment);
        return "redirect:/home";
    }

    @GetMapping("like/{id}")
    public String like(@PathVariable(name = "id") Long id) {
        commentSvc.like(id);
        return "redirect:/home";
    }

}
