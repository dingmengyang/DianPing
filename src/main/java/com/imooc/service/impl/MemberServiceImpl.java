package com.imooc.service.impl;

import com.imooc.bean.Member;
import com.imooc.cache.CodeCache;
import com.imooc.cache.TokenCache;
import com.imooc.dao.MemberDao;
import com.imooc.service.MemberService;
import com.imooc.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//TODO，简单示例，真实环境应使用第三方工具
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    private final static Logger logger = LoggerFactory
            .getLogger(MemberService.class);

    //判断手机号是否已注册
    public boolean exists(Long phone) {
        Member member=new Member();
        member.setPhone(phone);
        List<Member> list=memberDao.select(member);
        return list!=null&&list.size()==1;
    }


    public boolean saveCode(Long phone, String code) {
        CodeCache codeCache=CodeCache.getInstance();
        return codeCache.save(phone, Md5Util.getMD5(code));
    }

    //TODO，短信发送功能暂时欠缺
    public boolean sendCode(Long phone,String content){
        logger.error(phone+"|"+content);
        return true;
    }

    public String getCode(Long phone) {
        CodeCache codeCache=CodeCache.getInstance();
        return codeCache.getCode(phone);
    }

    public boolean saveToken(Long phone, String token) {
        TokenCache tokenCache=TokenCache.getInstance();
        return tokenCache.save(phone, token);
    }

    public Long getPhoneByToken(String token) {
        TokenCache tokenCache=TokenCache.getInstance();
        return tokenCache.getPhone(token);
    }

    public Long getIdByPhone(Long phone) {
        Member member=new Member();
        member.setPhone(phone);
        return memberDao.select(member).get(0).getId();
    }

    public Long getPhoneById(Long id) {
        return memberDao.getPhoneById(id);
    }

    public Long getPhoneFromComment(Long commentId) {
        return memberDao.getPhoneFromComment(commentId);
    }

    public Long getPhoneFromOrders(Long ordersId) {
        return memberDao.getPhoneFromOrders(ordersId);
    }
}
