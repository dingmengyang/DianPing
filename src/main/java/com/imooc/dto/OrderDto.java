package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.imooc.bean.Order;



@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends Order{

    //订单图片
    private String img;
    private String title;
    private Integer count;
    private String username;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
