package com.imooc.controller.system;

import com.imooc.bean.Page;
import com.imooc.constant.PageCodeEnum;
import com.imooc.constant.SessionKeyConstant;
import com.imooc.dto.OrderDto;
import com.imooc.dto.UserDto;
import com.imooc.service.OrderService;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * 后台订单管理页面
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private UserService userService;

    //登陆页面
    @RequestMapping
    public String index() {
        return "/system/login";
    }

    //处理拦截器拦截后未通过的跳转
    @RequestMapping("/sessionTimeout")
    public String sessionTimeout(RedirectAttributes attributes) {
        attributes.addFlashAttribute("pageCode", PageCodeEnum.LOGIN_TIMEOUT);
        return "redirect:/login";
    }

    @RequestMapping("/validate")
    public String validate(UserDto dto, RedirectAttributes attributes, HttpSession session){
        if (userService.validate(dto)) {
            session.setAttribute(SessionKeyConstant.USER_INFO,dto);
            return "redirect:/index";
        }
        attributes.addFlashAttribute("pageCode", PageCodeEnum.LOGIN_FAIL);
        return "redirect:/login";
    }
}
