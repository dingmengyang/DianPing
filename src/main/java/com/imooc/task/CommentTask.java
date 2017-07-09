package com.imooc.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 更新星级的定时任务
 */
@Component("CommentTask")
public class CommentTask {

    private final Logger logger= LoggerFactory.getLogger(BusinessTask.class);

    public void syncStar(){
        logger.info("同步星级");
    }
}
