package com.imooc.dao;



/**
 * Created by Administrator on 2017/7/8.
 */
public interface SyncDao {

    int insertData(String date);

    String queryLast();
}
