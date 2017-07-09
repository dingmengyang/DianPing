package com.imooc.dto;


import com.imooc.bean.Comment;

public class CommentDto extends Comment {

    //评价列表中隐藏号码后的用户名
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
