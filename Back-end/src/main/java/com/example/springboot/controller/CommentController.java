package com.example.springboot.controller;

import com.example.springboot.entity.Comment;
import com.example.springboot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;


    @PostMapping("/makeComments")
    public int makeComments(@RequestParam String content,
                             @RequestParam int orderItemId,
                             @RequestParam int buyerId){
        commentService.addComment(content, orderItemId, buyerId);
        return 1;
    }

    @PostMapping("/getProductComments")
    public List<Comment> getProductComments(@RequestParam int productId){
        return commentService.getProductComments(productId);
    }

}
