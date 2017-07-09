package com.imooc.controller.content;

import com.imooc.bean.Page;
import com.imooc.constant.DicTypeConstant;
import com.imooc.constant.PageCodeEnum;
import com.imooc.dto.AdDto;
import com.imooc.dto.BusinessDto;
import com.imooc.service.BusinessService;
import com.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 商户跳转,与AdController不同的是url设计`
 */
@Controller
@RequestMapping("/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @Autowired
    private DicService dicService;
    /*
    商户列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model, Page page){
        BusinessDto businessDto=new BusinessDto();
        businessDto.setPage(page);
        model.addAttribute("list", businessService.searchByPage(businessDto));
        model.addAttribute("searchParam", businessDto);
        return "/content/businessList";
    }

    //商铺删除
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String remove(@PathVariable("id")Long id,Model model){
//        System.out.println(id);
        if(businessService.remove(id)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.DELETE_FAIL);
        }
        return "redirect:/businesses";
    }

    //商铺增加页面跳转
    @RequestMapping(value = "/addPage",method = RequestMethod.GET)
    public String addInit(Model model){
        model.addAttribute("cityList", dicService.getListByType(DicTypeConstant.CITY));
        model.addAttribute("categoryList", dicService.getListByType(DicTypeConstant.CATEGORY));
        return "/content/businessAdd";
    }

    //商铺增加
    @RequestMapping(method = RequestMethod.POST)
    public String add(BusinessDto dto,RedirectAttributes attr){

        if(businessService.add(dto)) {
            attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_SUCCESS);
            return "redirect:/businesses";
        } else {
            attr.addAttribute(PageCodeEnum.KEY, PageCodeEnum.ADD_FAIL);
            return "redirect:/businesses/addPage";
        }
    }

    //商铺修改页面跳转
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String modifyInit(Model model,@PathVariable("id")Long id){
        model.addAttribute("modifyObj",businessService.queryById(id));
        model.addAttribute("cityList",dicService.getListByType(DicTypeConstant.CITY));
        model.addAttribute("categoryList",dicService.getListByType(DicTypeConstant.CATEGORY));
        return "/content/businessModify";
    }

    /*商铺增加
    由于使用Restful接口，不通过input id获取参数而是拼接在url中，
       在修改操作中，form的enctype="multipart/form-data"，在http请求提交到servlet时会以流的形式读取请求，后面才轮到拦截器，
    但HiddenHttpMethodFilter拦截器只能以参数形式获取name="_method" value="PUT"的input，所以不能起到将post转换为put请求的
    作用，所以需要再设置一个拦截器，它会提前将form解析好后传给HiddenHttpMethodFilter，从而拿到正确参数
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String modify(@PathVariable("id")Long id,BusinessDto dto,Model model){
//        System.out.println(id);
        if(businessService.modify(id,dto)){
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_SUCCESS);
        }else {
            model.addAttribute(PageCodeEnum.KEY,PageCodeEnum.MODIFY_FAIL);
        }
        return "/content/businessModify";
    }


}
