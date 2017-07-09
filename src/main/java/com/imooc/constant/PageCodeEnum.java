package com.imooc.constant;

/**
 * service执行操作返回
 */
public enum PageCodeEnum {
    //10,表示添加操作，00表示成功，其他表示失败
    ADD_SUCCESS(1000,"新增成功"),
    ADD_FAIL(1001,"新增失败"),
    UPDATE_SUCCESS(1100,"更新成功"),
    UPDATE_FAIL(1101,"更新失败"),
    DELETE_SUCCESS(1201,"删除成功"),
    DELETE_FAIL(1201,"删除失败"),
    MODIFY_SUCCESS(1301,"修改成功"),
    MODIFY_FAIL(1301,"修改失败"),
    LOGIN_FAIL(1401,"登陆失败，账号或者密码不对"),
    LOGIN_TIMEOUT(1402,"登陆超时");

    public static final String KEY="pageCode";

    private Integer code;
    private String msg;

    PageCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
