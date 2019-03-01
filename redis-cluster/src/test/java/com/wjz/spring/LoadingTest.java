package com.wjz.spring;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

import junit.framework.Assert;

public class LoadingTest {

	private static final String SPRING_CONFIG_LOCATION = "classpath:spring/spring-redis.xml";
	private ApplicationContext context;
	private RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings("unchecked")
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext(SPRING_CONFIG_LOCATION);
		redisTemplate = context.getBean(RedisTemplate.class);
	}

	@Test
	public void insert() {
		redisTemplate.boundValueOps("today").set("2019/03/01");
	}
	
	@Test
	public void select() {
		Assert.assertEquals("2019/03/01", redisTemplate.boundValueOps("today").get());
	}

	@Test
	public void update() {
		redisTemplate.boundValueOps("today").set("2016/01/01");
		Assert.assertEquals("2016/01/01", redisTemplate.boundValueOps("today").get());
	}
	
	@Test
	public void delete() {
		redisTemplate.boundValueOps("today").expire(0, TimeUnit.MILLISECONDS);
		Assert.assertEquals(null, redisTemplate.boundValueOps("today").get());
	}
	
}
