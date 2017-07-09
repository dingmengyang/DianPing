package com.imooc.service.impl;

import com.imooc.dao.SyncDao;
import com.imooc.service.SyncService;
import com.imooc.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private SyncDao dao;

    public boolean addRecord(String date) {
        int count=dao.insertData(date);
        return count>0;
    }

    public String getLastDate() {
        return dao.queryLast();
    }
}
