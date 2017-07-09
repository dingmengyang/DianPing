package com.imooc.service;

import com.imooc.bean.Ad;
import com.imooc.dao.AdDao;
import com.imooc.dto.AdDto;

import java.util.List;

/**
 * 广告模块的业务逻辑层
 */
public interface AdService {

    boolean add(AdDto adDto);

    List<AdDto> searchByPage(AdDto adDto);

    boolean deleteOne(Long id);

    AdDto queryById(Long id);

    boolean modify(AdDto adDto);
}
