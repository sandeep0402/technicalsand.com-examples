package com.technicalsand.scheduler.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class DynamicTasksScheduledThreadPool {
    private static Logger logger = LoggerFactory.getLogger(DynamicTasksScheduledThreadPool.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @PostConstruct
    public void scheduleTasks(){
        threadPoolTaskScheduler.scheduleWithFixedDelay(
                (new Runnable() {
                    public void run() {
                        dynamicTasksScheduleThreadPool();
                    }
                }),
                3000);
    }

    public void dynamicTasksScheduleThreadPool(){
        logger.info("dynamicTasksScheduleThreadPool executed at {}", LocalDateTime.now());
    }
}
