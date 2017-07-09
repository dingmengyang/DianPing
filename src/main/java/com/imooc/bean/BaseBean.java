package com.imooc.bean;

/**
 * 与分页有关的bean的父类
 */
public class BaseBean {

    private Page page;


    public Page getPage() {
        if (page==null){
            page=new Page();
        }
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
