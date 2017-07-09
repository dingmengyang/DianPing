package com.imooc.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 订单类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order extends BaseBean {

    private Long id;

    private Long memberId;

    private Long businessId;

    //付款人数，方便计算平均消费金额
    private Integer num;

    private Double price;

    private Integer commentState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

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

    public Integer getCommentState() {
        return commentState;
    }

    public void setCommentState(Integer commentState) {
        this.commentState = commentState;
    }
}
