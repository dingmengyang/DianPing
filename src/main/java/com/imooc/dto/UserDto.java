package com.imooc.dto;

import com.imooc.bean.User;

/**
 * Created by Administrator on 2017/7/9.
 */
public class UserDto extends User {

    //zTree中的节点值
    private Integer pId;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
