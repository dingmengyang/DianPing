package com.imooc.dto;

/**
 * 前端web评价功能传过来的实体类
 */
public class CommentForSubmitDto {

    //评论内容
    private String content;
    //订单id
    private Long id;
    //星级
    private int star;

    private String token;

    private Long username;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }
}
