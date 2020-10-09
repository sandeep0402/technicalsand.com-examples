package com.technicalsand.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksFixedRate {
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksFixedRate.class);

	@Scheduled(fixedRate = 10000)
	public void scheduleTaskFixedRate(){
		logger.info("scheduleTaskFixedRate executed at {}", LocalDateTime.now());
	}
}
