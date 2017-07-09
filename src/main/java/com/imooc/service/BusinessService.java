package com.imooc.service;

import com.imooc.bean.Page;
import com.imooc.dto.BusinessDto;
import com.imooc.dto.BusinessListDto;
import com.imooc.dto.CommentListDto;

import java.util.List;


public interface BusinessService {

    BusinessDto queryById(Long id);

    List<BusinessDto> searchByPage(BusinessDto businessDto);

    boolean add(BusinessDto dto);

    boolean remove(Long id);

    boolean modify(Long id, BusinessDto dto);

    BusinessListDto searchWithParams(BusinessDto dto);

    CommentListDto searchWithParams(int businessId, Page page);

    void updateNumber(String date);

    void updateStar();
}
