package com.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.springboot.entity.Comment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component(value = "commentService")
public interface CommentService extends IService<Comment> {

    public void addComment(String content, int orderItemId, int buyerId);

    public List<Comment> getProductComments(int productId);
}
