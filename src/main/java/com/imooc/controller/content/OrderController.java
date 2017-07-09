package com.imooc.controller.content;

import com.imooc.bean.Page;
import com.imooc.dao.OrderDao;
import com.imooc.dto.CommentDto;
import com.imooc.dto.OrderDto;
import com.imooc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台订单管理页面
 */
@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @RequestMapping
    public String init(Model model, Page page){
        OrderDto orderDto=new OrderDto();
        orderDto.setPage(page);
        model.addAttribute("list", orderService.searchByPage(orderDto));
        model.addAttribute("searchParam", orderDto);
        return "/content/orderList";
    }
}
