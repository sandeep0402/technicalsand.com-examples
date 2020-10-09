package com.technicalsand.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksCron {
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksCron.class);

	@Scheduled(cron = "*/3 * * ? * *")
	public void scheduleCronTask(){
		logger.info("scheduleCronTask executed at {}", LocalDateTime.now());
	}
}
