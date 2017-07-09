package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;


//由于与Order差别较大，不继承
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderForBuyDto {

    //前端接口定义的名字，实际就是phone
    private Long username;

    private String token;

    private Integer num;

    private Double price;

    //商户主键
    private Long id;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
