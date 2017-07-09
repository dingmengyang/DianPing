package com.imooc.dao;

import com.imooc.bean.Member;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public interface MemberDao {

    List<Member> select(Member member);

    Long getPhoneById(Long id);

    Long getPhoneFromComment(Long commentId);

    Long getPhoneFromOrders(Long ordersId);
}
