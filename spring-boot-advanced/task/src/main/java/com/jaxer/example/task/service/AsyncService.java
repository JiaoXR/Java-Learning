package com.jaxer.example.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by jaxer on 2018/11/23
 * 测试异步任务
 */
@Service
@Slf4j
public class AsyncService {

	@Async
	public void hello() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("异步任务 hello 执行中……");
	}
}
