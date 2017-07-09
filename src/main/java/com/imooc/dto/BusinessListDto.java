package com.imooc.dto;



import java.util.List;

/**
 * 商户列表json实体类
 */
public class BusinessListDto {

    private boolean hasMore;

    private List<BusinessDto> data;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public List<BusinessDto> getData() {
        return data;
    }

    public void setData(List<BusinessDto> data) {
        this.data = data;
    }
}
