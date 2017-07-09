package com.imooc.controller.content;

import com.imooc.bean.Page;
import com.imooc.dto.CommentDto;
import com.imooc.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台评论管理页面
 */
@Controller
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @RequestMapping
    public String init(Model model, Page page){
        CommentDto commentDto=new CommentDto();
        commentDto.setPage(page);
        model.addAttribute("list", commentService.searchByPage(commentDto));
        model.addAttribute("searchParam", commentDto);
        return "/content/commentList";
    }
}
