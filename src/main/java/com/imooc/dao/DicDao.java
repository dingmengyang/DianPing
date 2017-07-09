package com.imooc.dao;

import com.imooc.bean.Dic;

import java.util.List;

/**
 * Created by Administrator on 2017/7/4.
 */
public interface DicDao {

    List<Dic> select(Dic dic);

}
