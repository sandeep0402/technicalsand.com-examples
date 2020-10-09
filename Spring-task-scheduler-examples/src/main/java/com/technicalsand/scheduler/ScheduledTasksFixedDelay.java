package com.technicalsand.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksFixedDelay {
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksFixedDelay.class);

	@Scheduled(fixedDelay = 5000)
	public void scheduleTaskFixedDelay(){
		logger.info("scheduleTaskFixedDelay executed at {}", LocalDateTime.now());
	}

}
