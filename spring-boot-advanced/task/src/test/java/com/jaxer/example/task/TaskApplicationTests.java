package com.jaxer.example.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskApplicationTests {

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Test
	public void sendSimpleMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("聚餐通知");
		message.setText("周五晚上7点聚餐");
		message.setTo("xxiangru@163.com");
		message.setFrom("413730742@qq.com");
		mailSender.send(message);
	}

	@Test
	public void sendMime() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setSubject("聚餐通知");
		helper.setText("<b style='color:red'>周五晚上7点聚餐</br", true);

		helper.setTo("xxiangru@163.com");
		helper.setFrom("413730742@qq.com");
		// 添加附件
		helper.addAttachment("test.JPG", new File("/Users/jaxer/Desktop/test.JPG"));
		mailSender.send(mimeMessage);
	}

}
