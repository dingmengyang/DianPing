package com.imooc.service;

import com.imooc.dto.OrderDto;

import java.util.List;

/**
 * Created by Administrator on 2017/7/7.
 */
public interface OrderService {

    boolean addOrder(OrderDto dto);

    Long getMemberId(Long id);

    List<OrderDto> searchByPage(OrderDto orderDto);
}
