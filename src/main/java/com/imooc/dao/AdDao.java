package com.imooc.dao;

import com.imooc.bean.Ad;
import com.imooc.dto.AdDto;

import java.util.List;

/**
 * 广告类与数据库的交互
 */
public interface AdDao {
    //插入数据
    int insert(Ad ad);

    List<Ad> queryByPage(Ad ad);

    int deleteOne(Long id);

    Ad queryById(Long id);

    int updateOne(Ad ad);
}
