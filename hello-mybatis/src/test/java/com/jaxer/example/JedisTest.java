package com.jaxer.example;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 代码千万行，注释第一行。
 * 测试Jedis
 * <p>
 * Created by jaxer on 2019-08-17
 */
public class JedisTest {

	@Test
	public void testJedis() {
		Jedis jedis = new Jedis("127.0.0.1", 6379);
		jedis.set("hello", "world");
		String s = jedis.get("hello");
		System.out.println("s-->" + s);
		jedis.close();
	}

	@Test
	public void testPooled() {
		// 使用连接池
		JedisPool pool = new JedisPool("127.0.0.1", 6379);
		Jedis jedis = pool.getResource();
		String s = jedis.get("hello");
		System.out.println(s);

		jedis.close();
		pool.close();
	}
}
