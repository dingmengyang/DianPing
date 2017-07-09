package com.imooc.service.impl;

import com.imooc.bean.User;
import com.imooc.dao.UserDao;
import com.imooc.dto.UserDto;
import com.imooc.service.UserService;
import com.imooc.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/9.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public boolean validate(UserDto dto) {
        if (dto != null && !CommonUtil.isEmpty(dto.getName()) && !CommonUtil.isEmpty(dto.getPassword())){
            User user=new User();
            BeanUtils.copyProperties(dto,user);
            List<User> list=userDao.select(user);
            if (list.size()==1){
                BeanUtils.copyProperties(list.get(0),dto);
                return true;
            }
        }
        return false;
    }

    public List<UserDto> getList() {
        List<User> list=userDao.selectAll();
        List<UserDto> result=new ArrayList<UserDto>();
        for (User user : list) {
            UserDto dto=new UserDto();
            BeanUtils.copyProperties(user,dto);
            dto.setpId(0);
            result.add(dto);
        }
        return result;
    }
}
