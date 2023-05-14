package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springboot.entity.Comment;
import com.example.springboot.entity.OrderItem;
import com.example.springboot.mapper.CommentMapper;
import com.example.springboot.service.CommentService;
import com.example.springboot.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CommentService commentService;

    @Override
    public void addComment(String content, int orderItemId, int buyerId){
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBuyerId(buyerId);
        comment.setOrderItemId(orderItemId);

        OrderItem orderItem = orderItemService.getById(orderItemId);
        comment.setProductId(orderItem.getProductId());

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String createdDate = dateFormat.format(date);
        comment.setCreatedDate(createdDate);
        commentService.save(comment);
    }

    @Override
    public List<Comment> getProductComments(int productId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        return commentService.list(queryWrapper);
    }

}
