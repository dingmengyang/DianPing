package com.imooc.service;

import com.imooc.bean.Dic;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface DicService {

    public List<Dic> getListByType(String type);
}
