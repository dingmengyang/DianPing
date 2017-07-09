package com.imooc.controller.api;

import com.imooc.bean.Page;
import com.imooc.dto.*;
import com.imooc.constant.ApiCodeEnum;
import com.imooc.service.*;
import com.imooc.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 处理前端webapp的请求
 */
//所有方法以json数据格式输出
@RestController
//处理以/api开头的请求
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AdService adService;

    @Resource
    private BusinessService businessService;

    @Resource
    private MemberService memberService;

    @Resource
    private OrderService orderService;

    @Resource
    private CommentService commentService;


    @Value("${business.searchNumber}")
    private int businessSearchNumber;

    @Value("${ad.number}")
    private int adNumber;

    //首页广告
    @RequestMapping(value = "/homead", method = RequestMethod.GET)
    public List<AdDto> homead() {
        AdDto adDto=new AdDto();
        adDto.getPage().setPageNumber(adNumber);
        return adService.searchByPage(adDto);
//        字符串转换为json
//        ObjectMapper mapper = new ObjectMapper();
//        String s = "[{\"title\":\"暑假0折\",\"imgFileName\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796." +
//                "png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"暑假0折\",\"imgFileName\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796." +
//                "png\",\"link\":\"http://www.imooc.com/wap/index\"},{\"title\":\"暑假0折\",\"imgFileName\":\"http://images2015.cnblogs.com/blog/138012/201610/138012-20161016191639092-2000037796." +
//                "png\",\"link\":\"http://www.imooc.com/wap/index\"}]";
//        return mapper.readValue(s, new TypeReference<List<Ad>>() {
//        });
    }

    //首页 —— 推荐列表（猜你喜欢）
    @RequestMapping(value = "/homelist/{city}/{page.currentPage}", method = RequestMethod.GET)
    public BusinessListDto homelist(BusinessDto dto) {
        dto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchWithParams(dto);
    }

    // 搜索结果页 - 搜索结果 - 三个参数
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}/{keyword}",method = RequestMethod.GET)
    public BusinessListDto searchWithKeyword(BusinessDto dto){
        dto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchWithParams(dto);
    }

    // 搜索结果页 - 搜索结果 - 两个参数
    @RequestMapping(value = "/search/{page.currentPage}/{city}/{category}",method = RequestMethod.GET)
    public BusinessListDto search(BusinessDto dto){
        dto.getPage().setPageNumber(businessSearchNumber);
        return businessService.searchWithParams(dto);
    }

    //提交评论
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    public ApiCodeDto submitComment(CommentForSubmitDto commentDto){
        Long phone=memberService.getPhoneByToken(commentDto.getToken());
        ApiCodeDto codeDto;
        //如果用户登录了
        if (!CommonUtil.isEmpty(""+phone)&&phone.equals(commentDto.getUsername())){
            //防止用户篡改请求头，判断commentDto的订单id对应的会员id是否和commentDto的会员id是否一样
            Long queryMemberId=orderService.getMemberId(commentDto.getId());
            Long username=memberService.getPhoneById(queryMemberId);
            if (username!=null&&username.equals(commentDto.getUsername())){
                //进行评论
                if (commentService.commitComment(commentDto)){
                    codeDto=new ApiCodeDto(ApiCodeEnum.SUCCESS);
                    //TODO,商铺星级更新定时任务
                }else {
                    codeDto=new ApiCodeDto(ApiCodeEnum.COMMENT_FAIL);
                }
            }else {
                codeDto=new ApiCodeDto(ApiCodeEnum.NO_AUTH);
            }
        }else {
            codeDto=new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return codeDto;
    }

    /**
     * 详情页 - 商户信息
     */
    @RequestMapping(value = "/detail/info/{id}", method = RequestMethod.GET)
    public BusinessDto detail(@PathVariable("id") Long id) {
        return businessService.queryById(id);
    }

    //发送验证码
    @RequestMapping(value = "/sms", method = RequestMethod.POST)
    public ApiCodeDto sms(@RequestParam("username")Long phone){
        ApiCodeDto dto;
        if (CommonUtil.isEmpty(""+phone)){
            return new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        if (memberService.exists(phone)){
            //获取验证码
            String code=String.valueOf(CommonUtil.random(6));
            //保存手机号与验证码
            if (memberService.saveCode(phone,code)){
                //验证码发送成功
                if (memberService.sendCode(phone,code)){
                    dto = new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(), code);
                } else {
                    dto = new ApiCodeDto(ApiCodeEnum.SEND_FAIL);
                }
            } else {
                dto = new ApiCodeDto(ApiCodeEnum.REPEAT_REQUEST);
            }
        } else {
            dto = new ApiCodeDto(ApiCodeEnum.USER_NOT_EXISTS);
        }
        return dto;
    }

    //用户登录接口
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiCodeDto login(@RequestParam("username") Long phone, @RequestParam("code") String code) {
        ApiCodeDto dto;
        String tempCode=memberService.getCode(phone);
        if (tempCode!=null){
            //验证码正确
            if (tempCode.equals(code)){
                String token=CommonUtil.getUUID();
                memberService.saveToken(phone,token);
                dto=new ApiCodeDto(ApiCodeEnum.SUCCESS.getErrno(),token);
                dto.setToken(token);
            }else {
                dto=new ApiCodeDto(ApiCodeEnum.CODE_ERROR);
            }
        }else {
            dto = new ApiCodeDto(ApiCodeEnum.CODE_INVALID);
        }
        return dto;
    }

    //购买接口
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ApiCodeDto order(OrderForBuyDto dto) {
        Long phone=memberService.getPhoneByToken(dto.getToken());
        ApiCodeDto codeDto;
        //如果电话号码正确
        if (!CommonUtil.isEmpty(""+phone)&&phone.equals(dto.getUsername())){
            Long memberId=memberService.getIdByPhone(phone);
            //保存订单
            OrderDto orderDto=new OrderDto();
            orderDto.setMemberId(memberId);
            orderDto.setNum(dto.getNum());
            orderDto.setBusinessId(dto.getId());
            orderDto.setMemberId(memberId);
            if (orderService.addOrder(orderDto)){
                codeDto=new ApiCodeDto(ApiCodeEnum.SUCCESS);
            }else {
                codeDto=new ApiCodeDto(ApiCodeEnum.BUY_FAIL);
            }
            //TODO，商铺已售数量更新定时任务
        }else {
            codeDto=new ApiCodeDto(ApiCodeEnum.NOT_LOGGED);
        }
        return codeDto;
    }

    // 详情页 - 用户评论
    @RequestMapping(value = "/detail/comment/{currentPage}/{id}", method = RequestMethod.GET)
    public CommentListDto comment(@PathVariable("id")int businessId, Page page) {
        return businessService.searchWithParams(businessId,page);
    }

}
