package com.jaxer.source;

import com.jaxer.doc.aware.CommandManager;
import org.junit.Test;

import java.util.HashMap;

/**
 * 测试 Aware 接口相关
 * Created by jaxer on 2018/12/5
 */
public class AwareTests extends BaseTests {
	@Test
	public void testAware() {
		CommandManager commandManager = new CommandManager();
		commandManager.setApplicationContext(context);
		commandManager.process(new HashMap());
	}
}
