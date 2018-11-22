package com.jaxer.example.task.controller;

import com.jaxer.example.task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jaxer on 2018/11/23
 * 测试异步任务
 */
@RestController
public class AsyncController {
	@Autowired
	private AsyncService asyncService;

	@GetMapping(value = "/hello")
	public String hello() {
		asyncService.hello();
		return "success";
	}
}
