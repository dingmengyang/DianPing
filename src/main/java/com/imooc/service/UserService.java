package com.imooc.service;

import com.imooc.dto.UserDto;

import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */
public interface UserService {

    //检验账号正确
    boolean validate(UserDto dto);

    List<UserDto> getList();
}
