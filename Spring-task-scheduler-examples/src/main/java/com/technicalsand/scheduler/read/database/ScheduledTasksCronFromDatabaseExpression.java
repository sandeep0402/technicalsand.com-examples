package com.technicalsand.scheduler.read.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksCronFromDatabaseExpression {

	final static Logger logger = LoggerFactory.getLogger(ScheduledTasksCronFromDatabaseExpression.class);
	@Autowired
	private ConfigurationRepository configurationRepository;

	@Scheduled(cron = "#{@getCronExpressionFromDb}")
	public void scheduleTasksCronFromDatabaseExpression() {
		logger.info("scheduleTasksCronFromDatabaseExpression executed at {}", LocalDateTime.now());
	}

	@Bean
	public String getCronExpressionFromDb() {
		// read here from database and return
		Configuration configuration = configurationRepository.findByName("scheduleTasksCronFromDatabaseExpression");
		return configuration.getExpression();
	}
}
