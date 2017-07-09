package com.imooc.task;

import com.imooc.dao.SyncDao;
import com.imooc.service.BusinessService;
import com.imooc.service.SyncService;
import com.imooc.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * 统计商户已售数量的定时任务
 */
@Component("BusinessTask")
public class BusinessTask {

    private final Logger logger= LoggerFactory.getLogger(BusinessTask.class);

    @Autowired
    private SyncService syncService;

    @Autowired
    private BusinessService businessService;

    public void syncNumber(){
        logger.info("同步数量");
        String date= DateUtil.dateFormat(new Date());
        String lastDate=syncService.getLastDate();
        if (syncService.addRecord(date)){
            businessService.updateNumber(lastDate);
        }
    }

    public void syncStar(){
        logger.info("同步星级");
    }
}
