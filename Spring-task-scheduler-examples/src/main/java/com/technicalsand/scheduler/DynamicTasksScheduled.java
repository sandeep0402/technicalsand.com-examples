package com.technicalsand.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class DynamicTasksScheduled implements SchedulingConfigurer {

	private static Logger logger = LoggerFactory.getLogger(DynamicTasksScheduled.class);

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());
		taskRegistrar.addFixedRateTask((new Runnable() {
			@Override
			public void run() {
				dynamicTasksSchedule();
			}
		}), 5000);

		// dynamically schedule cronTask
//        taskRegistrar.addCronTask((new Runnable() {
//            public void run() {
//                dynamicTasksSchedule();
//            }
//        }), "*/10 * * ? * *");

	}

	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(50);
	}

	public void dynamicTasksSchedule() {
		logger.info("dynamicTasksSchedule executed at {}", LocalDateTime.now());
	}
}
