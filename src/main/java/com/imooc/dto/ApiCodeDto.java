package com.imooc.dto;

import com.imooc.constant.ApiCodeEnum;

/**
 * 返回前端的信息类,用于ajax请求返回java对象
 */
public class ApiCodeDto {

    private Integer errno;

    private String msg;

    //用于判断用户是否已经登陆，与session类似
    private String token;

    private String code;

    public ApiCodeDto() {

    }

    public ApiCodeDto(Integer errno, String msg) {
        this.errno = errno;
        this.msg = msg;
    }

    public ApiCodeDto(ApiCodeEnum apiCodeEnum) {
        this.errno = apiCodeEnum.getErrno();
        this.msg = apiCodeEnum.getMsg();
    }

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
