package com.imooc.dao;

import com.imooc.bean.Ad;
import com.imooc.bean.Business;
import com.imooc.dto.BusinessDto;

import java.util.List;


public interface BusinessDao {

    Business queryById(Long id);

    List<Business> queryByPage(Business condition);

    int insert(Business business);

    int deleteOne(Long id);

    int updateOne(Business business);

    List<Business> searchByPage(Business condition);

    int updateNumber(String date);

    int updateStar(Integer star);
}
