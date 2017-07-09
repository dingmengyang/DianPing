package com.imooc.service;


public interface MemberService {

    boolean exists(Long phone);

    boolean saveCode(Long phone,String code);

    boolean sendCode(Long phone,String content);

    String getCode(Long phone);

    boolean saveToken(Long phone, String token);

    Long getPhoneByToken(String token);

    Long getIdByPhone(Long phone);

    Long getPhoneById(Long id);

    Long getPhoneFromComment(Long commentId);

    Long getPhoneFromOrders(Long ordersId);

}
