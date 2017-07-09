package com.imooc.controller.system;

import com.imooc.dto.UserDto;
import com.imooc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 负责index.jsp跳转的controller
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    //默认进入该方法
    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> init(){
        return userService.getList();
    }
}
