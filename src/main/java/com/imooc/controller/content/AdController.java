package com.imooc.controller.content;

import com.imooc.bean.Page;
import com.imooc.constant.PageCodeEnum;
import com.imooc.dto.AdDto;
import com.imooc.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 广告管理页面跳转
 */
//@Controller可以让表单Input的id映射为方法参数中自定义类的同名成员变量并赋值
@Controller
@RequestMapping("/ad")
public class AdController {

    @Autowired
    private AdService adService;

    @RequestMapping
    public String init(Model model, Page page){
        AdDto adDto=new AdDto();
        adDto.setPage(page);
        model.addAttribute("list", adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    @RequestMapping("/addInit")
    public String addInit(){
        return "/content/adAdd";
    }

    @RequestMapping("/add")
    public String add(AdDto adDto, Model model){
        if(adService.add(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.ADD_FAIL);
        }
        return "/content/adAdd";
    }

    @RequestMapping("/search")
    public String search(Model model, Page page){
        AdDto adDto=new AdDto();
        adDto.setPage(page);
        model.addAttribute("list", adService.searchByPage(adDto));
        model.addAttribute("searchParam", adDto);
        return "/content/adList";
    }

    @RequestMapping("/remove")
    //@RequestParam("id")必须遵守，不加的话若想成功需要开启编译add variable attributes to generated class file,一般是debug默认开启
    public String remove(@RequestParam("id")Long id){
        adService.deleteOne(id);
        return "forward:/ad";
    }

    @RequestMapping("/modifyInit")
    public String modifyInit(Model model,@RequestParam("id")Long id){
        model.addAttribute("modifyObj",adService.queryById(id));
        return "/content/adModify";
    }

    @RequestMapping("/modify")
    public String modify(Model model,AdDto adDto){
        if(adService.modify(adDto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.UPDATE_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.UPDATE_FAIL);
        }
        return "/content/adList";
    }


}
