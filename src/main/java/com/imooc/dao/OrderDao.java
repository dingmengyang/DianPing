package com.imooc.dao;


import com.imooc.bean.Order;

import java.util.List;

public interface OrderDao {

    int add(Order order);

    Long getMemberId(Long id);

    int updateState(Long id);

    List<Order> queryByPage(Order condition);
}
