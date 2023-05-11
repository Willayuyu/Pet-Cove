package com.example.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springboot.entity.OrderDetails;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "orderDetailsMapper")
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {
}
