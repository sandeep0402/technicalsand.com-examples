package com.technicalsand.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksCronPropertiesConfig {
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksCronPropertiesConfig.class);

	@Scheduled(cron="${scheduledTasksCronPropertiesConfig.cron.expression}")
	public void scheduledTasksCronPropertiesConfig(){
		logger.info("scheduledTasksCronPropertiesConfig executed at {}", LocalDateTime.now());
	}
}
