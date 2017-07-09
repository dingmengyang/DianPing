package com.imooc.service.impl;

import com.imooc.bean.Comment;
import com.imooc.bean.Order;
import com.imooc.constant.CommentStateConstant;
import com.imooc.dao.MemberDao;
import com.imooc.dao.OrderDao;
import com.imooc.dto.CommentDto;
import com.imooc.dto.OrderDto;
import com.imooc.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private MemberDao memberDao;

    public boolean addOrder(OrderDto dto) {
        Order order=new Order();
        BeanUtils.copyProperties(dto,order);
        order.setCommentState(CommentStateConstant.NOT_COMMENT);
        int count=orderDao.add(order);
        return count>0;
    }

    public Long getMemberId(Long id) {
        return orderDao.getMemberId(id);
    }

    public List<OrderDto> searchByPage(OrderDto orderDto) {
        List<OrderDto> result = new ArrayList<OrderDto>();
        Order condition = new Order();
        BeanUtils.copyProperties(orderDto, condition);
        List<Order> list = orderDao.queryByPage(condition);
        for (Order order : list) {
            OrderDto dto = new OrderDto();
            BeanUtils.copyProperties(order, dto);
            dto.setUsername(""+memberDao.getPhoneFromOrders(order.getId()));
            result.add(dto);
        }
        return result;
    }
}
