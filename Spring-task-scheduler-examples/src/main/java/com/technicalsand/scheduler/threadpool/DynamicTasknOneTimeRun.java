package com.technicalsand.scheduler.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class DynamicTasknOneTimeRun {

	private static Logger logger = LoggerFactory.getLogger(DynamicTasknOneTimeRun.class);
	@Autowired
	private TaskScheduler taskScheduler;

	@PostConstruct
	public void scheduleTasks() {
		taskScheduler.schedule(() -> {
			dynamicTasksScheduleThreadPool();
		}, new Date(System.currentTimeMillis() + 5000));
	}

	public void dynamicTasksScheduleThreadPool() {
		logger.info("DynamicTasknOneTimeRun executed at {}", LocalDateTime.now());
	}
}
