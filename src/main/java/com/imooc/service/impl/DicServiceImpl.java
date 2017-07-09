package com.imooc.service.impl;

import com.imooc.bean.Dic;
import com.imooc.dao.BusinessDao;
import com.imooc.dao.DicDao;
import com.imooc.service.DicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
@Service
public class DicServiceImpl implements DicService{

    @Autowired
    private DicDao dicDao;

    public List<Dic> getListByType(String type) {
        Dic dic=new Dic();
        dic.setType(type);
        return dicDao.select(dic);
    }
}
