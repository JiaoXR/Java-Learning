package com.jaxer.example.task.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试定时任务
 * <p>
 * Created by jaxer on 2018/11/23
 */
@Component
@Slf4j
public class SimpleJob {
	@Scheduled(cron = "0 0 * * * *")
	public void execute() {
		log.info("SimpleJob.execute 正在执行……");
	}
}
