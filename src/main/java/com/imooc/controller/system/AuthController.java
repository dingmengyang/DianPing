package com.imooc.controller.system;


import com.imooc.constant.DicTypeConstant;
import com.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private DicService dicService;

	@RequestMapping
	public String index(Model model) {
		model.addAttribute("httpMethodList", dicService.getListByType(DicTypeConstant.HTTP_METHOD));
		return "/system/auth";
	}
}