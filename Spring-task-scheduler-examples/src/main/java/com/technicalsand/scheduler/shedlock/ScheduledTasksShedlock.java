package com.technicalsand.scheduler.shedlock;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksShedlock {
	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksShedlock.class);

	@Scheduled(fixedDelay = 5000)
	@SchedulerLock(name = "scheduledTasksShedlock")
	public void scheduledTasksShedlock(){
		logger.info("scheduledTasksShedlock executed at {}", LocalDateTime.now());
	}
}
