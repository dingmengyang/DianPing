package com.imooc.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 负责index.jsp跳转的controller
 */

@Controller
@RequestMapping("/index")
public class IndexController {

    //默认进入该方法
    @RequestMapping
    public String init(){
        return "/system/index";
    }
}
